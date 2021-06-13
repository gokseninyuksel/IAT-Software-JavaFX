package InputPhase;

import java.util.*;

public class TestAttributes implements InputSubject {
    private HashSet<String> attributes;
    //Observer Pattern
    private List<InputObserver> observers;

    private static TestAttributes testAttributes;
    /**
     * Constructor for the test attributes
     */
    private TestAttributes(){
        this.attributes = new LinkedHashSet<>();
        this.observers = new LinkedList<>();
    }

    public static TestAttributes getTestAttributesInstance(){
        if(testAttributes == null){
            testAttributes = new TestAttributes();
        }
        return testAttributes;
    }
    /**
     * Return true if there is no duplicates
     * @param target
     * @return
     */
    public boolean addAttribute(String target){
        if(this.attributes.add(target)){
            notifyObserver();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns true if attribute is removed successfully
     * @param target
     * @return
     */
    public boolean removeAttribute(String target){
        if(this.attributes.contains(target)){
            boolean removed = this.attributes.remove(target);
            notifyObserver();
            return removed;

        }
        else{
            return false;
        }
    }

    /**
     * Getter for attributes
     * @return
     */
    public LinkedHashSet<String> getAttributes() {
        return (LinkedHashSet<String>) attributes;
    }

    @Override
    public void addObserver(InputObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(InputObserver observer) {
        int index = this.observers.indexOf(observer);
        if(index >= 0){
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        this.observers.forEach(InputObserver::update);
    }
}
