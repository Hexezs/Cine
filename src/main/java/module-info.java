module com.ites.proyectotemplate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.Cine to javafx.fxml;
    exports com.Cine;
    exports com.Cine.controllers;
    exports com.Cine.models;
    opens com.Cine.controllers to javafx.fxml;
    opens com.Cine.models to javafx.fxml, org.hibernate.orm.core;
}