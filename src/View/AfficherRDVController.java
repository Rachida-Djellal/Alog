package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import models.AppointmentManager;
import models.Client;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AfficherRDVController {
    @FXML
    private DatePicker Jour;
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

    public void AfficherRDV (ActionEvent actionEvent)
    {

        // String date= Jour.getEditor().getText();
        LocalDate date= Jour.getValue();
        Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
        Date datee = Date.from(instant);
        System.out.println(datee + "\n" + instant + "\n" + date);

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Aficher2.fxml"));
            Parent root = (Parent) loader.load();
            Aficher2Controller secController=loader.getController();

            secController.myFunction(datee);

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
