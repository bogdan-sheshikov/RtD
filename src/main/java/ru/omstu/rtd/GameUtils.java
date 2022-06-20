package ru.omstu.rtd;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import ru.omstu.rtd.Controllers.MenuController;
import ru.omstu.rtd.Entities.Entity;
import ru.omstu.rtd.Entities.Monster;
import ru.omstu.rtd.Entities.Treasure;
import ru.omstu.rtd.Items.HpPotion;

import java.io.IOException;
import java.util.*;

public class GameUtils {
    static int hardVal;
    private static final int[] randMap = new int[20];
    public static int currentIndexMap = 0;
    private static final ArrayList<Entity> gameMap = new ArrayList<>();
    private static final ArrayList<Entity> monstersList = new ArrayList<>();
    private static final ArrayList<Entity> treasureList = new ArrayList<>();

    public static void initMonsters() {
        Monster monster1 = new Monster(hardVal, 100, 122, 0.2, 0.5, 0.3);
        Monster monster2 = new Monster(hardVal, 123, 132, 0.2, 0.5, 0.3);
        Monster monster3 = new Monster(hardVal,100, 122, 0.2, 0.5, 0.3);
        Monster monster4 = new Monster(hardVal,100, 112, 0.2, 0.5, 0.3);
        Monster monster5 = new Monster(hardVal,100, 132, 0.6, 0.5, 0.3);
        Monster monster6 = new Monster(hardVal,100, 142, 0.2, 0.5, 0.3);
        Monster monster7 = new Monster(hardVal,100, 122, 0.2, 0.5, 0.3);
        Monster monster8 = new Monster(hardVal,100, 112, 0.2, 0.5, 0.3);
        Monster monster9 = new Monster(hardVal,100, 132, 0.2, 0.5, 0.3);
        Monster monster10 = new Monster(hardVal,100, 122, 0.2, 0.5, 0.3);
        monstersList.addAll(Arrays.asList(monster1, monster2, monster3, monster4, monster5, monster6,
                monster7, monster8, monster9, monster10));
    }

    public static void initTreasures() {
        Treasure treasure1 = new Treasure(new HpPotion(240));
        Treasure treasure2 = new Treasure(new HpPotion(241));
        Treasure treasure3 = new Treasure(new HpPotion(242));
        Treasure treasure4 = new Treasure(new HpPotion(243));
        Treasure treasure5 = new Treasure(new HpPotion(244));
        Treasure treasure6 = new Treasure(new HpPotion(245));
        Treasure treasure7 = new Treasure(new HpPotion(246));
        Treasure treasure8 = new Treasure(new HpPotion(247));
        Treasure treasure9 = new Treasure(new HpPotion(248));
        Treasure treasure10 = new Treasure(new HpPotion(249));
        treasureList.addAll(Arrays.asList(treasure1, treasure2, treasure3, treasure4, treasure5, treasure6,
                treasure7, treasure8, treasure9, treasure10));
    }

    private static void generateMap() {
        Set<Integer> memo = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            int rand = new Random().nextInt(20);
            while (!memo.add(rand)) {
                rand = new Random().nextInt(20);
            }
            randMap[i] = rand;
        }
        initMonsters();
        initTreasures();
        Random random = new Random();
        RandomizeLists(random, monstersList);
        RandomizeLists(random, treasureList);
    }

    private static void RandomizeLists(Random random, ArrayList<Entity> treasureList) {
        for (int i = 0; i < 10; i++) {
            int randInt = random.nextInt(10);
            HashSet<Integer> treasureCache = new HashSet<>();
            while (!treasureCache.add(randInt)) {
                randInt = random.nextInt(10);
            }
            gameMap.add(treasureList.get(randInt));
        }
    }

    public static Entity getCurrentRoom() {
        return gameMap.get(randMap[currentIndexMap - 1]);
    }
    public static Entity getRandRoom() {
        generateMap();
        currentIndexMap++;
        return gameMap.get(randMap[currentIndexMap - 1]);
    }

    public static void setHard(double value) {
        hardVal = (int) value;
    }
}
