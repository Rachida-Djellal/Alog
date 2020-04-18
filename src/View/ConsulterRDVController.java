package View;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentManager;
import models.Client;

import java.io.IOException;
import java.util.ArrayList;

public class ConsulterRDVController {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse1;
    @FXML
    private TextField phone;
    @FXML
    private TextField mail;
    @FXML
    private TextArea inform;
    @FXML
    private ListView<Appointment> list2;
    // get AppointmentManager instance
    AppointmentManager manager=AppointmentManager.getInstance();
    public void changeScreenButtonSample(ActionEvent event) throws IOException
    {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void ConsulterRDV (ActionEvent event) throws IOException {

        String patient2= nom.getText();
        String patient1=prenom.getText();
        String add=adresse1.getText();
        String mail1=mail.getText();
        String phone1=phone.getText();
        String inform1=inform.getText();
        ArrayList array= new ArrayList<String>();
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Consulter2.fxml"));
            Parent root = (Parent) loader.load();
            consulter2Controller secController=loader.getController();
            Client patient=new Client(patient2,patient1,add,mail1,phone1,inform1);
            secController.myFunction(patient);

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
