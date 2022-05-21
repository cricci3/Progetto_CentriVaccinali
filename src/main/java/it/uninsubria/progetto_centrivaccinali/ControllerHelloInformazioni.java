package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.rmi.registry.*;
import java.util.*;

public class ControllerHelloInformazioni implements Initializable{
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField tf_nome, tf_comune;

    @FXML
    private Label lb_erroreNome, lb_erroreTipoComune;

    @FXML
    private ChoiceBox<String> cb_tipo;
    private final String[] tipologia = {"ospedale","hub","aziendale"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_tipo.getItems().addAll(tipologia);
    }


    public void cercaNomeCentro(ActionEvent event) {
        String nomeCV = tf_nome.getText().toLowerCase();

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");
            ArrayList<CentroVaccinale> listaRisultato = stub.cercaCentroVaccinale(nomeCV);

            if(!listaRisultato.isEmpty()) {
                System.out.println(listaRisultato);
                ControllerRisultatiRicerca ricerca = new ControllerRisultatiRicerca();
                ricerca.caricaRisultati(listaRisultato);

                String sceneFile = "risultati-ricerca.fxml";
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


            }else{
                lb_erroreNome.setText("Nessun centro vaccinale trovato");
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void cercaTipoComuneCentro(ActionEvent event) {
        String comune = tf_comune.getText().toLowerCase();
        String tipo = cb_tipo.getValue();

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");
            ArrayList<CentroVaccinale> listaRisultato = stub.cercaCentroVaccinale(comune,tipo);

            if(!listaRisultato.isEmpty()) {
                System.out.println(listaRisultato);
                ControllerRisultatiRicerca ricerca = new ControllerRisultatiRicerca();
                ricerca.caricaRisultati(listaRisultato);

                String sceneFile = "risultati-ricerca.fxml";
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
            }else{
                lb_erroreTipoComune.setText("Nessun centro vaccinale trovato");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void back(ActionEvent event){
        String sceneFile = "hello-cittadino.fxml";
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
