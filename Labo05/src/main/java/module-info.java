module log.labo.labo05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens log.labo.labo05 to javafx.fxml;
    exports log.labo.labo05;
    exports log.labo.labo05.Controllers;
    opens log.labo.labo05.Controllers to javafx.fxml;
    exports log.labo.labo05.Views;
    opens log.labo.labo05.Views to javafx.fxml;
    exports log.labo.labo05.Models;
    opens log.labo.labo05.Models to javafx.fxml;
}