module com.example.aminhosseintehrani_comp228_sec401_testfall2022 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.sql.rowset;

    opens com.example.aminhosseintehrani_comp228_sec401_testfall2022 to javafx.fxml;
    exports com.example.aminhosseintehrani_comp228_sec401_testfall2022;
}