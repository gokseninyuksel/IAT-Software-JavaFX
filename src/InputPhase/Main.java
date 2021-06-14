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
        Test test = new SimpleTest();
        test = new AttributeDecorator(test,scene);
        test = new TargetDecorator(test,scene);
        test = new ReversedDecorator(test,scene);
        test.render();
        System.out.println(test.getClassify_left());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
