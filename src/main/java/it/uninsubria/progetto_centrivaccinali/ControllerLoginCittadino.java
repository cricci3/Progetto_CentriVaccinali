package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.net.*;
import java.rmi.registry.*;

/**
 * ControllerLoginCittadino
 */
public class ControllerLoginCittadino {
    @FXML
    private TextField tf_usernameLogin;
    @FXML
    private PasswordField pf_pswLogin;
    @FXML
    private Label lb_erroreLogin;

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
     * Metodo che permette di invocare il metodo "loginCittadino" del server per poter effetuare l'accesso al sistema, da parte di un cittadino
     * @param event event
     */
    public void loginCittadino(ActionEvent event) {
        String username = tf_usernameLogin.getText();
        String password = pf_pswLogin.getText();

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            int id = stub.loginCittadino(username, password);
            if(id==0){
                lb_erroreLogin.setText("Credenziali sbagliate, riprovare");
            }else{
                lb_erroreLogin.setText(String.valueOf(id));

                String sceneFile = "add-eventi-avversi.fxml";
                URL url = getClass().getResource(sceneFile);
                try {
                    FXMLLoader fxmlLoader;
                    fxmlLoader= new FXMLLoader(url);
                    root = fxmlLoader.load();

                    ControllerAddEventiAvversi controller2 = fxmlLoader.getController();
                    controller2.getId(id);

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch (Exception e) {
                    System.out.println("Exception on FXMLLoader.load()");
                    System.out.println("  * url: " + url);
                    System.out.println("  * " + e);
                    System.out.println("    ----------------------------------------\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che permette di passare alla pagina precedente dell'interfaccia grafica
     * @param event event
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
