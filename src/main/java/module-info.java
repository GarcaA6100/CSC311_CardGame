module com.example.csc311_cardgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc311_cardgame to javafx.fxml;
    exports com.example.csc311_cardgame;
}