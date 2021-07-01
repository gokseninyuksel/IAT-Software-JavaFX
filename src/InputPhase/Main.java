package InputPhase;

import TestPhase.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        ControllerInput controllerInput = new PlainController(primaryStage);
        Scene scene = new Scene(new BorderPane(),600,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        Test test = new SimpleTest(scene);
        test = new AttributeDecorator(test);
        test = new TargetDecorator(test);
        test = new ReversedDecorator(test);
        test.render();
        System.out.println(test.getClassify_left());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
