package it.uninsubria.progetto_centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.net.*;

/**
 * ControllerHelloOperatore
 * @author Claudio Ricci mtr. 747555, VA
 * @author Alessandro Macrina mtr. 737128, VA
 */
public class ControllerHelloOperatore {
    /**
     * scene
     */
    private Scene scene;
    /**
     * stage
     */
    private Stage stage;
    /**
     * root
     */
    private Parent root;

    /**
     * Metodo che permette di passare alla pagina della GUI "add-centro-vaccinale"
     * @param event event
     */
    @FXML
    protected void switchToAddCentroVaccinale(ActionEvent event){
        String sceneFile = "add-centro-vaccinale.fxml";
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
     * Metodo che permette di passare alla pagina della GUI "add-vaccinato"
     * @param event event
     */
    @FXML
    protected void switchToAddVaccinato(ActionEvent event){
        String sceneFile = "add-vaccinato.fxml";
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
            e.printStackTrace();
        }
    }

    /**
     * Metodo che permette di passare alla pagina precedente dell'interfaccia grafica
     * @param event event
     */
    public void back(ActionEvent event){
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
}
