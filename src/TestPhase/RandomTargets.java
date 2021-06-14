package TestPhase;

import InputPhase.TestAttributes;
import InputPhase.TestTargets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RandomTargets extends RandomWordStrategy{
    private TestTargets testTargets;
    /**
     * Constructor for random attributes
     */
    public RandomTargets(){
        random.setSeed(100);
        this.testTargets = TestTargets.getTargetInstance();
    }

    /**
     * Get a random word from the attribute word set.
     * @return
     */
    @Override
    public String pool() {
        Iterator<String> targetIterator = testTargets.getTargets().iterator();
        List<String> randomList = new LinkedList<>();
        while(targetIterator.hasNext()) {
            HashSet<String> words = testWords.getTestWords().get(targetIterator.next());
            randomList.addAll(words);
        }
        return randomList.get(random.nextInt(randomList.size()));
    }
}
