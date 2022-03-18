package at.kaindorf.pattern.observer;

public class ConcreteSubjuct extends Subject{

    private String data;

    @Override
    public void notifiyObservers() {
        for(Observer observer : observers)
            observer.update(data);
    }

}
