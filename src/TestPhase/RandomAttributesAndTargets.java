package TestPhase;

import InputPhase.TestAttributes;
import InputPhase.TestTargets;
import InputPhase.TestWords;

import java.util.*;

public class RandomAttributesAndTargets extends RandomWordStrategy {
    private TestAttributes testAttributes;
    private TestTargets testTargets;

    public RandomAttributesAndTargets(){
        random.setSeed(300);
        this.testAttributes = TestAttributes.getTestAttributesInstance();
        this.testTargets = TestTargets.getTargetInstance();
    }
    /**
     * Get a random word from the attribute and target word set.
     * @return random word
     */
    @Override
    public String pool() {
        Iterator<String> attributeIterator = testAttributes.getAttributes().iterator();
        Iterator<String> targetIterator = testTargets.getTargets().iterator();
        List<String> randomList = new LinkedList<>();
        while(attributeIterator.hasNext()) {
            HashSet<String> words = testWords.getTestWords().get(attributeIterator.next());
            randomList.addAll(words);
        }
        while(targetIterator.hasNext()) {
            HashSet<String> words = testWords.getTestWords().get(targetIterator.next());
            randomList.addAll(words);
        }
        return randomList.get(random.nextInt(randomList.size()));
    }
}
