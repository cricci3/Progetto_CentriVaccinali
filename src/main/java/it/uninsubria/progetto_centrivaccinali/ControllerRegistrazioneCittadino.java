package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.rmi.registry.*;

public class ControllerRegistrazioneCittadino {
    @FXML
    private TextField tf_nomeReg, tf_cognomeReg, tf_idReg, tf_nomeCVReg,
            tf_emailReg, tf_usernameReg;
    @FXML
    private PasswordField pf_passwordReg;
    @FXML
    private Label lb_erroreReg;


    public void registrazioneCittadino(ActionEvent actionEvent) {
        int id = (int)Integer.parseInt(tf_idReg.getText());
        String nomeCV = tf_nomeCVReg.getText();
        String nome = tf_nomeReg.getText();
        String cognome = tf_cognomeReg.getText();
        String email = tf_emailReg.getText();
        String username = tf_usernameReg.getText();
        String password = pf_passwordReg.getText();

        if(password.length()<8){
            lb_erroreReg.setText("Password deve avere almeno 8 caratteri");
        }else{
            try {
                Registry registro = LocateRegistry.getRegistry(1099);
                InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");
                boolean result = stub.controlloRegistrazione(id);
                if(result){
                    result = stub.addCittadinoRegistrato2(id, nomeCV, nome, cognome, email, username, password);
                    if(result){
                        lb_erroreReg.setText("Registrazione effettuata con successo");
                    }else{
                        lb_erroreReg.setText("ERRORE");
                    }
                }else{
                    lb_erroreReg.setText("A questo ID corrisponde un cittadino già registrato");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
