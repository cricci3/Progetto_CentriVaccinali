/**
 * File moduleinfo
 */
module it.uninsubria.progetto_centrivaccinali {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.rmi;


    opens it.uninsubria.progetto_centrivaccinali to javafx.fxml;
    exports it.uninsubria.progetto_centrivaccinali;
}