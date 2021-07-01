package TestPhase;

import InputPhase.TestAttributes;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AttributeDecorator extends TestDecorator{
    private TestAttributes testAttributes;
    public AttributeDecorator(Test testDecorated) {
        this.testDecorated = testDecorated;
        testAttributes = TestAttributes.getTestAttributesInstance();
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
        System.out.println(this.classify_right.getText());
        System.out.println(this.classify_left.getText());

    }

    @Override
    public void setLabels() {
        testDecorated.setLabels();
//        this.left_label_1 = new Text((String) testAttributes.getAttributes().toArray()[0]);
//        this.right_label_1 = new Text((String) testAttributes.getAttributes().toArray()[0]);
        this.left_label_1 = new Text("A");
        this.right_label_1 = new Text("B");
        this.left_label_1.setFill(Color.NAVY);
        this.right_label_1.setFill(Color.NAVY);
    }

    @Override
    public String getClassify_left() {
        return testDecorated.getClassify_left() + " " + this.left_label_1.getText();
    }
    public String getClassify_right(){
        return testDecorated.getClassify_right() + " " + this.right_label_1.getText();

    }
}
