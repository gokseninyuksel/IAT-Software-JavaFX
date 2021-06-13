package InputPhase;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

public class ViewInput2 implements InputObserver{
    //Target 1 words view, this is a chip view.
    private JFXChipView<String> target_1_words;
    //Target 2 words view, this is a chip view.
    private JFXChipView<String> target_2_words;
    //Attribute 1 words view, this is a chip view.
    private JFXChipView<String> attribute_1_words;
    //Attribute 2 words view, this is a chip view.
    private JFXChipView<String> attribute_2_words;
    //Model, updating the words
    private TestWords testWords;
    //Getting the name of the attributes
    private TestAttributes testAttributes;
    //Getting the name of the targets
    private TestTargets testTargets;
    //Controller, decides what to do with the input
    private ControllerInput controller;
    //Label on top
    private Label text;
    //Text for for attribute.
    private Label attributes_1_text;
    //Text for second attribute
    private Label attributes_2_text;
    //Text for first target
    private Label targets_1_text;
    //Text for second target
    private Label targets_2_text;
    //Array for storing attribute texts
    private Label[] attributes_texts;
    //Array for storing target texts
    private Label[] targets_texts;
    //Button for proceeding to next phase
    private JFXButton next;
    //Button for going back to the previous phase
    private JFXButton previous;
    //Grip pane for adjusting the elements
    private GridPane pane;
    //Border pane for centering the elements
    private BorderPane border;
    //Alert for > 10 or duplicate elements
    private Alert alert;
    /**
     * Constructor for the View of Input 2
     * @param controller
     */
    public ViewInput2(ControllerInput controller){
        this.testTargets = TestTargets.getTargetInstance();
        this.testAttributes = TestAttributes.getTestAttributesInstance();
        this.testWords = TestWords.getTestWordsInstance();
        this.controller = controller;
        this.pane = new GridPane();
    }

    @Override
    public void update() {
        System.out.println("Test Words: " +  testWords.getTestWords());
    }

