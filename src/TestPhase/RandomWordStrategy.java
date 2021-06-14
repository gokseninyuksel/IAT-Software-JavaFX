package TestPhase;

import InputPhase.TestAttributes;
import InputPhase.TestTargets;
import InputPhase.TestWords;

import java.util.Random;

public abstract class RandomWordStrategy {
    protected TestWords testWords = TestWords.getTestWordsInstance();
    protected Random random;
    public abstract String pool();
}
