module src.breakouttec {
    requires javafx.controls;
    requires javafx.fxml;


    opens src.breakouttec to javafx.fxml;
    exports src.breakouttec;
}