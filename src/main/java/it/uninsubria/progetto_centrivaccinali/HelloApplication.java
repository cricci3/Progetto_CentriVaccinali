package it.uninsubria.progetto_centrivaccinali;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

/**
 * @author Macrina Alessandro mtr. 737128 VA
 * @author Ricci Claudio mtr. 747555 VA
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Centri Vaccinali");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo-centri-vaccinali.png")));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}