module it.uninsubria.progetto_centrivaccinali {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.uninsubria.progetto_centrivaccinali to javafx.fxml;
    exports it.uninsubria.progetto_centrivaccinali;
}