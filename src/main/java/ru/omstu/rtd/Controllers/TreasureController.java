package ru.omstu.rtd.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.omstu.rtd.Entities.Hero;
import ru.omstu.rtd.Entities.Treasure;
import ru.omstu.rtd.GameUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TreasureController implements Initializable {

    public ImageView chest;
    public Label treasure;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/chest.png")));
        chest.setImage(img);
        treasure.setText(GameUtils.getCurrentRoom().toString());
        Treasure treasure = (Treasure) GameUtils.getCurrentRoom();
        Hero.inventory.addAll(treasure.items);
    }

    public void chestClick(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/ru/omstu/rtd/Controllers/choose-view.fxml"))));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
