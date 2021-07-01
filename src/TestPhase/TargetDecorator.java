package TestPhase;

import InputPhase.TestAttributes;
import InputPhase.TestTargets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TargetDecorator extends TestDecorator{
    private TestTargets testTargets;
    private Scene primary;
    public TargetDecorator(Test testDecorated) {
        this.testDecorated = testDecorated;
        testTargets = TestTargets.getTargetInstance();
    }

    @Override
    public void render() {
        this.setLabels();
        testDecorated.render();
        this.classify_right.setText("Press I to classify them as: " + this.getClassify_right());
        this.classify_left.setText("Press E to classify them as: " + this.getClassify_left());
        this.leftPane.add(this.classify_left ,0,0);
        this.rightPane.add(this.classify_right,0,0);
        this.root.setLeft(this.leftPane);
        this.root.setRight(this.rightPane);
        this.root.setCenter(this.middlePane);
        this.scene.setRoot(this.root);
    }

    @Override
    public void setLabels() {
        testDecorated.setLabels();
//        this.left_label_1 = new Text((String) testAttributes.getAttributes().toArray()[0]);
//        this.right_label_1 = new Text((String) testAttributes.getAttributes().toArray()[0]);
        this.left_label_2 = new Text("AA");
        this.right_label_2 = new Text("BB");
        this.left_label_2.setFill(Color.NAVY);
        this.right_label_2.setFill(Color.NAVY);
    }
    public String getClassify_left() {
        return testDecorated.getClassify_left() + " " + this.left_label_2.getText();
    }
    public String getClassify_right(){
        return testDecorated.getClassify_right() + " " + this.right_label_2.getText();

    }
}
