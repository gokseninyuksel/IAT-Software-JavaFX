package InputPhase;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.event.ChangeListener;

public class ViewInput1 implements InputObserver {
    //Target view, this is a chip view.
    private JFXChipView<String> targets;
    //Attributes view, this is a chip view.
    private JFXChipView<String> attributes;
    //Updating the chips, Model
    private TestTargets testTargets;
    //Updating the chips, Model
    private TestAttributes testAttributes;
    //Controller, decides what to do with the input
    private ControllerInput controller;
    //Label on top
    private Label text;
    //Text for attributes.
    private Label attributes_text;
    //Text for targets
    private Label targets_text;
    //Button for proceeding to next phase
    private JFXButton next;
    //Grip pane for adjusting the elements
    private GridPane pane;
    //Border pane for centering the elements
    private BorderPane border;
    //Alert for > 2 or duplicate elements
    private Alert alert;
    /**
     * Constructor for the View of Input 1
     * @param controller
     */
    public ViewInput1(ControllerInput controller){
        this.testTargets = TestTargets.getTargetInstance();
        this.testAttributes = TestAttributes.getTestAttributesInstance();
        this.controller = controller;
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
        //Setting up the text for attributes and targets
        this.attributes_text = new Label("Attributes. Good/Bad");
        this.targets_text = new Label("Targets. Black/White");
        targets_text.setTextFill(Color.RED);
        targets_text.setFont(Font.font("Calibri"));
        targets_text.setFont(Font.font(20));
        targets_text.setStyle("-fx-font-weight: bold;");
        attributes_text.setTextFill(Color.NAVY);
        attributes_text.setFont(Font.font("Calibri"));
        attributes_text.setFont(Font.font(20));
        attributes_text.setStyle("-fx-font-weight: bold;");
        //Setting up the input for targets and attributes
        this.targets = new JFXChipView<>();
        this.attributes = new JFXChipView<>();
        targets.setStyle("-fx-background-color: WHITE;");
        attributes.setStyle("-fx-background-color: WHITE;");
        //Setting up the next button
        this.next = new JFXButton("Next");
        next.setRipplerFill(Color.RED);
        next.setVisible(false);
        next.setButtonType(JFXButton.ButtonType.RAISED);
        next.setStyle("-fx-background-color: gray");
        //Add Change listener on button pressed to invoke controller
        next.setOnAction(this.buttonPressed());
        //Add change listeners to invoke controller
        targets.getChips().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if(c.wasAdded()){
                    controller.addWordsTargets(c.getAddedSubList());

                }
                else if(c.wasRemoved()){
                    controller.removeWordsTargets(c.getRemoved());

                }
            controller.setButtonVisibility();
            }

        });
        attributes.getChips().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if(c.wasAdded()){
                    controller.addWordsAttributes(c.getAddedSubList());
                }
                else if(c.wasRemoved()){
                    controller.removeWordsAttributes(c.getRemoved());

                }
                controller.setButtonVisibility();
            }
        });

        //Set the grid pane and put the elements
        this.pane = new GridPane();
        ColumnConstraints height = new ColumnConstraints(250);
        RowConstraints gap_1 = new RowConstraints(100);
        RowConstraints gap_2 = new RowConstraints(200);
        RowConstraints gap_3 = new RowConstraints(50);
        Label[] labels = new Label[]{targets_text,attributes_text};
        JFXChipView<String>[] views = new JFXChipView[]{targets,attributes};
        for(int a = 0; a < 2; a++){
            pane.add(labels[a],a,0);
            pane.add(views[a],a,1);
            pane.getColumnConstraints().add(height);
        }
        pane.add(next,2,2);
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.getRowConstraints().add(0,gap_1);
        pane.getRowConstraints().add(1,gap_2);
        pane.getRowConstraints().add(2,gap_3);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    /**
     * EventHandler on the button, if it is pressed, controller switches to second scene
     * @return event handler
     */
    public EventHandler<ActionEvent> buttonPressed(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.next();
            }
        };
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
     * Getter for the attribute inputs
     * @return
     */
    public JFXChipView<String> getAttributes() {
        return attributes;
    }

    /**
     * Getter for the target inputs
     * @return
     */
    public JFXChipView<String> getTargets(){
        return targets;
    }

    /**
     * Check the array content.
     */
    @Override
    public void update() {
        System.out.println("Test Targets Is " + testTargets.getTargets());
        System.out.println("Test Attributes Is " + testAttributes.getAttributes());
    }


}
