module com.mycompany.algoritmofloyd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.algoritmofloyd to javafx.fxml;
    exports com.mycompany.algoritmofloyd;
}
