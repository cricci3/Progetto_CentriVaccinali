package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.rmi.registry.*;

public class ControllerLoginCittadino {
    @FXML
    private TextField tf_usernameLogin;
    @FXML
    private PasswordField pf_pswLogin;
    @FXML
    private Label lb_erroreLogin;

    private Parent root;
    private Scene scene;
    private Stage stage;


    public void loginCittadino(ActionEvent event) {
        String username = tf_usernameLogin.getText();
        String password = pf_pswLogin.getText();

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            /*
            boolean result = stub.loginCittadino(username, password);

            if(result){
                lb_erroreLogin.setText("TUTTO OK");

                String sceneFile = "add-eventi-avversi.fxml";
                URL url = getClass().getResource(sceneFile);
                try {
                    FXMLLoader fxmlLoader;
                    fxmlLoader= new FXMLLoader(url);
                    root = fxmlLoader.load();
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

            } else{
                lb_erroreLogin.setText("Credenziali sbagliate, riprovare");
            }
             */


            int id = stub.loginCittadinoID(username, password);
            if(id==0){
                lb_erroreLogin.setText("Credenziali sbagliate, riprovare");
            }else{
                lb_erroreLogin.setText(String.valueOf(id));

                /*
                String sceneFile = "add-eventi-avversi.fxml";
                URL url = getClass().getResource(sceneFile);
                try {
                    FXMLLoader fxmlLoader;
                    fxmlLoader= new FXMLLoader(url);
                    root = fxmlLoader.load();
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
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
