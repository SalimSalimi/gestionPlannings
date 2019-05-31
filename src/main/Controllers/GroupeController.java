package main.Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Models.Groupe;
import main.Models.Section;
import main.Models.Specialite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupeController implements Initializable {


    //SECTION
    @FXML
    private JFXTextField txtTitreSection, txtRechercherSection;

    @FXML
    private JFXComboBox comboNiveauSection, comboSpecialiteSection;

    @FXML
    private TableView<Section> tableSection;

    @FXML
    private TableColumn<Section, String> columnTitreSection, columnNiveauSection, columnSpecialiteSection;

    ObservableList<Section> allSections = FXCollections.observableArrayList();
    /******--------------------------------------------------------------------------------------------------**********/

    //SPECIALITE

    @FXML
    private JFXTextField txtTitreSpecialite, txtCapaciteSpecialite;

    @FXML
    private JFXComboBox comboNiveauSpecialite;

    @FXML
    private TableView<Specialite> tableSpecialite;

    @FXML
    private TableColumn<Specialite, String> columnTitreSpecialite, columnCapaciteSpecialite, columnNiveauSpecialite;

    ObservableList<Specialite> allSpecialites = FXCollections.observableArrayList();
/******--------------------------------------------------------------------------------------------------**********/

    //GROUPES
    @FXML
    private JFXTextField txtTitreGroupe, txtCapaciteGroupe, txtRechercherGroupe;

    @FXML
    private JFXComboBox comboNiveauGroupe;

    @FXML
    private TableView<Groupe> tableGroupe;

    @FXML
    private TableColumn<Groupe, String> columnTitreGroupe, columnNiveauGroupe;
    @FXML
    private TableColumn<Groupe, Integer>  columnCapaciteGroupe;

    ObservableList<Groupe> allGroupes = FXCollections.observableArrayList();

    /******--------------------------------------------------------------------------------------------------**********/
    //SECTIONS
    private ObservableList<Section> getAllSections(){
        ObservableList sections = allSections;

        sections.add(new Section("A", "M1", "GL"));
        sections.add(new Section("B", "M1", "GL"));
        sections.add(new Section("C", "M1", "GL"));
        sections.add(new Section("A", "L1", "MI"));
        return sections;
    }

    public void deleteSectionBtnClicked() {
        ObservableList<Section> sectionsSelected, sections;
        sectionsSelected = tableSection.getSelectionModel().getSelectedItems();

        sections = tableSection.getItems();

        sectionsSelected.forEach(sections::remove);
    }

    public void updateSectionBtnClicked(){
        int position;
        ObservableList<Section> allSections;
        Section updateSection = new Section();
        allSections = tableSection.getItems();
        position = tableSection.getSelectionModel().getSelectedIndex();

        updateSection.setTitre(txtTitreSection.getText());
        updateSection.setNiveau(comboNiveauSection.getSelectionModel().getSelectedItem().toString());
        updateSection.setSpecialite(comboSpecialiteSection.getSelectionModel().getSelectedItem().toString());

        allSections.set(position, updateSection);

    }


    private void searchSections(){
        //FILTER
        FilteredList<Section> filteredLocal = new FilteredList<>(allSections, p -> true);

        txtRechercherSection.textProperty().addListener((observable, oldValue, newValue) ->
                filteredLocal.setPredicate( section -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (section.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                })
        );

        SortedList<Section> sortedData = new SortedList<>(filteredLocal);
        sortedData.comparatorProperty().bind(tableSection.comparatorProperty());
        tableSection.setItems(sortedData);
    }

    public void addSectionBtnClicked(){
        Section section = new Section();

        section.setTitre(txtTitreSection.getText());
        section.setSpecialite(comboNiveauSection.getSelectionModel().getSelectedItem().toString());
        section.setNiveau(comboSpecialiteSection.getSelectionModel().getSelectedItem().toString());

        allSections.add(section);
    }
/******--------------------------------------------------------------------------------------------------------------------**///
    //SPECIALITE

    private ObservableList<Specialite> getAllSpecialites(){
    ObservableList<Specialite> specialites = allSpecialites;

    specialites.add(new Specialite("A", "M1", 15));
    specialites.add(new Specialite("B", "M1", 20));
    specialites.add(new Specialite("C", "M1", 30));
    specialites.add(new Specialite("A", "L1", 60));
    return specialites;
}

    public void deleteSpecialiteBtnClicked() {
        ObservableList<Specialite> specialitesSelected, specialites;
        specialitesSelected = tableSpecialite.getSelectionModel().getSelectedItems();

        specialites = tableSpecialite.getItems();

        specialitesSelected.forEach(specialites::remove);
    }

    public void updateSpecialiteBtnClicked(){
        int position;
        ObservableList<Specialite> allSpecialites;
        Specialite updateSpecialite = new Specialite();
        allSpecialites = tableSpecialite.getItems();
        position = tableSpecialite.getSelectionModel().getSelectedIndex();

        updateSpecialite.setTitre(txtTitreSpecialite.getText());
        updateSpecialite.setNiveau(comboNiveauSpecialite.getSelectionModel().getSelectedItem().toString());
        updateSpecialite.setCapacite(Integer.parseInt(txtCapaciteSpecialite.getText()));

        allSpecialites.set(position, updateSpecialite);

    }


    private void searchSpecialite(){
        //FILTER
        FilteredList<Section> filteredLocal = new FilteredList<>(allSections, p -> true);

        txtRechercherSection.textProperty().addListener((observable, oldValue, newValue) ->
                filteredLocal.setPredicate( section -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (section.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                })
        );

        SortedList<Section> sortedData = new SortedList<>(filteredLocal);
        sortedData.comparatorProperty().bind(tableSection.comparatorProperty());
        tableSection.setItems(sortedData);
    }

    public void addSpecialiteBtnClicked(){
        Specialite specialite = new Specialite();

        specialite.setTitre(txtTitreSpecialite.getText());
        specialite.setNiveau(comboNiveauSpecialite.getSelectionModel().getSelectedItem().toString());
        specialite.setCapacite(Integer.parseInt(txtCapaciteSpecialite.getText()));

        allSpecialites.add(specialite);
        tableSpecialite.getItems().add(specialite);
    }



    /******--------------------------------------------------------------------------------------------------------------------**///


    // GROUPE

    private ObservableList<Groupe> getAllGroupes(){
        ObservableList<Groupe> groupes = allGroupes;

        groupes.add(new Groupe("A", "M1", 15));
        groupes.add(new Groupe("B", "M1", 20));
        groupes.add(new Groupe("C", "M1", 30));
        groupes.add(new Groupe("A", "L1", 60));
        return groupes;
    }

    public void deleteGroupeBtnClicked() {
        ObservableList<Groupe> groupesSelected, groupes;
        groupesSelected = tableGroupe.getSelectionModel().getSelectedItems();

        groupes = tableGroupe.getItems();

        groupesSelected.forEach(groupes::remove);
    }

    public void updateGroupeBtnClicked(){
        int position;
        ObservableList<Groupe> allGroupes;
        Groupe updateGroupe = new Groupe();
        allGroupes = tableGroupe.getItems();
        position = tableGroupe.getSelectionModel().getSelectedIndex();

        updateGroupe.setTitre(txtTitreGroupe.getText());
        updateGroupe.setNiveau(comboNiveauGroupe.getSelectionModel().getSelectedItem().toString());
        updateGroupe.setCapacite(Integer.parseInt(txtCapaciteGroupe.getText()));

        allGroupes.set(position, updateGroupe);

    }


    private void searchGroupe(){
        //FILTER
        FilteredList<Section> filteredLocal = new FilteredList<>(allSections, p -> true);

        txtRechercherSection.textProperty().addListener((observable, oldValue, newValue) ->
                filteredLocal.setPredicate( section -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (section.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                })
        );

        SortedList<Section> sortedData = new SortedList<>(filteredLocal);
        sortedData.comparatorProperty().bind(tableSection.comparatorProperty());
        tableSection.setItems(sortedData);
    }

    public void addGroupeBtnClicked(){
        Groupe groupe = new Groupe();

        groupe.setTitre(txtTitreGroupe.getText());
        groupe.setNiveau(comboNiveauGroupe.getSelectionModel().getSelectedItem().toString());
        groupe.setCapacite(Integer.parseInt(txtCapaciteGroupe.getText()));

        allGroupes.add(groupe);
        tableGroupe.getItems().add(groupe);
    }
    /*************************************************************************************************----------------------****/////

    //Bouton menu


    public void enseignantLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/enseignant.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }

    public void moduleLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/module.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }


    public void localLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/local.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }


    public void groupeLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/groupe.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Sections
        columnTitreSection.setCellValueFactory(new PropertyValueFactory<>("titre"));
        columnSpecialiteSection.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        columnNiveauSection.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        tableSection.setItems(getAllSections());
        tableSection.getColumns().addAll(columnTitreSection, columnSpecialiteSection, columnNiveauSection);


        comboSpecialiteSection.getItems().addAll(
                "Genie Logiciel",
                "ASR",
                "IA",
                "RESYD"
        );

        comboNiveauSection.getItems().addAll(
                "L1",
                "L2",
                "L3"
        );

        columnTitreSpecialite.setCellValueFactory(new PropertyValueFactory<>("titre"));
        columnCapaciteSpecialite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        columnNiveauSpecialite.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        tableSpecialite.getItems().addAll(getAllSpecialites());
        tableSpecialite.getColumns().addAll(columnNiveauSpecialite, columnTitreSpecialite, columnCapaciteSpecialite);

        comboNiveauSpecialite.getItems().addAll(
                "L1",
                "L2",
                "L3"
        );


        /***----------------------------------------------------------------------------------------------------**/
        //GROUPES

        columnTitreGroupe.setCellValueFactory(new PropertyValueFactory<>("titre"));
        columnCapaciteGroupe.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        columnNiveauGroupe.setCellValueFactory(new PropertyValueFactory<>("niveau"));


        tableGroupe.getItems().addAll(getAllGroupes());
        tableGroupe.getColumns().addAll(columnNiveauGroupe, columnTitreGroupe, columnCapaciteGroupe);


        comboNiveauGroupe.getItems().addAll(
                "L1",
                "L2",
                "L3"
        );

    }
}