package it.uninsubria.progetto_centrivaccinali;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.net.*;

public class ControllerHelloOperatore {
    @FXML
    private Parent root;
    @FXML
    protected void switchToAddCentroVaccinale(){
        String sceneFile = "add-centro-vaccinale.fxml";
        URL url = getClass().getResource(sceneFile);
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader= new FXMLLoader(url);
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
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
