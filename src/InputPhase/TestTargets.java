package InputPhase;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a singleton class as we just need one instance of the object the whole time
 *
 */
public class TestTargets implements InputSubject {
    //No duplicate allowed, set for targets
    private HashSet<String> targets;
    private List<InputObserver> observers;
    //The instance
    private static TestTargets testTarget;
    /**
     * Constructor for test targets
     */
    private TestTargets(){
        this.targets = new LinkedHashSet<>();
        this.observers = new LinkedList<>();
    }

    /**
     * The singleton instance of the class. Returns the instance
     * @return
     */
    public static TestTargets getTargetInstance(){
        if( testTarget == null){
            testTarget = new TestTargets();
        }
        return testTarget;
    }
    /**
     * Return true if there is no duplicates
     * @param target
     * @return
     */
    public boolean addTarget(String target){
        if(this.targets.add(target)){
            notifyObserver();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns true if target is removed successfully
     * @param target
     * @return
     */
    public boolean removeTarget(String target){
        if(this.targets.contains(target)){
            boolean removed = this.targets.remove(target);
            notifyObserver();
            return removed;

        }
        else{
            return false;
        }
    }

    /**
     * Getter for targets
     * @return
     */
    public HashSet<String> getTargets() {
        return (LinkedHashSet<String>) targets;
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
