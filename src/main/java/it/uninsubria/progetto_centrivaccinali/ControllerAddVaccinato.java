package it.uninsubria.progetto_centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAddVaccinato implements Initializable {
    @FXML
    private ChoiceBox<String> cb_vaccino;
    @FXML
    private TextField tf_nome, tf_cognome, tf_nomecentrovaccinale, tf_codicefiscale;
    @FXML
    private DatePicker dp_datavaccinazione;
    private String[] vaccini = {"Pfizer", "Moderna", "AstraZeneca", "Johnson&Johnson"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_vaccino.getItems().addAll(vaccini);
    }

    @FXML
    public void addVaccinato(ActionEvent event){
        String nome = tf_nome.getText();
        String cognome = tf_cognome.getText();
        String nomecentrovaccinale = tf_nomecentrovaccinale.getText();
        String codicefiscale = tf_codicefiscale.getText();
        String datavaccinazione = dp_datavaccinazione.getValue().toString();
    }
}
