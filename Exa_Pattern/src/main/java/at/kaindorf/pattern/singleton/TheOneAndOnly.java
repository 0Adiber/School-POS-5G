package at.kaindorf.pattern.singleton;

public class TheOneAndOnly {

    private static TheOneAndOnly instance;

    public static TheOneAndOnly getInstance() {
        if(instance == null)
            instance = new TheOneAndOnly();
        return instance;
    }

    private TheOneAndOnly() {

    }

}
