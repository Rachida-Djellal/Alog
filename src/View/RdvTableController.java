package View;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Controller.PrinterDocxFile;
import Controller.PrinterTxtFile;
import Controller.WordGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentManager;
import models.Client;

import javax.swing.*;

//import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class RdvTableController {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Appointment> personTable;
    @FXML
    private TableColumn<Appointment, Client> firstNameColumn;

    @FXML
    private TableColumn<Appointment, String> ObjectColumn;
    @FXML
    private TableColumn<Appointment, Date> DateColumn;
    @FXML
      private   Label firstNameLabel ;
    @FXML
    private   Label lastNameLabel ;
    @FXML
    private   Label streetLabel ;
    @FXML
    private   Label adresse ;
    @FXML
    private   Label phone ;
    @FXML
    private   Label email ;
    @FXML
    private   Label object ;
    private ObservableList<Appointment> masterData = null;
    private ObservableList<Appointment> filteredData = FXCollections.observableArrayList();
    AppointmentManager manager=AppointmentManager.getInstance();
    PrinterTxtFile manager1=PrinterTxtFile.getInstance();
    PrinterDocxFile manager2=PrinterDocxFile.getInstance();
    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public RdvTableController() {


      masterData=FXCollections.observableArrayList(manager.getAll());
      System.out.println(masterData.size());
        // Initially add all data to filtered data
        filteredData.addAll(masterData);
        System.out.println(filteredData.size());
        // Listen for changes in master data.
        // Whenever the master data changes we must also update the filtered data.
        masterData.addListener(new ListChangeListener<Appointment>() {


            @Override
            public void onChanged(ListChangeListener.Change<? extends Appointment> change) {
                updateFilteredData();
            }
        });
        System.out.println(filteredData.size());
    }
    public void print (ActionEvent event) throws Exception
    {
        // manager1.openFile();
       // Appointment selectedPerson = personTable.getSelectionModel().getSelectedItem();

        WordGenerator wg = new WordGenerator();

        Appointment selectedPerson = personTable.getSelectionModel().getSelectedItem();
        //Create word document according to VK lines
        wg.createWord(selectedPerson);
      //  manager1.print(selectedPerson);
       // manager1.closeFile();
    }
    private void showPersonDetails(Appointment person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getClient().getLastName());
            lastNameLabel.setText(person.getClient().getFirstName());
            streetLabel.setText(person.getTime().toString());
             adresse.setText(person.getClient().getAddress());
             phone.setText(person.getClient().getPhone());
             email.setText(person.getClient().getEmail());
             object.setText(person.getObject());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");

        }
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            Appointment selectedPerson = personTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedPerson.getId());
              manager.delete(selectedPerson);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("confirmation");
            alert.setHeaderText("succee");
            alert.setContentText("le rendezvous est supprime");

            alert.showAndWait();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    public void changeScreenButton(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void handleEditPerson() {
        Appointment selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {

            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditRDV.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Person");
                dialogStage.initModality(Modality.WINDOW_MODAL);

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the person into the controller.
                EditRdvCpntroller controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPerson(selectedPerson);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();


            } catch (IOException e) {
                e.printStackTrace();

            }

        }
    }
    @FXML
    private void initialize() {
        // Initialize the person table
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<Appointment, Client>("client"));

          ObjectColumn.setCellValueFactory(
                  new PropertyValueFactory<Appointment, String>("object"));
           DateColumn.setCellValueFactory(
                   new PropertyValueFactory<Appointment, Date>("time"));



        // Add filtered data to the table
        personTable.setItems(filteredData);
// Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        // Listen for text changes in the filter text field
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                updateFilteredData();
            }
        });
    }

    /**
     * Updates the filteredData to contain all data from the masterData that
     * matches the current filter.
     */
    private void updateFilteredData() {
        filteredData.clear();

        for (Appointment p : masterData) {
            if (matchesFilter(p)) {
                filteredData.add(p);
            }
        }

        // Must re-sort table after items changed
        reapplyTableSortOrder();
    }

    /**
     * Returns true if the person matches the current filter. Lower/Upper case
     * is ignored.
     *
     * @param person
     * @return
     */
    private boolean matchesFilter(Appointment person) {
        String filterString = filterField.getText();
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (person.getObject().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (person.getClient().getFirstName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }else if(person.getClient().getLastName().toLowerCase().indexOf(lowerCaseFilterString) != -1){
             return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Appointment, ?>> sortOrder = new ArrayList<>(personTable.getSortOrder());
        personTable.getSortOrder().clear();
        personTable.getSortOrder().addAll(sortOrder);
    }
}