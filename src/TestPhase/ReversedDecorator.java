package TestPhase;

import InputPhase.TestTargets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ReversedDecorator extends TestDecorator{
    private Scene primary;
    public ReversedDecorator(Test testDecorated, Scene primary) {
        this.testDecorated = testDecorated;
        this.primary = primary;
    }

    @Override
    public void render() {
        this.setLabels();
        testDecorated.render();
        this.classify_right.setText("Press I to classify them as: " + this.getClassify_left());
        this.classify_left.setText("Press E to classify them as: " + this.getClassify_right());
        this.leftPane.add(this.classify_left ,0,0);
        this.rightPane.add(this.classify_right,0,0);
        this.root.setLeft(this.leftPane);
        this.root.setRight(this.rightPane);
        this.root.setCenter(this.middlePane);
        this.primary.setRoot(this.root);
    }

    @Override
    public void setLabels() {
        testDecorated.setLabels();
    }
    public String getClassify_left() {
        return testDecorated.getClassify_left();
    }
    public String getClassify_right(){
        return testDecorated.getClassify_right();

    }
}
