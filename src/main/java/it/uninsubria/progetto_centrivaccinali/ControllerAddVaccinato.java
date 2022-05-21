package it.uninsubria.progetto_centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.rmi.registry.*;
import java.util.ResourceBundle;

public class ControllerAddVaccinato implements Initializable {
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
        String nome = tf_nome.getText();
        String cognome = tf_cognome.getText();
        String nomeCV = tf_nomecentrovaccinale.getText().replaceAll(" ","");
        String cf = tf_codicefiscale.getText();
        String datavaccinazione = dp_datavaccinazione.getValue().toString();
        String nomeVaccino = cb_vaccino.getValue();
        int id=0;
        do {
            id= IdGenerator.generateUniqueId();
        }while(id>99999999 && id<10000000);

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
            } else {
                lbl_addVaccinato.setText("ERRORE, riprovare");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
