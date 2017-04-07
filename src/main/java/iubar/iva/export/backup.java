/*FileInputStream fIn;
		FileChannel fChan;
		ByteBuffer mBuf;
		int count;

		try {
			fIn = new FileInputStream(new File(FILE_PATH));
			fChan = fIn.getChannel();
			mBuf = ByteBuffer.allocate(128);

			do {
				count = fChan.read(mBuf);

				if(count != -1) {
					mBuf.rewind();
					if (count < position ) {
						for(int i = 0; i < count ; i++) {

						}
					}
					for(int i = 0; i < count; i++) {
						char c = (char)mBuf.get();
					}
				}

			} while(count != -1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

/*public final class iubar.iva.export.CreateConfigFile
{

    public static final String PDF_PATH = "/home/yawn/temp/SpecificheIva2017";
    public static final String OUTPUT_PATH = "/home/yawn/temp/iva.cfg";

    private Writer stream;
    private LinkedList<String> format;
    private LinkedList<Integer> length;
    private LinkedList<Integer> field;
    private LinkedList<Integer> position;

    public void createFile(){

        if(field.size() == position.size() &&
                position.size() == length.size() &&
                length.size() == format.size() &&
                format.size() > 0){
            try{

                stream = new PrintWriter(OUTPUT_PATH);
                String output = field.getLast() + " " + position.getLast() + " " + length.getLast() + " " + format.getLast();
                stream.write(output);

            } catch (IOException e){
                e.printStackTrace();
            }
        } else {

            System.out.print(field.size() + "+" + position.size() + "+" + format.size() + "+" + length.size() + "H");

        }



    }

    public void stringCheck(String pdfLine) {

        //Pattern posizionali = Pattern.compile("AN|CF|CN|PI|DT|NU|PN|PR|CB|D4");
        //Pattern non_posizionali = Pattern.compile("AN|CB|CB12|CF|CN|PI|DA|DT|DN|D4|D6|NP|NU|NX|PC|PR|PN|QU");
        boolean position_found = false;

        pdfLine = pdfLine.replaceAll("[a-z\\W ]{1,}", " ");

        String[] tmp = pdfLine.split("\\s+");

        if(tmp.length > 0){
            if (StringUtils.isNumeric(tmp[0])){

                this.field.add(Integer.parseInt(tmp[0]));

                for(int i = 0 ; i < tmp.length ; i++){

                    if (i != 1) {

                        if (StringUtils.isNumeric(tmp[i])) {

                            if (position_found) {

                                this.position.add(Integer.parseInt(tmp[i]));
                            } else {

                                this.length.add(Integer.parseInt(tmp[i]));
                            }
                            position_found = !position_found;
                        }

                        if (i > 1){         //set to 2, format won't be less than that so less runtime

                            if (tmp[i].matches("AN|CF|CN|PI|DT|NU|PN|PR|CB|D4")){
                                this.format.add(tmp[i]);
                            }
                        }
                    }
                }
            } else{
                System.out.print("-NO-");
            }
            createFile();
        }
    }

    public void getTextFromPDF()  throws IOException{

        PDDocument pdf = null;
        PDFTextStripper stripper = new PDFTextStripper();
        stream = new StringWriter();

        try {

            pdf = PDDocument.load(new File(PDF_PATH));
            stripper.setStartPage(16);
            stripper.setEndPage(20);
            stripper.writeText(pdf, stream);
            String[] split = stream.toString().split("\\R");
            for(String obj : split){
                this.stringCheck(obj);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        finally
        {
            if( pdf != null )
            {
                pdf.close();
            }
        }

    }

    public iubar.iva.export.CreateConfigFile(){

        this.field = new LinkedList<Integer>();
        this.position = new LinkedList<Integer>();
        this.length = new LinkedList<Integer>();
        this.format = new LinkedList<String>();

    }

    public static void main (String[] args) throws IOException {

        iubar.iva.export.CreateConfigFile file = new iubar.iva.export.CreateConfigFile();
        file.getTextFromPDF();

    }

}*/
