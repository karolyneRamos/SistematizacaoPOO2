module br.uniceub.contatos.sistematizacaopoo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.uniceub.contatos.sistematizacaopoo2 to javafx.fxml;
    exports br.uniceub.contatos.sistematizacaopoo2;
}