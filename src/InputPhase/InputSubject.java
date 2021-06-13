package InputPhase;

public interface InputSubject {
    void addObserver(InputObserver observer);
    void removeObserver(InputObserver observer);
    void notifyObserver();
}
