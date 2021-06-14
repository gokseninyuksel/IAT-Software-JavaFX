package TestPhase;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

public interface Controller {
    void next();
    void start();
    boolean classify_left(Text left1, Text left2, String text);
    boolean classify_right(Text right1, Text right2, String text);
}