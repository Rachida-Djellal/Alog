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
}
