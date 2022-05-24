package it.uninsubria.progetto_centrivaccinali;

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
import java.rmi.registry.*;
import java.util.ResourceBundle;

public class ControllerAddVaccinato implements Initializable {
    private Parent root, root_popup;
    private Scene scene, scene_popup;
    private Stage stage, stage_popup;
    @FXML
    private ChoiceBox<String> cb_vaccino;
    @FXML
    private TextField tf_nome, tf_cognome, tf_nomecentrovaccinale, tf_codicefiscale;
    @FXML
    private DatePicker dp_datavaccinazione;
    private String[] vaccini = {"Pfizer", "Moderna", "AstraZeneca", "Johnson&Johnson"};
    @FXML
    private Label lbl_addVaccinato;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_vaccino.getItems().addAll(vaccini);
    }

    @FXML
    public void addVaccinato(ActionEvent event){
        String nome = tf_nome.getText().toLowerCase();
        String cognome = tf_cognome.getText().toLowerCase();
        String nomeCV = tf_nomecentrovaccinale.getText().replaceAll(" ","").toLowerCase();
        String cf = tf_codicefiscale.getText().toLowerCase();
        String datavaccinazione = dp_datavaccinazione.getValue().toString().toLowerCase();
        String nomeVaccino = cb_vaccino.getValue().toLowerCase();
        int id=0;
        do {
            id= IdGenerator.generateUniqueId();
        }while(id>99999999 || id<=9999999);
        System.out.println(id);


        if(cf.length()!=16){
            lbl_addVaccinato.setText("Codice fiscale errato");
        }

        CittadinoVaccinato nuovoVaccinato = new CittadinoVaccinato(nomeCV,id,nome,cognome,cf,datavaccinazione,nomeVaccino);

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            boolean response = stub.addCittadinoVaccinato(nuovoVaccinato);

            if (response) {
                lbl_addVaccinato.setText("Vaccinato aggiunto correttamente");
                popupId(id);
            } else {
                lbl_addVaccinato.setText("ERRORE, riprovare");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void popupId(int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup_id.fxml"));
        root_popup = fxmlLoader.load();
        stage_popup = new Stage();
        scene_popup= new Scene(root_popup);
        stage_popup.setScene(scene_popup);
        stage_popup.show();
    }

    public void back(ActionEvent event){
        String sceneFile = "hello-operatore.fxml";
        URL url = getClass().getResource(sceneFile);
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader= new FXMLLoader(url);
            root = fxmlLoader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + e );
            System.out.println( "    ----------------------------------------\n" );
        }
    }
}
