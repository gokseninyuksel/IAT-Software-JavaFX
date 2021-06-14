package TestPhase;

import InputPhase.TestAttributes;
import InputPhase.TestWords;

import java.util.*;

public class RandomAttributes extends RandomWordStrategy{
    private TestAttributes testAttributes;
    /**
     * Constructor for random attributes
     */
    public RandomAttributes(){
        random.setSeed(500);
        this.testAttributes = TestAttributes.getTestAttributesInstance();
    }

    /**
     * Get a random word from the attribute word set.
     * @return
     */
    @Override
    public String pool() {
        Iterator<String> attributeIterator = testAttributes.getAttributes().iterator();
        List<String> randomList = new LinkedList<>();
        while(attributeIterator.hasNext()) {
            HashSet<String> words = testWords.getTestWords().get(attributeIterator.next());
            randomList.addAll(words);
        }
        return randomList.get(random.nextInt(randomList.size()));
    }
}
