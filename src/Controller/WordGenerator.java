package Controller;


import models.Appointment;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WordGenerator {

    //Get lines from text file
    private final static SimpleDateFormat DATE_FORMAT_DETAILS = new SimpleDateFormat("dd-MM-yyyy  HH:mm", Locale.getDefault());
    public List<String> getLines (String fileName) throws Exception {
        //ReadFile instance
        ReadFile rf = new ReadFile();

        //Read the text
        try {
            List<String> lines = rf.readLines(fileName);
            for (String line : lines) {
                System.out.println(line);
            }
            return lines;
        } catch (IOException e) {
            // Print out the exception that occurred
            System.out.println("Unable to create " + fileName + ": " + e.getMessage());
            throw e;
        }
    }

    //Create Word
    public void createWord(Appointment appointment) throws IOException {
        FileOutputStream out = new FileOutputStream(
                new File("createdWord.docx"));
        XWPFDocument document = new XWPFDocument();

            //Blank Document

            //Write the Document in file system


            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();



        run.setText( "**************************Rendez-vous N° " +appointment.getId() + " ****************************\n    \t\t   \t" +
                "\n     \n \t \n -La date : " + DATE_FORMAT_DETAILS.format(appointment.getTime())+" \n                       \t  \t \t \t \t  \t \n" +
                "\n \t         -L'objet : " + appointment.getObject() + "\n\n-------- Le patient N°"+appointment.getClient().getId()+"-------    \t                                   \t\n" +
                "\n\t             -Le nom et prenom du patient : " + appointment.getClient().getFirstName() + " " + appointment.getClient().getLastName()+ "\t \t \t \t                    \t\n"+
                "\n\t             -L'adresse : "+appointment.getClient().getAddress() +"   \t \t          \t \t                  \t \n\t "+
                "\n\t             -Le numero du telephone : "+ appointment.getClient().getPhone()+"\t \t    \t \t             \t \n\t"+
                "\n\t-            -Email : " + appointment.getClient().getEmail() +" \t \t         \t \t                     \t                 \n\t"+
                "\n\t-          - Information supplementaire : " + appointment.getClient().getInformation()+" \t \t\t \t \t \n\t"+
                "\n\n\t***************************************************************************\n\n\n\n");

        document.write(out);



        out.close();

    }
}
