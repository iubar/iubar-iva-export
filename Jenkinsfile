pipeline {
    agent {    
    	docker {   	
    		image 'iubar-maven-alpine'
    		label 'docker'
    		args '-v ${HOME}/.m2:/home/jenkins/.m2:rw,z -v ${HOME}/.sonar:/home/jenkins/.sonar:rw,z'
    	} 
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn --batch-mode clean compile'
            }
        }
		stage('Test') {
            steps {
                sh 'mvn --batch-mode -Djava.io.tmpdir=${WORKSPACE}@tmp -Djava.awt.headless=true test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // show junit log in Jenkins 
                }
            }
        }
        stage('Analyze') {
            steps {
				sh '''
					echo "SKIP_SONARQUBE: ${SKIP_SONARQUBE}"
					if [ $SKIP_SONARQUBE = true ]; then												
						echo "Skipping sonar-scanner analysis"
            		else
               			sonar-scanner
                	fi
				'''						
            }
        }
        stage('Quality gate') {

            steps {
				sh '''
					SONAR_PROJECTKEY=$(grep sonar.projectKey sonar-project.properties | cut -d '=' -f2)
					echo "SONAR_PROJECTKEY: ${SONAR_PROJECTKEY}"				
				    QUALITYGATE=$(curl --data-urlencode "projectKey=${SONAR_PROJECTKEY}" ${SONAR_URL}/api/qualitygates/project_status | jq '.projectStatus.status')
				    QUALITYGATE=$(echo "$QUALITYGATE" | sed -e 's/^"//' -e 's/"$//')
				    echo "QUALITYGATE: ${QUALITYGATE}"
                    if [ $QUALITYGATE = OK ]; then
                       echo "High five !"
                    else
                       echo "Poor quality !"
					   echo "( see ${SONAR_URL}/dashboard?id=${SONAR_PROJECTKEY})"
                       exit 1
                    fi
				'''
            }
        }
		stage ('Deploy') {
            steps {
                sh 'mvn --batch-mode -DskipTests=true deploy'
            }
        }
    }
	post {
		always { // oppure utilizzare changed
			sh "curl -H 'JENKINS: Pipeline Hook Iubar' -i -X GET -G ${env.IUBAR_WEBHOOK_URL} -d status=${currentBuild.currentResult} -d job_name='${JOB_NAME}' -d build_number='${BUILD_NUMBER}'"
		}
		cleanup {
			cleanWs()
			dir("${env.WORKSPACE}@tmp") {				
				deleteDir()
			}
        }
    }    
}