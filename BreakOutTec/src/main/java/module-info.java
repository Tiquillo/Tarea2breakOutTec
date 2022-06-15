module src.breakouttec {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens src.breakouttec to javafx.fxml;
    exports src.breakouttec;
}