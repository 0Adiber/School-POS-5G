package at.kaindorf.observer;

import at.kaindorf.pojos.Team;
import at.kaindorf.singleton.Calculator;

import java.util.ArrayList;
import java.util.List;

public class Console implements IObserver {
    @Override
    public void update() {
        List<Team> teams = new ArrayList<>(Calculator.getInstance().generateTable());

        int longest = 0;
        for (Team t : teams) {
            if (t.getName().length() > longest) {
                longest = t.getName().length();
            }
        }

        String header = "\n   Team";
        header += " ".repeat(longest - 3);
        header += "Sp   T Pt";
        System.out.println(header);
        for (Team t : teams) {
            t.setName(t.getName() + " ".repeat(longest - t.getName().length()));
            System.out.format("%2d %s %2d %3d %2d\n", t.getPosition(), t.getName(), t.getPlayed(), t.getGd(), t.getPts());
        }
    }
}
