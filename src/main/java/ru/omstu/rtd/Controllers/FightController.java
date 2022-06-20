package ru.omstu.rtd.Controllers;

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
import ru.omstu.rtd.Entities.Hero;
import ru.omstu.rtd.Entities.Monster;
import ru.omstu.rtd.GameUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class FightController implements Initializable {
    public Text heroHpCounter;
    public ImageView background;
    public ImageView slot1;
    public ImageView slot2;
    public ImageView slot3;
    public ImageView slot4;
    public ImageView slot5;
    public ImageView slot6;
    Stage stage;
    Scene scene;
    Monster currentMonster;
    private Image monster;
    private Image section;
    public ImageView monsterImage;
    public ImageView headTarget;
    public ImageView bodyTarget;
    public ImageView legsTarget;


    public void headClicked(MouseEvent mouseEvent) throws IOException {
        if (currentMonster.hitChance[0] > Math.random()) {
            currentMonster.takeDamage((int) (Hero.attackDamage * (2 - currentMonster.hitChance[0])));
        }
        currentMonster.takeDamage(Hero.attackDamage);
        updateGame(mouseEvent);
        isAlive(mouseEvent);
    }

    public void bodyClicked(MouseEvent mouseEvent) throws IOException {
        if (currentMonster.hitChance[1] > Math.random()) {
            currentMonster.takeDamage((int) (Hero.attackDamage * (2 - currentMonster.hitChance[0])));
        }
        currentMonster.takeDamage(Hero.attackDamage);
        currentMonster.takeDamage(Hero.attackDamage);
        updateGame(mouseEvent);
        isAlive(mouseEvent);

    }

    public void legsClicked(MouseEvent mouseEvent) throws IOException {
        if (currentMonster.hitChance[2] > Math.random()) {
            currentMonster.takeDamage((int) (Hero.attackDamage * (2 - currentMonster.hitChance[0])));
        }
        currentMonster.takeDamage(Hero.attackDamage);
        currentMonster.takeDamage(Hero.attackDamage);
        updateGame(mouseEvent);
        isAlive(mouseEvent);

    }

    void updateGame(MouseEvent event) throws IOException {
        Hero.takeDamage(currentMonster.attackDamage);
        System.out.println(currentMonster.attackDamage);
        heroHpCounter.setText(String.valueOf(Hero.health));
        // check Hero is alive
        if (Hero.health <= 0) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game-over-view.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
    }
    void isAlive(MouseEvent event) throws IOException {
        if (GameUtils.currentIndexMap >= 19){
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("happy-end-view.fxml")));
                scene = new Scene(root);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!currentMonster.alive && Hero.health > 0) {
            Hero.health += currentMonster.health/10;
            Hero.getXp(currentMonster.attackDamage);
            Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/ru/omstu/rtd/Controllers/choose-view.fxml"))));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    public void drawPotions() {
        Image potion = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/potion.png")));
        if (Hero.inventory.size() > 0) {
            slot1.setImage(potion);
            if (Hero.inventory.size() > 1) {
                slot2.setImage(potion);
                if (Hero.inventory.size() > 2) {
                    slot3.setImage(potion);
                    if (Hero.inventory.size() > 3) {
                        slot4.setImage(potion);
                        if (Hero.inventory.size() > 4) {
                            slot5.setImage(potion);
                            if (Hero.inventory.size() > 5) {
                                slot6.setImage(potion);
                            } else {
                                slot6.setImage(null);
                            }
                        } else {
                            slot5.setImage(null);
                            slot6.setImage(null);
                        }
                    } else {
                        slot4.setImage(null);
                        slot5.setImage(null);
                        slot6.setImage(null);
                    }
                } else {
                    slot3.setImage(null);
                    slot4.setImage(null);
                    slot5.setImage(null);
                    slot6.setImage(null);
                }
            } else {
                slot2.setImage(null);
                slot3.setImage(null);
                slot4.setImage(null);
                slot5.setImage(null);
                slot6.setImage(null);
            }
        } else {
            slot1.setImage(null);
            slot2.setImage(null);
            slot3.setImage(null);
            slot4.setImage(null);
            slot5.setImage(null);
            slot6.setImage(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drawPotions();

        heroHpCounter.setText(String.valueOf(Hero.health));
        currentMonster = (Monster) GameUtils.getCurrentRoom();
        Image backgroundImg;
        if (Hero.xpLevel > 30) {
            monster = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/monster2.png")));
            backgroundImg = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/background.jpg")));
        } else {
            monster = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/monster1.png")));
            backgroundImg = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/background.jpg")));
        }
        background.setImage(backgroundImg);
        section = new Image(String.valueOf(getClass().getResource("/ru/omstu/rtd/assets/section.png")));
        monsterImage.setImage(monster);
        headTarget.setImage(section);
        bodyTarget.setImage(section);
        legsTarget.setImage(section);
    }

    public void slot1Click(MouseEvent mouseEvent) throws IOException {
        if (Hero.inventory.size() > 0) {
            Hero.usePotion(0);
            heroHpCounter.setText(String.valueOf(Hero.health));
            drawPotions();
            updateGame(mouseEvent);
        }
    }

    public void slot2Click(MouseEvent mouseEvent) throws IOException {
        if (Hero.inventory.size() > 1) {
            Hero.usePotion(1);
            heroHpCounter.setText(String.valueOf(Hero.health));
            drawPotions();
            updateGame(mouseEvent);
        }
    }

    public void slot3Click(MouseEvent mouseEvent) throws IOException {
        if (Hero.inventory.size() > 2) {
            Hero.usePotion(2);
            heroHpCounter.setText(String.valueOf(Hero.health));
            drawPotions();
            updateGame(mouseEvent);
        }
    }

    public void slot4Click(MouseEvent mouseEvent) throws IOException {
        if (Hero.inventory.size() > 3) {
            Hero.usePotion(3);
            heroHpCounter.setText(String.valueOf(Hero.health));
            drawPotions();
            updateGame(mouseEvent);
        }
    }

    public void slot5Click(MouseEvent mouseEvent) throws IOException {
        if (Hero.inventory.size() > 4) {
            Hero.usePotion(4);
            heroHpCounter.setText(String.valueOf(Hero.health));
            drawPotions();
            updateGame(mouseEvent);
        }
    }

    public void slot6Click(MouseEvent mouseEvent) throws IOException {
        if (Hero.inventory.size() > 5) {
            Hero.usePotion(5);
            heroHpCounter.setText(String.valueOf(Hero.health));
            drawPotions();
            updateGame(mouseEvent);
        }
    }
}
