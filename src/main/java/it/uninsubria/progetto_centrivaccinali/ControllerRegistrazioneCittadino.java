package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.rmi.registry.*;

public class ControllerRegistrazioneCittadino {
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
    @FXML
    private TextField tf_nomeReg, tf_cognomeReg, tf_idReg, tf_nomeCVReg,
            tf_emailReg, tf_usernameReg;
    @FXML
    private PasswordField pf_passwordReg;
    @FXML
    private Label lb_erroreReg;


    /**
     * Metodo che permette di invocare il metodo "controlloRegistrazione" del server
     * @param event
     */
    public void registrazioneCittadino(ActionEvent event) {
        int id = (int)Integer.parseInt(tf_idReg.getText());
        String nomeCV = tf_nomeCVReg.getText().replaceAll(" ","").toLowerCase();
        String nome = tf_nomeReg.getText().toLowerCase();
        String cognome = tf_cognomeReg.getText().toLowerCase();
        String email = tf_emailReg.getText().toLowerCase();
        String username = tf_usernameReg.getText().toLowerCase();
        String password = pf_passwordReg.getText();

        if(username.equals("")){
            lb_erroreReg.setText("Inserire uno username");
        }else if(password.length()<8){
            lb_erroreReg.setText("Password deve avere almeno 8 caratteri");
        }else{
            try {
                Registry registro = LocateRegistry.getRegistry(1099);
                InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

                boolean result = stub.controlloRegistrazione(id, nomeCV);
                //se result == false non è ancora registrato e può farlo
                if(!result){
                    //serve ora controllare se a id corrisponde un cittadino vaccinato
                    result = stub.addCittadinoRegistrato(id, nomeCV, nome, cognome, email, username, password);
                    if(result){
                        lb_erroreReg.setText("Registrazione effettuata con successo");

                        String sceneFile = "hello-eventi-avversi.fxml";
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
                        lb_erroreReg.setText("A questo ID non corrisponde nessun cittadino vaccinato");
                    }
                }else{
                    lb_erroreReg.setText("A questo ID corrisponde un cittadino già registrato");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo che permette di passare alla pagina precedente dell'interfaccia grafica
     * @param event
     */
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