    /**
     * Initialize the layout, set javaFX elements return the grid pane
     */
    public GridPane init(){
        //Setting up the top text
        this.text = new Label();
        text.setText("Welcome to the Implicit Association Test software, to proceed you need to input " +
                " four categories where two of them is targets, and other two is attributes.");
        text.setFont(Font.font(18));
        //Setting up the next button
        this.next = new JFXButton("Next");
        next.setRipplerFill(Color.RED);
        next.setVisible(false);
        next.setButtonType(JFXButton.ButtonType.RAISED);
        next.setStyle("-fx-background-color: gray");
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.next();
            }
        });
        //Setting up the previous button
        this.previous = new JFXButton("Previous");
        previous.setRipplerFill(Color.RED);
        previous.setVisible(true);
        previous.setButtonType(JFXButton.ButtonType.RAISED);
        previous.setStyle("-fx-background-color: gray");
        previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.previous();
            }
        });
        //Setting up the chip views
        this.newAttribute_1();
        this.newAttribute_2();
        this.newTarget_1();
        this.newTarget_2();
        //Set the grid pane and put the elements
        ColumnConstraints width1 = new ColumnConstraints(285);
        RowConstraints gap1 = new RowConstraints(100);
        RowConstraints gap2 = new RowConstraints(300);
        RowConstraints gap3 = new RowConstraints(50);
        for(int a = 0; a < 4; a++){
            pane.getColumnConstraints().add(width1);
        }
        pane.add(next,4,3);
        pane.add(previous,3,3);
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.getRowConstraints().add(0,gap1);
        pane.getRowConstraints().add(1,gap2);
        pane.getRowConstraints().add(2,gap3);
        return pane;
    }

    /**
     * Initialize the labels.
     */
    public void init_labels() {
        Iterator<String> attributesIterator = this.testAttributes.getAttributes().iterator();
        Iterator<String> targetsIterator = this.testTargets.getTargets().iterator();
        for(int a = 0; a < 2; a++) {
            if(attributes_texts != null && targets_texts != null) {
                pane.getChildren().remove(attributes_texts[a]);
                pane.getChildren().remove(targets_texts[a]);
            }
        }
        attributes_1_text = new Label();
        attributes_2_text = new Label();
        targets_1_text = new Label();
        targets_2_text = new Label();
        attributes_texts = new Label[]{attributes_1_text, attributes_2_text};
        targets_texts = new Label[]{targets_1_text, targets_2_text};
        set_labels(attributes_texts,attributesIterator,false);
        set_labels(targets_texts, targetsIterator,true);
        Label[] labels ={targets_1_text,targets_2_text,attributes_1_text,attributes_2_text};
        for(int a = 0; a < 4; a++) {
            pane.add(labels[a], a, 0);
        }
    }

    /**
     * Set the label tests according to the attributes and the targets
     * @param array
     * @param iterator
     * @param red
     */
    private void set_labels(Label[] array, Iterator<String> iterator, boolean red){
        int index = 0;
        while(iterator.hasNext()) {
            array[index].setText("Words for: " + iterator.next());
            if(red)
                array[index].setTextFill(Color.RED);
            else
                array[index].setTextFill(Color.NAVY);
            array[index].setFont(Font.font("Calibri"));
            array[index].setFont(Font.font(20));
            array[index].setStyle("-fx-font-weight: bold;");
            index++;
        }
    }
    /**
     * Hide the next button
     */
    public void next_visible(){
        this.next.setVisible(true);
    }

    /**
     * Make the next button visible
     */
    public void next_hide(){
        this.next.setVisible(false);
    }

    /**
     * Show an alert
     */
    public void showAlert(){
        this.alert = new Alert(Alert.AlertType.ERROR);
        alert.showAndWait();
    }

    /**
     * Instantiate new view for attribute 1
     */
    public void newAttribute_1(){
        this.attribute_1_words = new JFXChipView<>();
        attribute_1_words.setStyle("-fx-background-color: WHITE;");
        attribute_1_words.getChips().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if(c.wasAdded() && c.getAddedSubList().size() == 1){
                    controller.addWordsTestWord(attributes_texts[0].getText(),c.getAddedSubList());
                }
                else if(c.wasRemoved()){
                    controller.removeWordsTestWord(attributes_texts[0].getText(),c.getRemoved());
                }
                controller.setWordButtonVisibility();
            }
        });
        pane.add(attribute_1_words,2,1);


    }
    /**
     * Instantiate new view for attribute 2
     */
    public void newAttribute_2(){
        this.attribute_2_words = new JFXChipView<>();
        attribute_2_words.setStyle("-fx-background-color: WHITE;");
        attribute_2_words.getChips().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if(c.wasAdded() && c.getAddedSubList().size() == 1){
                    controller.addWordsTestWord(attributes_texts[1].getText(),c.getAddedSubList());
                }
                else if(c.wasRemoved()){
                    controller.removeWordsTestWord(attributes_texts[1].getText(),c.getRemoved());
                }
                controller.setWordButtonVisibility();
            }
        });
        pane.add(attribute_2_words,3,1);

    }
    /**
     * Instantiate new view for target 1
     */
    public void newTarget_1(){
        this.target_1_words = new JFXChipView<>();
        target_1_words.setStyle("-fx-background-color: WHITE;");
        target_1_words.getChips().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if(c.wasAdded() && c.getAddedSubList().size() == 1){
                    controller.addWordsTestWord(targets_texts[0].getText(),c.getAddedSubList());
                }
                else if(c.wasRemoved()){
                    controller.removeWordsTestWord(targets_texts[0].getText(),c.getRemoved());
                }
                controller.setWordButtonVisibility();
            }
        });
        pane.add(target_1_words,0,1);
    }
    /**
     * Instantiate new view for target 2
     */
    public void newTarget_2(){
        this.target_2_words = new JFXChipView<>();
        target_2_words.setStyle("-fx-background-color: WHITE;");
        target_2_words.getChips().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if(c.wasAdded() && c.getAddedSubList().size() == 1){
                    controller.addWordsTestWord(targets_texts[1].getText(),c.getAddedSubList());
                }
                else if(c.wasRemoved()){
                    controller.removeWordsTestWord(targets_texts[1].getText(),c.getRemoved());

                }
                controller.setWordButtonVisibility();
            }
        });
        pane.add(target_2_words,1,1);
    }

    public GridPane getPane(){
        return pane;
    }
    /**
     * Getter for the each word view, stored in hashmap
     * @return
     */
    public HashMap<Optional<String>,JFXChipView<String>> getWords() {
        HashMap<Optional<String>, JFXChipView<String>> returns = new HashMap<>();
        if(targets_1_text.getText().length() >= 2) {
            returns.put(Optional.ofNullable(targets_1_text.getText().split(" ")[2]), target_1_words);
            returns.put(Optional.ofNullable(targets_2_text.getText().split(" ")[2]), target_2_words);
            returns.put(Optional.ofNullable(attributes_1_text.getText().split(" ")[2]), attribute_1_words);
            returns.put(Optional.ofNullable(attributes_2_text.getText().split(" ")[2]), attribute_2_words);
        }
        else{
            returns.put(Optional.empty(), target_1_words);
            returns.put(Optional.empty(), target_2_words);
            returns.put(Optional.empty(), attribute_1_words);
            returns.put(Optional.empty(), attribute_2_words);
        }
        return returns;
    }



}
