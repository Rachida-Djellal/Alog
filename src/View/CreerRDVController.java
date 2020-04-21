package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private boolean isInputValid() {
        String errorMessage = "";

        if (Nom.getText() == null || Nom.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (Prenom.getText() == null || Prenom.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (Objet.getText() == null || Objet.getText().length() == 0) {
            errorMessage += "No valid object name!\n";
        }
        if (email.getText() == null || email.getText().length() == 0) {
            errorMessage += "No valid email name!\n";
        }
        if (adresse.getText() == null || adresse.getText().length() == 0) {
            errorMessage += "No valid adresse name!\n";
        }
        if (infos.getText() == null || infos.getText().length() == 0) {
            errorMessage += "No valid informations name!\n";
        }
        if (tele.getText() == null || tele.getText().length() == 0) {
            errorMessage += "No valid phone name!\n";
        }
        if (Date1.getValue() == null || Date1.getValue().toString().length() == 0) {
            errorMessage += "No valid date name!\n";
        }
        if (Heure.getText() == null || Heure.getText().length() == 0) {
            errorMessage += "No valid heure name!\n";
        }




        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    public void CreerRdv( ActionEvent actionEvent)
    {
        boolean valid = isInputValid();
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
     datee.setTime(Long.parseLong(heure));
        Client patient = new Client(nom,pre,adr,telephone,eml,information);
        manager1.insertClient(patient);
        Appointment RDV= new Appointment(patient,datee,objet);
        manager.create(RDV);
        System.out.println(RDV.getId());

   if  (valid==true) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

       alert.setTitle("confirmation");
       alert.setHeaderText("succee");
       alert.setContentText("Le rendezvous est ajouter.");

       alert.showAndWait();
   }

    }
}
