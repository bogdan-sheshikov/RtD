module ru.omstu.rtd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens ru.omstu.rtd to javafx.fxml;
    exports ru.omstu.rtd;
    exports ru.omstu.rtd.Controllers;
    opens ru.omstu.rtd.Controllers to javafx.fxml;
    exports ru.omstu.rtd.Entities;
    opens ru.omstu.rtd.Entities to javafx.fxml;
}