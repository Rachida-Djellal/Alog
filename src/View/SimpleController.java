package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import View.*;
public class SimpleController implements Initializable {
    public Label helloWorld;

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");

    }

    @FXML
    private TextField Nom;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse1;
    @FXML
    private TextField Prenom;
    @FXML
    private DatePicker Date;
    @FXML
    private DatePicker Jour;
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
    @FXML private ListView listView;
    @FXML
    private CheckBox supp;
    @FXML
    private CheckBox avancer;
    @FXML
    private CheckBox reporter;
    @FXML
    private TextField nom1;
    @FXML
    private DatePicker date1;
    @FXML
    private TextField heure1;
    public void CreerRdv( ActionEvent actionEvent)
    {

        String nom= Nom.getText();
        String date= Date.getEditor().getText();
        String heure= Heure.getText();
        String objet= Objet.getText();
        String pre=Prenom.getText();
        String telephone = tele.getText();
        String eml=email.getText();
        String information=infos.getText();
        String adr=adresse.getText();




    }
    public void AfficherRDV (ActionEvent actionEvent)
    {

        String date= Jour.getEditor().getText();


    }
    public void ConsulterRDV (ActionEvent actionEvent)
    {

       String patient= nom.getText();
       String patient1=prenom.getText();
       String add=adresse1.getText();



    }
    public void ModifierRdv (ActionEvent actionEvent)
    {

        String  name = nom1.getText();
        String  jour= date1.getEditor().getText();
        String   heu =heure1.getText();
        if (supp.isSelected())
          //  order += "\npineapple";

        if (avancer.isSelected())
         //   order += "\npepperoni";

        if (reporter.isSelected())
          //  order += "\nbacon";
            ;


    }
    public void changeScreenButtonSample(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void changeScreenButtonCreer(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("creerRDV.fxml"));
        Scene tableViewScene = new Scene(root);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void changeScreenButtonConsulter(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ConsulterRDV.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void changeScreenButtonImprimer(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ImprimerRdv.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void changeScreenButtonModifier(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifierRDV.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void changeScreenButtonAfficher(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficherRDV.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       // listView.getItems().addAll("Golf Balls","Wedges","Irons","Tees","Driver","Putter");
    }
}
