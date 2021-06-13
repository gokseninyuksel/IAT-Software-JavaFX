package InputPhase;

import java.util.*;

public class TestWords implements InputSubject {
    //No duplicates in test words, and corresponding keys.
    private Map<String, HashSet<String>> testWords;
    private static TestWords testWordsInstance;
    private List<InputObserver> observers;
    /**
     * Constructor for Test Words
     */
    private TestWords(){
        //No duplicates are allowed constant look up time
        this.testWords = new HashMap<>();
        this.observers = new LinkedList<>();
    }

    public static TestWords getTestWordsInstance() {
        if(testWordsInstance == null){
            testWordsInstance = new TestWords();
        }
        return testWordsInstance;
    }

    /**
     * Adds the test word corresponding to the attribute/target. If it is duplicate returns false.
     *
     * @param toAdd attribute/target name
     * @param test_word the test word to be added to attribute/target
     * @return false if duplicate test_words are contained.
     */
    public boolean addWord(String toAdd,String test_word){
        if(this.testWords.containsKey(toAdd)){
            HashSet<String> added = this.testWords.get(toAdd);
            if(!containsValue(test_word)) {
                added.add(test_word);
                this.testWords.put(toAdd, added);
                notifyObserver();
                return true;
            }
            else{
                return false;
            }
        }
        else{
            HashSet<String> testWords = new HashSet<>();
            if(!containsValue(test_word)) {
                testWords.add(test_word);
                this.testWords.put(toAdd, testWords);
                notifyObserver();
                return true;
            }
            else{
                return false;
            }
        }
    }

    /**
     * Check if the word is present in the test words. Return true if it is, false otherwise
     * @param toAdd
     * @return
     */
    private boolean containsValue(String toAdd) {
        boolean contains = false;
        for( HashSet<String> set : testWords.values()){
            if(!set.isEmpty()) {
                contains = contains || set.contains(toAdd);
            }
        }
        return contains;
    }

    /**
     * Removes the test word associated with the target/attribute. Return true if it is removed, false otherwise.
     * @param toRemove
     * @param removed
     * @return
     */
    public boolean removeWord(String toRemove, String removed){
        if(this.testWords.containsKey(toRemove)){
            HashSet<String> remove = this.testWords.get(toRemove);
            boolean isPresent = remove.contains(removed);
            if(isPresent){
                remove.remove(removed);
                this.testWords.put(toRemove,remove);
                notifyObserver();
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Deletes the specified key
     * @param delete
     */
    public void deleteKey(String delete){
        this.testWords.remove(delete);
        notifyObserver();
    }

    /**
     * Getter for the test words
     * @return
     */
    public Map<String, HashSet<String>> getTestWords() {
        return testWords;
    }

    @Override
    public void addObserver(InputObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(InputObserver observer) {
        int index = this.observers.indexOf(observer);
        if(index >= 0 ){
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        observers.forEach(InputObserver::update);
    }
}
