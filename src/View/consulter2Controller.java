package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentManager;
import models.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class consulter2Controller implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void myFunction(ArrayList<Client> patient)
    {    ObservableList<Appointment> data =null;
        for (int i=0;i<patient.size();i++) {
            data = FXCollections.observableArrayList(manager.getByClient(patient.get(i)));

        }
        list2.setItems(data);
    }

}
