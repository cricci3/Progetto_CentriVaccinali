package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.rmi.registry.*;

public class ControllerLoginOperatore {
    @FXML
    private Parent root;
    @FXML
    private TextField tf_username, pf_password;
    @FXML
    private Label lb_control;

    public void logIn(ActionEvent event){
        String username = tf_username.getText();
        String password = pf_password.getText();

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            boolean result = stub.loginOperatore(username, password);

            if(result){
                lb_control.setText("TUTTO OK");

                String sceneFile = "hello-operatore.fxml";
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

            } else{
                lb_control.setText("ERRORE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
