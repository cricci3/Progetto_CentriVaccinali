package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ControllerAddEventiAvversi {
    @FXML
    private TextField tf_mdt, tf_febbre, tf_linfo, tf_crisi, tf_dolori, tf_tachi, tf_id, tf_centrovaccinale;
    @FXML
    private TextArea tf_noteMdt, tf_noteFebbre, tf_noteLinfo, tf_noteCrisi, tf_noteDolori, tf_noteTachi;
    @FXML
    private Label lb_errore;

    public void inviaDati(ActionEvent actionEvent) {
        int mdt = Integer.parseInt(tf_mdt.getText());
        int febbre = Integer.parseInt(tf_febbre.getText());
        int linfo = Integer.parseInt(tf_linfo.getText());
        int crisi = Integer.parseInt(tf_crisi.getText());
        int dolori = Integer.parseInt(tf_dolori.getText());
        int tachi = Integer.parseInt(tf_tachi.getText());
        int id = Integer.parseInt(tf_id.getText());
        String cv = tf_centrovaccinale.getText();
        String noteMdt = tf_noteMdt.getText();
        String noteFebbre = tf_noteFebbre.getText();
        String noteLinfo = tf_noteLinfo.getText();
        String noteCrisi = tf_noteCrisi.getText();
        String noteDolori = tf_noteDolori.getText();
        String noteTachi = tf_noteTachi.getText();

        EventiAvversi nuovoEventiavversi = new EventiAvversi(id, cv, mdt, noteMdt, febbre, noteFebbre, dolori,
                noteDolori, linfo, noteLinfo, tachi, noteTachi, crisi, noteCrisi);

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            boolean response = stub.addEventiAvversi(nuovoEventiavversi);
            if(response){
                lb_errore.setText("Eventi avversi aggiunti correttamente");
            }else{
                lb_errore.setText("ERRORE, riprovare");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
