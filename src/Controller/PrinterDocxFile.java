package Controller;

import models.Appointment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PrinterDocxFile implements Printer {

    private static PrinterDocxFile PrinterDocxFile;
    private FileWriter myWriter ;
    private String filename = "file.docx";
    private final static SimpleDateFormat DATE_FORMAT_DETAILS = new SimpleDateFormat("dd-MM-yyyy  HH:mm", Locale.getDefault());

    private PrinterDocxFile(){}

    public static PrinterDocxFile getInstance(){
        if (PrinterDocxFile == null){
            PrinterDocxFile = new PrinterDocxFile();
        }
        return PrinterDocxFile ;
    }
    @Override
    public void print(Appointment appointment) throws IOException {
        String herder = "******************************************* Rendez-vous N° " +appointment.getId() + " ******************************************";

        String appointmentStringFormat =  "\nLa date : " + DATE_FORMAT_DETAILS.format(appointment.getTime()) +
                "\nL'objet : " + appointment.getObject() + "\n\n-------- Le patient N°"+appointment.getClient().getId()+"-------\n" +
                "\n- Le nom et prenom du patient : " + appointment.getClient().getFirstName() + " " + appointment.getClient().getLastName() +
                "\n- L'adresse : "+appointment.getClient().getAddress() +
                "\n- Le numero du telephone : "+ appointment.getClient().getPhone()+
                "\n- Email : " + appointment.getClient().getEmail() +
                "\n- Information supplementaire : " + appointment.getClient().getInformation() ;

        String footer = "\n\n*******************************************************************************************************\n\n\n\n";

        myWriter.write( herder + appointmentStringFormat + footer);

       java.awt.Desktop.getDesktop().print(new File("file.docx"));
    }
    public void openFile() throws IOException {
        myWriter = new FileWriter(this.filename);
    }

    public void closeFile() throws IOException {
        myWriter.close();
    }

}
