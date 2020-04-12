package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentManager;
import models.Client;
import models.ClientManager;

import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        // because is Singleton
        ClientManager clientManager =  ClientManager.getInstance() ;
        AppointmentManager appointmentManager =AppointmentManager.getInstance();
        //  Client client = new Client(8,"Rachida","Djelal","Msila","0604030203" ,"gr_djelel@esi.dz","rien") ;
        //clientManager.insertClient(client);
       /**
        * This part is for testing the getAll() method
        * */

       ArrayList<Client> res =   clientManager.getAll() ;
        System.out.println(res.size());
        for (Client c: res) {
            System.out.println(c.getFirstName());
        }
        appointmentManager.create( new Appointment(1,res.get(5),new Date(), "Radio") );
        System.out.println("c.getFirstName()");

       ArrayList<Appointment>  re =appointmentManager.getAll();

        for (Appointment c: re) {
            System.out.println(c.getTime());
        }

      /**/

         /**
          * and this is for testing the insert method
          * */
         //   clientManager.insertClient(new Client("Youcef", "Lerari"));
        launch(args);
    }
}
