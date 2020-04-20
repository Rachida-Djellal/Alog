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
import java.util.Date;
import java.util.ResourceBundle;

public class Aficher2Controller implements Initializable {

    @FXML
    private ListView<Appointment> list4;
    AppointmentManager manager=AppointmentManager.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void myFunction(Date datee)
    {

        ObservableList< Appointment > data = FXCollections.observableArrayList(manager.getAppointmentByDay(datee));
        list4.setItems(data);

    }
    public void changeScreenButton(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(root);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
