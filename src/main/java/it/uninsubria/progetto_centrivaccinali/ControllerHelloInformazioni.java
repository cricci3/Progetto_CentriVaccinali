package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.util.*;

public class ControllerHelloInformazioni implements Initializable{
    private Parent root;
    private Scene scene;
    private Stage stage;
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
