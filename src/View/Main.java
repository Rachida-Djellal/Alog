package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // ResourceBundle resources = ResourceBundle.getBundle("View/sample.fxml");;

       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");




        primaryStage.setScene(new Scene(root, 650, 470));
        primaryStage.show();

    }



    public static void main(String[] args)
    {
        // because is Singleton
        //ClientManager clientManager =  ClientManager.getInstance() ;

       /**
        * This part is for testing the getAll() method
        * */
      /*  ArrayList<Client> res =   clientManager.getAll() ;
        System.out.println(res.size());
        for (Client c: res) {
            System.out.println(c.getFirstName());
        }
        System.out.println("c.getFirstName()");
      */
         /**
          * and this is for testing the insert method
          * */
         //   clientManager.insertClient(new Client("Youcef", "Lerari"));
        launch(args);
    }
}
