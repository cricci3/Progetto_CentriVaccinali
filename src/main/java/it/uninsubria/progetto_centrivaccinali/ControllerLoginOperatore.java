package it.uninsubria.progetto_centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ControllerLoginOperatore {
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
            } else lb_control.setText("ERRORE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
