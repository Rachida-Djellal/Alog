package View;

import Controller.PrinterTxtFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import models.Appointment;
import models.AppointmentManager;

import java.util.ArrayList;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // ResourceBundle resources = ResourceBundle.getBundle("View/sample.fxml");;

       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");




        primaryStage.setScene(new Scene(root, 650, 500));
        primaryStage.show();

    }



    public static void main(String[] args)
    {


       // ClientManager clientManager = ClientManager.getInstance();
     //   clientManager.insertClient(new Client(11,"Imad","Stihi","Ain Naaja","0424236751","imad@gmail.com" , "Bien"));

     //   Appointment app = new Appointment(10, new Client(11,"Youcef","lerari","Bouzerria","0424236751","youcef@gmail.com" , "Bien"),new Date(),"visite");
        AppointmentManager appointmentManager = AppointmentManager.getInstance();
       // appointmentManager.create(app);
        PrinterTxtFile printerTxtFile = PrinterTxtFile.getInstance();
        ArrayList<Appointment> res = appointmentManager.getAll();
        try {

            printerTxtFile.openFile();
            for (Appointment re : res) {
                printerTxtFile.print(re);
            }
             printerTxtFile.closeFile();
        }catch (Exception e) {
                e.printStackTrace();
                //System.out.println(e.getMessage());}

        }
        launch(args);
    }
}
