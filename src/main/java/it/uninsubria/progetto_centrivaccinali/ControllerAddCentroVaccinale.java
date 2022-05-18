package it.uninsubria.progetto_centrivaccinali;

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

    private String[] tipologia = {"ospedale","hub","aziendale"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_tipoCV.getItems().addAll(tipologia);
    }
}
