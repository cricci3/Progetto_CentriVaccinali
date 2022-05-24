package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.*;
import java.rmi.registry.*;
import java.util.*;

public class ControllerAddCentroVaccinale implements Initializable{
    private Parent root;
    private Scene scene;
    private Stage stage;
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
        String nomeCV = tf_nomeCV.getText().replaceAll(" ","").toLowerCase();
        String indirizzoCV = tf_indirizzoCV.getText().toLowerCase();
        String comune = tf_comuneCV.getText().toLowerCase();
        String provincia = tf_provinciaCV.getText().toLowerCase();
        int cap = Integer.parseInt(tf_capCV.getText());
        String tipologia = cb_tipoCV.getValue().toLowerCase();


        if (!nomeCV.equals("") && !indirizzoCV.equals("") &&!comune.equals("") &&!provincia.equals("") && !tipologia.equals("")){
            CentroVaccinale nuovoCentro = new CentroVaccinale(nomeCV, indirizzoCV, comune, provincia, cap, tipologia);

            try {
                Registry registro = LocateRegistry.getRegistry(1099);
                InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

                boolean response = stub.addCentroVaccinale(nuovoCentro);
                if(response){
                    lb_centro.setText("Centro aggiunto correttamente");
                }else{
                    lb_centro.setText("ERRORE, riprovare");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            lb_centro.setText("Inserire tutti i dati");
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
