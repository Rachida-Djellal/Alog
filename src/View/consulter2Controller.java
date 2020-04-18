package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import models.Appointment;
import models.AppointmentManager;
import models.Client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class consulter2Controller implements Initializable {

    @FXML
    private ListView<Appointment> list2;
    // get AppointmentManager instance
    AppointmentManager manager=AppointmentManager.getInstance();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void myFunction(Client patient)
    {
        ObservableList < Appointment > data = FXCollections.observableArrayList(manager.getByClient(patient));
        list2.setItems(data);

    }

}
