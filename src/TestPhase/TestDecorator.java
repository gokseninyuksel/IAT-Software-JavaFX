package TestPhase;

public class TestDecorator extends Test{
    private final Test testDecorated;
    public TestDecorator(Test testDecorated){
        this.testDecorated = testDecorated;
    }
    @Override
    public void render() {
        this.testDecorated.render();
    }

    @Override
    public void setLabels() {
        this.testDecorated.setLabels();
    }
}
