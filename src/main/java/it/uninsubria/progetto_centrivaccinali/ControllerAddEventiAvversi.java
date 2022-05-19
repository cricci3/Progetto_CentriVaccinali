package it.uninsubria.progetto_centrivaccinali;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class ControllerAddEventiAvversi {
    @FXML
    private TextField tf_mdt, tf_febbre, tf_linfo, tf_crisi, tf_dolori, tf_tachi;
    @FXML
    private TextArea tf_noteMdt, tf_noteFebbre, tf_noteLinfo, tf_noteCrisi, tf_noteDolori, tf_noteTachi;
    @FXML
    private Label lb_errore;

    public void inviaDati(ActionEvent actionEvent) {
    }
}
