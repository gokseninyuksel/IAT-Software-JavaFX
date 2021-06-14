package TestPhase;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class Test {
    // First Label for the left word
    protected Text left_label_1;
    // Second label for the right word
    protected Text left_label_2;
    //First Label for the right word
    protected Text right_label_1;
    //Second label for the right word
    protected Text right_label_2;
    //Press E to classify as left_label_1 / left_label_2
    protected Label classify_left;
    //Press I to classify as right_label_1 / right_label_2
    protected Label classify_right;
    //When you are feeling ready press space
    protected Label space;
    //Wrongly classified
    protected Label wrongly;
    //The random word
    protected Label randomWord;
    //Random Word shower for the associated labels
    protected RandomWordStrategy randomWordStrategy;
    //TimeLine for showing the words, and pausing/starting the application
    protected Timeline timeline;
    //How many words will be shown
    protected int word_count;
    //Controller for deciding what to do with key pressed
    protected Controller controller;
    //Left Pane including labels
    protected GridPane leftPane;
    //Right pane including labels
    protected GridPane rightPane;
    //Middle pane including the word, start and wrong
    protected GridPane middlePane;
    //Scene where the application is running
    protected Scene scene;
    //Border pane to set the root of the scene
    protected BorderPane root;


    /**
     * Constructor for the test, initialize the shared elements
     */
    public Test(){
        init();
    }

    /**
     * Initialize the shared elements
     */
    public void init(){
        this.space = new Label("When you are feeling ready, press space");
        this.wrongly = new Label("You classified it wrongly");
        this.randomWord = new Label();
        this.wrongly.setVisible(false);
        this.randomWord.setVisible(false);
        this.leftPane = new GridPane();
        this.rightPane = new GridPane();
        this.middlePane = new GridPane();
        this.leftPane.setAlignment(Pos.TOP_LEFT);
        this.rightPane.setAlignment(Pos.TOP_RIGHT);
        this.middlePane.setAlignment(Pos.CENTER);
        this.middlePane.add(space,0,0);
        this.middlePane.add(wrongly,0,0);
        this.middlePane.add(randomWord,0,0);
        this.randomWord = new Label();
        this.root = new BorderPane();
        this.classify_right = new Label("Press I to classify them as: ");
        this.classify_left = new Label("Press E to classify them as: ");
        this.timeline = new Timeline(new KeyFrame(Duration.minutes(5),this::run));
    }

    /**
     * Run the program test for maximum of 5 minutes
     * @param actionEvent
     */
    public void run(ActionEvent actionEvent){
          scene.setOnKeyPressed(event -> {
              if(event.getCode().equals(KeyCode.SPACE)){
               controller.start();
           }
           else if(event.getCode().equals(KeyCode.E)){
               controller.classify_left(left_label_1,left_label_2,this.randomWord.getText());
           }
           else if(event.getCode().equals(KeyCode.I)){
               controller.classify_right(right_label_1,right_label_2,this.randomWord.getText());
           }
           else if(word_count == 0){
               controller.next();
           }

          });
    }


    /**
     * Select a random word from the strategy, and pause the time line
     */
    private void selectWord() {
        this.randomWord.setText(this.randomWordStrategy.pool());
        this.randomWord.setVisible(true);
        this.word_count--;
        this.timeline.pause();
    }

    /**
     * Pause the running of the timeline
     */
    public void pauseAnimation(){
        this.timeline.pause();
    }

    /**
     * Start the timeline
     */
    public void startAnimation(){
        this.timeline.play();
    }

    /**
     * Set the wrong text as visible or not
     * @param isVisible true if it is visible false otherwise
     */
    public void setVisibleWrong(boolean isVisible){
        this.wrongly.setVisible(isVisible);
    }

    /**
     * Set the space text as visible or not
     * @param isVisible true if it is visible false otherwise
     */
    public void setInvisibleSpace(boolean isVisible){
        this.space.setVisible(isVisible);
    }
    /**
     * Set the random word text as visible or not
     * @param isVisible true if it is visible false otherwise
     */
    public void setInvisibleWord(boolean isVisible){
        this.randomWord.setVisible(isVisible);
    }
    public abstract void render();
    public abstract void setLabels();
    public abstract String getClassify_left();
    public abstract String getClassify_right();
}
