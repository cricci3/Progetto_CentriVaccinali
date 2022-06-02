package it.uninsubria.progetto_centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ControllerAddEventiAvversi
 * @author Claudio Ricci mtr. 747555, VA
 * @author Alessandro Macrina mtr. 737128, VA
 */
public class ControllerAddEventiAvversi {
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
    private TextField tf_mdt, tf_febbre, tf_linfo, tf_crisi, tf_dolori, tf_tachi, tf_id, tf_centrovaccinale;
    @FXML
    private TextArea tf_noteMdt, tf_noteFebbre, tf_noteLinfo, tf_noteCrisi, tf_noteDolori, tf_noteTachi;
    @FXML
    private Label lb_errore;

    /**
     * id cittadino
     */
    private int idCittadino;

    /**
     * @param id id
     */
    public void getId(int id) {
        idCittadino = id;
        //lb_errore.setText(String.valueOf(idCittadino));
    }

    /**
     * Metodo che crea un oggetto della classe EventiAvversi, il quale viene poi mandato al server per poter essere inserito nel database
     */
    public void inviaDati() {
        int mdt, febbre,linfo,crisi,dolori,tachi,id;
        String cv, noteMdt, noteFebbre, noteDolori, noteLinfo, noteTachi, noteCrisi;
        mdt = Integer.parseInt(tf_mdt.getText());
        febbre = Integer.parseInt(tf_febbre.getText());
        linfo = Integer.parseInt(tf_linfo.getText());
        crisi = Integer.parseInt(tf_crisi.getText());
        dolori = Integer.parseInt(tf_dolori.getText());
        tachi = Integer.parseInt(tf_tachi.getText());
        id = idCittadino;
        cv = tf_centrovaccinale.getText().toLowerCase().replaceAll(" ", "");
        noteMdt = tf_noteMdt.getText().toLowerCase();
        noteFebbre = tf_noteFebbre.getText().toLowerCase();
        noteLinfo = tf_noteLinfo.getText().toLowerCase();
        noteCrisi = tf_noteCrisi.getText().toLowerCase();
        noteDolori = tf_noteDolori.getText().toLowerCase();
        noteTachi = tf_noteTachi.getText().toLowerCase();
        lb_errore.setText("Verificare che i dati siano compresi tra 1 e 5");




        if(mdt < 1 || mdt > 5 || febbre <1 || febbre >5 || linfo<1 || linfo >5 ||crisi<1||crisi>5||dolori<1||dolori>5||tachi<1||tachi>5){
            lb_errore.setText("Alcuni valori non sono compresi tra 1 e 5");
        }else if(noteMdt.length()>256 || noteFebbre.length()>256 || noteCrisi.length()>256||noteDolori.length()>256||noteLinfo.length()>256||noteTachi.length()>256){
            lb_errore.setText("Note troppo lunghe");
        }else {

            EventiAvversi nuovoEventiavversi = new EventiAvversi(id, cv, febbre, noteFebbre, mdt, noteMdt, dolori,
                    noteDolori, linfo, noteLinfo, tachi, noteTachi, crisi, noteCrisi);

            try {
                Registry registro = LocateRegistry.getRegistry(1099);
                InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

                String response = stub.addEventiAvversi(nuovoEventiavversi);
                if (response.equals("ERRORE nel inserimento dei dati")) {
                    lb_errore.setText(response);
                } else if (response.equals("ERRORE, centro vaccinale inserito errato")) {
                    lb_errore.setText(response);
                } else if (response.equals("Dati inseriti correttamente")) {
                    lb_errore.setText(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo che permette di passare a pagina precedente dell'interfaccia grafica
     * @param event event
     */
    public void back(ActionEvent event){
        String sceneFile = "login-cittadino.fxml";
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
