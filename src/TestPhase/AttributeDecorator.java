package TestPhase;

import InputPhase.TestAttributes;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AttributeDecorator extends TestDecorator{
    private TestAttributes testAttributes;
    public AttributeDecorator(Test testDecorated) {
        super(testDecorated);
        testAttributes = TestAttributes.getTestAttributesInstance();
    }

    @Override
    public void render() {
        super.render();
        this.leftPane.add(this.classify_left ,0,0);
        this.rightPane.add(this.classify_right,0,0);
        this.m

    }

    @Override
    public void setLabels() {
        super.setLabels();
        this.left_label_1 = new Text((String) testAttributes.getAttributes().toArray()[0]);
        this.right_label_1 = new Text((String) testAttributes.getAttributes().toArray()[0]);
        this.left_label_1.setFill(Color.NAVY);
        this.right_label_1.setFill(Color.NAVY);
        this.classify_left = new Label("Press E to classify them as: " + left_label_1 );
        this.classify_right = new Label("Press I to classify them as: " + right_label_1);

    }
}
