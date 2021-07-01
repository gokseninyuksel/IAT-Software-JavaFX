package TestPhase;

public class DataCollectorDecorator extends TestDecorator{
    private Test test;
    public DataCollectorDecorator(Test test) {
        this.test = test;
    }

    @Override
    public void render() {
        test.attach_data();
        super.render();
    }
}
