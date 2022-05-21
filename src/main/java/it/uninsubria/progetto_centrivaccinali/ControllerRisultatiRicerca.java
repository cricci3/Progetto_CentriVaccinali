package it.uninsubria.progetto_centrivaccinali;

import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.util.*;

public class ControllerRisultatiRicerca implements Initializable {
    @FXML
    private ListView<String> lv_centri;

    public static ArrayList<String> listaNomi = new ArrayList<>();


    public void caricaRisultati(ArrayList<CentroVaccinale> listaRisultato) {
        for (CentroVaccinale centro : listaRisultato) {
            listaNomi.add("Nome: "+centro.getNome()+"\nComune: "+centro.getComune()+" ("+centro.getProvincia()+") ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_centri.getItems().addAll(listaNomi);


    }
}
