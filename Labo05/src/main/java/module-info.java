module log.labo.labo05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens log.labo.labo05 to javafx.fxml;
    exports log.labo.labo05;
}