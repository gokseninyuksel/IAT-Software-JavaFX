package TestPhase;

import javafx.scene.Scene;

public class TestFactoryMethod {
    public static Test createTest(String spec, Scene scene) {
        if(spec.equalsIgnoreCase("0")){
            return new TargetDecorator(new SimpleTest(scene));
        }
        else if(spec.equalsIgnoreCase("1")){
            return new AttributeDecorator(new SimpleTest(scene));
        }
        else if(spec.equalsIgnoreCase("2")){
            return new AttributeDecorator(new TargetDecorator(new SimpleTest(scene)));
        }
        else if(spec.equalsIgnoreCase("3")){
            return new DataCollectorDecorator(new TargetDecorator( new AttributeDecorator(new SimpleTest(scene))));
        }
        else if(spec.equalsIgnoreCase("4")){
            return new ReversedDecorator(new TargetDecorator(new SimpleTest(scene)));
        }
        else if(spec.equalsIgnoreCase("5")){
            return new ReversedDecorator(new AttributeDecorator(new TargetDecorator(new SimpleTest(scene))));
        }
        else{
            return new DataCollectorDecorator(new ReversedDecorator(new AttributeDecorator(new TargetDecorator(new SimpleTest(scene)))));
        }

    }
}
