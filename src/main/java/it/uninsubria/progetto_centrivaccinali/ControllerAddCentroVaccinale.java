package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.util.*;

public class ControllerAddCentroVaccinale implements Initializable{
    @FXML
    private Button btn_addCV;
    @FXML
    private TextField tf_nomeCV, tf_indirizzoCV, tf_comuneCV, tf_capCV, tf_provinciaCV;
    @FXML
    private ChoiceBox<String> cb_tipoCV;
    @FXML
    private Label lb_centro;

    private final String[] tipologia = {"ospedale","hub","aziendale"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_tipoCV.getItems().addAll(tipologia);
    }

    public void addCentroVaccinale(ActionEvent actionEvent) {
        String nomeCV = tf_nomeCV.getText();
        String indirizzoCV = tf_indirizzoCV.getText();
        String comune = tf_comuneCV.getText();
        String provincia = tf_provinciaCV.getText();
        int cap = Integer.parseInt(tf_capCV.getText());
        String tipologia = cb_tipoCV.getValue();

        lb_centro.setText("Aggiunto nuovo centro vaccinale: "+nomeCV+", "+indirizzoCV+", "+comune+", "+provincia+", "+cap);
        CentroVaccinale nuovoCentro = new CentroVaccinale(nomeCV, indirizzoCV, comune, provincia, cap, tipologia);
    }
}
