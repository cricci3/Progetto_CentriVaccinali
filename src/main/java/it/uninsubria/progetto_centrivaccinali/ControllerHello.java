package it.uninsubria.progetto_centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.net.*;

/**
 * ControllerHello
 */
public class ControllerHello {
    /**
     * root
     */
    private Parent root;
    /**
     * scene
     */
    private Scene scene;
    /**
     * stage
     */
    private Stage stage;

    /**
     * Metodo che permette di passare alla pagina dell'interfaccia grafica "login-operatore"
     * @param event event
     */
    @FXML
    protected void switchToOperatore(ActionEvent event){
        String sceneFile = "login-operatore.fxml";
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

    /**
     * Metodo che permette di passare alla pagina dell'interfaccia grafica "hello-cittadino"
     * @param event event
     */
    @FXML
    protected void switchToCittadino(ActionEvent event){
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