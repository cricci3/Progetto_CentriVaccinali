package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.util.*;

public class ControllerHelloInformazioni implements Initializable{
    @FXML
    private TextField tf_nome, tf_comune;

    @FXML
    private ChoiceBox<String> cb_tipo;
    private final String[] tipologia = {"ospedale","hub","aziendale"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_tipo.getItems().addAll(tipologia);
    }


    public void cercaNomeCentro(ActionEvent event) {
    }

    public void cercaTipoComuneCentro(ActionEvent event) {
    }
}
