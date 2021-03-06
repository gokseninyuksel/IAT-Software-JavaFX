package InputPhase;

import com.jfoenix.controls.JFXChipView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class PlainController implements ControllerInput{
    //View for input 1
    private ViewInput1 viewInput1;
    //View for input 2
    private ViewInput2 viewInput2;
    //Model test Attributes
    private TestAttributes testAttributes;
    //Model testTargets
    private TestTargets testTargets;
    //Model testWords
    private TestWords testWords;
    // Stage for setting the scene
    private Stage stage;
    //Scene for setting the environment
    private Scene scene;
    //Grid pane for layout
    private GridPane pane;
    //Grid pane from view 2
    private GridPane pane_view2;
    /**
     * Constructor for the controller
     * @Param stage  starting stage
     */
    public PlainController(Stage stage){
        this.testAttributes = TestAttributes.getTestAttributesInstance();
        this.testTargets = TestTargets.getTargetInstance();
        this.testWords = TestWords.getTestWordsInstance();
        this.viewInput1 = new ViewInput1(this);
        this.viewInput2 = new ViewInput2(this);
        this.testAttributes.addObserver(viewInput1);
        this.testTargets.addObserver(viewInput1);
        this.testWords.addObserver(viewInput2);
        pane = this.viewInput1.init();
        this.viewInput2.newTarget_2();
        this.viewInput2.newTarget_1();
        this.viewInput2.newAttribute_1();
        this.viewInput2.newAttribute_2();
        this.viewInput2.init_labels();
        pane_view2 = this.viewInput2.init();
        this.stage = stage;
        scene = new Scene(pane, 600,400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * If there are less than two words in attribute array, try to add the recently written element.
     * Throws an alert if adding was not successful, target array contains the same word or array size is bigger than 2
     *
     * @param input
     * @return
     */
    @Override
    public void addWordsAttributes(List<? extends String> input) {
        ObservableList<String> chips = viewInput1.getAttributes().getChips();
        if (testAttributes.getAttributes().size() < 2) {
            Iterator<? extends String> iterator = input.iterator();
            boolean successful = true;
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (!testTargets.getTargets().contains(next))
                    successful = successful && testAttributes.addAttribute(next);
                else {
                    removeAndShow(chips);
                }
            }
            if(!successful){
                removeAndShow(chips);
            }
       } else {
            removeAndShow(chips);
        }

    }

    /**
     * Removes the words from the list of words of attributes.
     * @param input
     * @return
     */
    @Override
    public void removeWordsAttributes(List<? extends String> input) {
        Iterator<? extends String> iterator = input.iterator();
        boolean successful = true;
        while (iterator.hasNext()) {
                String next = iterator.next();
                successful = successful && testAttributes.removeAttribute(next);
                testWords.deleteKey(next);
            }
        if(!successful){
            viewInput1.showAlert();
        }
        }

    /**
     * If there are less than two words in targets array, try to add the recently written element.
     * Throws an alert if adding was not successful, attribute array contains the same word or array size is bigger than 2.
     * @param input
     * @return
     */
    @Override
    public void addWordsTargets(List<? extends String> input) {
        ObservableList<String> chips = viewInput1.getTargets().getChips();
        //If test array is contains less than two elements, try to add the element
        if(testTargets.getTargets().size() < 2) {
            Iterator<? extends String> iterator = input.iterator();
            boolean successful = true;
            while (iterator.hasNext()) {
                String next = iterator.next();
                //If attributes array does not contain the same element, add it
                if (!testAttributes.getAttributes().contains(next)) {
                    successful = successful && testTargets.addTarget(next);
                }
                //If attributes array contains the same element throw an error.
                else{
                   removeAndShow(chips);
                }
            }
            //If adding was not successful, throw an error.
            if(!successful){
                removeAndShow(chips);
            }
        }
        //If test array contains two or more elements, throw an error.
        else{
            removeAndShow(chips);

        }
    }
    /**
     * Removes the words from the list of words of attributes.
     * @param input
     * @return
     */
    @Override
    public void removeWordsTargets(List<? extends String> input) {
        Iterator<? extends String> iterator = input.iterator();
        boolean successful = true;
        while(iterator.hasNext()){
            String next = iterator.next();
            successful = successful && testTargets.removeTarget(next);
            testWords.deleteKey(next);
        }
        if(!successful){
            viewInput1.showAlert();
        }
    }

    /**
     * Removes all the chips for view 2, and adds the ones that we have in the test word set.
     *
     */
    public void removeChips() {
        //Remove the chips
        this.viewInput2.newTarget_2();
        this.viewInput2.newTarget_1();
        this.viewInput2.newAttribute_1();
        this.viewInput2.newAttribute_2();
        //Search the targets
        for(String target: testTargets.getTargets()){
            if(viewInput2.getWords().containsKey(Optional.ofNullable(target))) {
                if (testWords.getTestWords().containsKey(target)) {
                    viewInput2.getWords().get(Optional.ofNullable(target)).getChips().addAll(testWords.getTestWords().get(target));
                }
            }
        }
        //Search the attributes
        for(String target: testAttributes.getAttributes()){
            if(viewInput2.getWords().containsKey(Optional.ofNullable(target))) {
                if(testWords.getTestWords().containsKey(target))
                    viewInput2.getWords().get(Optional.ofNullable(target)).getChips().addAll(testWords.getTestWords().get(target));
            }
        }
    }

    /**
     * Removes the last added element and shows an error
     * @param chips
     */
    public void removeAndShow(ObservableList<String> chips){
        chips.remove(chips.size()-1);
        viewInput1.showAlert();
    }
    public void removeAndShow(String add){
        JFXChipView<String> toRemoved = viewInput2.getWords().get(Optional.ofNullable(add));
        toRemoved.getChips().remove(toRemoved.getChips().size()-1);
        viewInput2.showAlert();
    }
    /**
     * Add the words to the test word model.
     * @param toAdd
     * @param input
     * @return
     */
    @Override
    public boolean addWordsTestWord(String toAdd, List<? extends String> input) {
        String[] toAddParsed = toAdd.split(" ");
        String add = toAddParsed[2];
        Iterator<String> iterator = (Iterator<String>) input.iterator();
        if(!testWords.getTestWords().containsKey(add)){
            boolean sucessfull = true;
            while (iterator.hasNext()) {
                sucessfull = sucessfull && testWords.addWord(add, iterator.next());
            }
            if (!sucessfull) {
                removeAndShow(add);
            }
        }
        else if (testWords.getTestWords().get(add).size() < 10) {
            boolean sucessfull = true;
            while (iterator.hasNext()) {
                sucessfull = sucessfull && testWords.addWord(add, iterator.next());
            }
            if (!sucessfull) {
                System.out.println("F");
                removeAndShow(add);
            }
        }
        else{
            return false;
            }

        return false;
    }

    @Override
    public boolean removeWordsTestWord(String toRemove, List<? extends String> removed) {
        String[] toAddParsed = toRemove.split(" ");
        String remove = toAddParsed[2];
        Iterator<String> iterator = (Iterator<String>) removed.iterator();
        boolean sucessfull = true;
        while(iterator.hasNext()){
            sucessfull = sucessfull && testWords.removeWord(remove,iterator.next());
        }
        if(!sucessfull){
            viewInput2.showAlert();
        }
        return false;
    }

    /**
     * Makes the next button visible/invisible. If both have size 2, it is visible otherwise invisible.
     */
    public void setButtonVisibility(){
        if(testAttributes.getAttributes().size() == 2 && testTargets.getTargets().size() == 2){
            viewInput1.next_visible();
        }
        else{
            viewInput1.next_hide();
        }
    }

    /**
     * Makes the next button visible/invisible. If each test word has size 3 it is visible. Otherwise hidden.
     */
    @Override
    public void setWordButtonVisibility() {
        boolean isThree = true;
        for(HashSet<String> set : testWords.getTestWords().values()){
            if(!set.isEmpty())
                isThree = isThree && set.size() >= 3;
            else
                isThree = false;
        }
        if(isThree && testWords.getTestWords().values().size() == 4){
            viewInput2.next_visible();
        }
        else{
            viewInput2.next_hide();
        }
    }

    /**
     * Change to the next scene
     */
    @Override
    public void next() {
        viewInput2.init_labels();
        pane_view2 = viewInput2.getPane();
        removeChips();
        scene.setRoot(pane_view2);
    }

    /**
     * Change to the previous scene
     */
    @Override
    public void previous() {
        scene.setRoot(pane);
    }
}
