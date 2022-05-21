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

    private int idCittadino;

    public void getId(int id) {
        idCittadino = id;
        lb_errore.setText(String.valueOf(idCittadino));
    }

    public void inviaDati(ActionEvent actionEvent) {
        int mdt = Integer.parseInt(tf_mdt.getText());
        int febbre = Integer.parseInt(tf_febbre.getText());
        int linfo = Integer.parseInt(tf_linfo.getText());
        int crisi = Integer.parseInt(tf_crisi.getText());
        int dolori = Integer.parseInt(tf_dolori.getText());
        int tachi = Integer.parseInt(tf_tachi.getText());
        int id = idCittadino;
        String cv = tf_centrovaccinale.getText().toLowerCase();
        String noteMdt = tf_noteMdt.getText().toLowerCase();
        String noteFebbre = tf_noteFebbre.getText().toLowerCase();
        String noteLinfo = tf_noteLinfo.getText().toLowerCase();
        String noteCrisi = tf_noteCrisi.getText().toLowerCase();
        String noteDolori = tf_noteDolori.getText().toLowerCase();
        String noteTachi = tf_noteTachi.getText().toLowerCase();

        EventiAvversi nuovoEventiavversi = new EventiAvversi(id, cv, febbre, noteFebbre, mdt, noteMdt, dolori,
                noteDolori, linfo, noteLinfo, tachi, noteTachi, crisi, noteCrisi);

        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            String response = stub.addEventiAvversi(nuovoEventiavversi);
            if(response.equals("ERRORE nel inserimento dei dati")){
                lb_errore.setText(response);
            }else if(response.equals("ERRORE, centro vaccinale inserito errato")){
                lb_errore.setText(response);
            }else if(response.equals("Dati inseriti correttamente")){
                lb_errore.setText(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
