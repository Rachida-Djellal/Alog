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


       // ClientManager clientManager = ClientManager.getInstance();
     //   clientManager.insertClient(new Client(11,"Imad","Stihi","Ain Naaja","0424236751","imad@gmail.com" , "Bien"));

     //   Appointment app = new Appointment(10, new Client(11,"Youcef","lerari","Bouzerria","0424236751","youcef@gmail.com" , "Bien"),new Date(),"visite");
        AppointmentManager appointmentManager = AppointmentManager.getInstance();
       // appointmentManager.create(app);
        ArrayList<Appointment> res = appointmentManager.getAppointmentByDay(new Date());
        for (Appointment re:
             res) {
            System.out.println(re);
        }
        launch(args);
    }
}
