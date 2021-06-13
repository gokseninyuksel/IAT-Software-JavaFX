package InputPhase;

import java.util.List;

public interface ControllerInput {
    void addWordsAttributes(List<? extends String> input);
    void removeWordsAttributes(List<? extends String> input);
    void addWordsTargets(List<? extends String> input);
    void removeWordsTargets(List<? extends String> input);
    boolean addWordsTestWord(String toAdd, List<? extends String> input);
    boolean removeWordsTestWord(String toRemove, List<? extends String> removed);
    void setButtonVisibility();
    void setWordButtonVisibility();
    void next();
    void previous();
}
