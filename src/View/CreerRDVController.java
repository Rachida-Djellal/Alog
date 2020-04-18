package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentManager;
import models.Client;
import models.ClientManager;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreerRDVController
{

    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private DatePicker Date1;

    @FXML
    private TextField Heure;
    @FXML
    private TextField Objet;
    @FXML
    private TextField tele;
    @FXML
    private TextField email;
    @FXML
    private TextArea infos;
    @FXML
    private TextField adresse;



    public void changeScreenButtonSample(ActionEvent event) throws IOException
    {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    // get AppointmentManager instance
    AppointmentManager manager=AppointmentManager.getInstance();
    ClientManager manager1=ClientManager.getInstance();

    // la fonction pour la creation d'un rendez vous d'un patient
    public void CreerRdv( ActionEvent actionEvent)
    {

        String nom= Nom.getText();
        LocalDate date= Date1.getValue();
        Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
        Date datee = Date.from(instant);
        System.out.println(datee + "\n" + instant + "\n" + date);
        String heure= Heure.getText();
        String objet= Objet.getText();
        String pre=Prenom.getText();
        String telephone = tele.getText();
        String eml=email.getText();
        String information=infos.getText();
        String adr=adresse.getText();
        Client patient = new Client(nom,pre,adr,telephone,eml,information);
        Appointment RDV= new Appointment(patient,datee,objet);
        manager.create(RDV);
        manager1.insertClient(patient);
    }
}
