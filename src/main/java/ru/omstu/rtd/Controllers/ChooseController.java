package ru.omstu.rtd.Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.omstu.rtd.Entities.Hero;
import ru.omstu.rtd.GameUtils;
import ru.omstu.rtd.Entities.Monster;
import ru.omstu.rtd.RtD;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChooseController implements Initializable {
    public Text HpCounter;
    public Text LvlCounter;
    public ImageView background;
    Stage stage;
    Scene scene;
    @FXML
    public ImageView leftDoor;
    @FXML
    public ImageView rightDoor;

    private void chooseDoor(Event event) throws IOException {
        Parent root;
        if (GameUtils.getRandRoom().getClass() == Monster.class ) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fight-view.fxml")));
        } else {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("treasure-view.fxml")));
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void leftDoorClicked(MouseEvent event) throws IOException {
        chooseDoor(event);
    }

    @FXML
    private void rightDoorClicked(MouseEvent event) throws IOException {
        chooseDoor(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HpCounter.setText(String.valueOf(Hero.health));
        LvlCounter.setText(String.valueOf(Hero.xpLevel));
        Image doors;
        Image backgroundImg;
        if (Hero.xpLevel > 30) {
            backgroundImg = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/background.jpg")));
            doors = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/newDungeonDoor.png")));
        } else {
            backgroundImg = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/background.jpg")));
            doors = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/door.png")));
        }
        background.setImage(backgroundImg);
        leftDoor.setImage(doors);
        rightDoor.setImage(doors);
    }

}
