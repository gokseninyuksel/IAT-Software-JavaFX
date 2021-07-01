package TestPhase;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestController implements Controller{
    //HashMap to store tests, useful for changing the scene
    private HashMap<Integer,Test> tests;
    //HashMao to store the reaction times of the user
    private HashMap<Test, List<Float>> reactionTimes;
    //Number of appearance of the words
    private List<Integer> chances;
    //Integer indication which test we are dealing with
    private int pointer;
    private Scene scene;
    public TestController(Scene scene){
        this.tests = new HashMap<>();
        this.reactionTimes = new HashMap<>();
        this.chances = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            tests.put(i,TestFactoryMethod.createTest(String.valueOf(i), scene));
        }

    }

    @Override
    public void next() {

    }

    @Override
    public void start() {

    }

    @Override
    public boolean classify_left(Text left1, Text left2, String text) {
        return false;
    }

    @Override
    public boolean classify_right(Text right1, Text right2, String text) {
        return false;
    }
}
