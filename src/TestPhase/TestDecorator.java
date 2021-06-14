package TestPhase;

public class TestDecorator extends Test{
    Test testDecorated;
    public TestDecorator(){
    }
    @Override
    public void render() {
        this.testDecorated.render();
    }

    @Override
    public void setLabels() {
        this.testDecorated.setLabels();
    }

    @Override
    public String getClassify_left() {
        return testDecorated.getClassify_left();
    }

    @Override
    public String getClassify_right() {
        return testDecorated.getClassify_right();
    }
}
