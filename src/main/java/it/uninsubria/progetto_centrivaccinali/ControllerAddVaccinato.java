package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.rmi.registry.*;
import java.util.*;

public class ControllerAddVaccinato implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private ChoiceBox<String> cb_vaccino;
    @FXML
    private TextField tf_nome, tf_cognome, tf_nomecentrovaccinale, tf_codicefiscale;
    @FXML
    private DatePicker dp_datavaccinazione;
    private String[] vaccini = {"Pfizer", "Moderna", "AstraZeneca", "Johnson&Johnson"};
    @FXML
    private Label lbl_addVaccinato, lb_id;

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

        if(cf.length()!=16 || nome.equals("") || cognome.equals("") || nomeCV.equals("") || nomeVaccino.equals("")){
            lbl_addVaccinato.setText("ERRORE, Inserire i dati correttamente");
        }else {
            CittadinoVaccinato nuovoVaccinato = new CittadinoVaccinato(nomeCV, id, nome, cognome, cf, datavaccinazione, nomeVaccino);

            try {
                Registry registro = LocateRegistry.getRegistry(1099);
                InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

                boolean response = stub.addCittadinoVaccinato(nuovoVaccinato);

                if (response) {
                    lbl_addVaccinato.setText("Vaccinato aggiunto correttamente");
                    lb_id.setText("ID cittadino: " + id);
                } else {
                    lbl_addVaccinato.setText("ERRORE, riprovare");
                }
            } catch (Exception e) {
                e.printStackTrace();
                lbl_addVaccinato.setText("ERRORE, riprovare");
            }
        }
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
