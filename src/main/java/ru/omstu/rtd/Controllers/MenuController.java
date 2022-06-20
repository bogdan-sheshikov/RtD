package ru.omstu.rtd.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import ru.omstu.rtd.GameUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public Slider hardSlider;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button startBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private void startBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("choose-view.fxml"))));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        GameUtils.setHard(51 + hardSlider.getValue());
    }
    @FXML
    private void exitBtnAction(ActionEvent event) {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}