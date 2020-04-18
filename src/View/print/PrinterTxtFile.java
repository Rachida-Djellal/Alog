package Controller.print;

import models.Appointment;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PrinterTxtFile implements Printer {

    private static PrinterTxtFile printerTxtFile;
    private String filename = "filename.txt";
    private FileWriter myWriter ;
    private final static SimpleDateFormat DATE_FORMAT_DETAILS = new SimpleDateFormat("dd-MM-yyyy  HH:mm", Locale.getDefault());


    private PrinterTxtFile(){}

    public static PrinterTxtFile getInstance(){
        if (printerTxtFile == null){
            printerTxtFile = new PrinterTxtFile();
        }
        return printerTxtFile ;
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
    }

    public void openFile() throws IOException {
        myWriter = new FileWriter(this.filename);
    }

    public void closeFile() throws IOException {
        myWriter.close();
    }
}
