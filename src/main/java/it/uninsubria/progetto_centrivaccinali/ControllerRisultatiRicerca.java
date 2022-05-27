package it.uninsubria.progetto_centrivaccinali;

import javafx.beans.value.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.rmi.registry.*;
import java.util.*;

public class ControllerRisultatiRicerca implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private ListView<String> lv_centri;
    @FXML
    private Label lb_infoCentro;

    public static ArrayList<String> listaCentri = new ArrayList<>();

    /**
     * Metodo che permette di passare una lista di centri vaccinali dalla pagina precedente della GUI a quella gestita da questa classe
     * @param listaRisultato lista di oggetti tipo CentroVaccinale
     */
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

                    ArrayList<Double> medieCV = stub.visualizzaInfoCentroVaccinale(nomeCV);
                    String info = "Nome: "+centro.getNome()+"\n"+
                                    "Indirizzo: "+centro.getIndirizzo()+"\n"+
                                    "Comune: "+centro.getComune()+" ("+centro.getProvincia()+"), "+centro.getCap()+"\n"+
                                    "Tipologia: "+centro.getTipologia()+"\n\n"+
                                    "Medie degli eventi avversi\nrelativi a questo centro\n"+
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

    /**
     * Metodo che permette di passare alla pagina precedente dell'interfaccia grafica
     * @param event
     */
    public void back(ActionEvent event) {
        listaCentri.clear();
        String sceneFile = "hello-informazioni.fxml";
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
