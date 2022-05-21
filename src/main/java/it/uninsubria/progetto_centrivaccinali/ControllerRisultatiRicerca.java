package it.uninsubria.progetto_centrivaccinali;

import javafx.beans.value.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.rmi.registry.*;
import java.util.*;

public class ControllerRisultatiRicerca implements Initializable {
    @FXML
    private ListView<String> lv_centri;
    @FXML
    private Label lb_infoCentro;

    public static ArrayList<String> listaCentri = new ArrayList<>();


    public void caricaRisultati(ArrayList<CentroVaccinale> listaRisultato) {
        for (CentroVaccinale centro : listaRisultato) {
            listaCentri.add(centro.getNome());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_centri.getItems().addAll(listaCentri);

        lv_centri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String nomeCV = lv_centri.getSelectionModel().getSelectedItem();
                System.out.println(nomeCV);

                try {
                    Registry registro = LocateRegistry.getRegistry(1099);
                    InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");
                    ArrayList<CentroVaccinale> centroVaccinale = stub.getInfoCentroVaccinale(nomeCV);
                    CentroVaccinale centro = centroVaccinale.get(0);

                    ArrayList<Double> medieCV = stub.getMedieCentroVaccinale(nomeCV);
                    String info = "Nome: "+centro.getNome()+"\n"+
                                    "Indirizzo: "+centro.getIndirizzo()+", "+centro.getComune()+" ("+centro.getProvincia()+"), "+centro.getCap()+"\n"+
                                    "Tipologia: "+centro.getTipologia()+"\n"+
                                    "Febbre: "+medieCV.get(0)+
                                    "\nMal di testa: "+medieCV.get(1)+
                                    "\nDolori Muscolari: "+medieCV.get(2)+
                                    "\nLinfoadenopatia: "+medieCV.get(3)+
                                    "\nTachicardia: "+medieCV.get(4)+
                                    "\nCrisi ipertensive: "+medieCV.get(5);
                    lb_infoCentro.setText(info);

                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        });
    }
}
