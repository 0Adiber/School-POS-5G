package at.kaindorf.singleton;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Tournament;

import javax.xml.bind.JAXB;
import java.nio.file.Path;

public class XmlDal {
    private static XmlDal instance;

    public static XmlDal getInstance() {
        if (instance == null) instance = new XmlDal();

        return instance;
    }

    private XmlDal() {
    }

    public Tournament loadTournament(Path path) {
        return JAXB.unmarshal(path.toFile(), Tournament.class);
    }

    public Game loadGame(Path path) {
        return JAXB.unmarshal(path.toFile(), Game.class);
    }

    public void saveTournament(Tournament t, Path path) {
        JAXB.marshal(t, path.toFile());
    }
}