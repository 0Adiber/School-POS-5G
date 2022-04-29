package at.kaindorf.singleton;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Team;
import at.kaindorf.pojos.Tournament;

import javax.xml.bind.JAXB;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Calculator {
    private static Calculator instance;
    private List<Game> games = new ArrayList<>();

    public static Calculator getInstance() {
        if (instance == null) instance = new Calculator();

        return instance;
    }

    private Calculator() {
    }

    public List<Game> getAllGamesByTeamName(String name) {
        return games.stream()
                .filter(g -> g.isTeamPresent(name))
                .collect(Collectors.toList());
    }

    public List<Team> generateTable() {
        Map<String, List<Game>> teamGames = new HashMap<>();
        for (Game g : games) {
            // team 1
            if (!teamGames.containsKey(g.getTeam1())) teamGames.put(g.getTeam1(), new ArrayList<>());
            teamGames.get(g.getTeam1()).add(g);

            // team 2
            if (!teamGames.containsKey(g.getTeam2())) teamGames.put(g.getTeam2(), new ArrayList<>());
            teamGames.get(g.getTeam2()).add(g);
        }

        List<Team> teams = new ArrayList<>();
        teamGames.forEach((s, tGames) -> {
            int points = 0, gs = 0, ga = 0, gd = 0, won = 0, draw = 0, loss = 0;

            for (Game g : tGames) {
                if (g.getTeam1().equals(s)) {
                    gs += g.getGoal1();
                    ga += g.getGoal2();
                    if (g.getGoal1() > g.getGoal2()) won++;
                    else if (g.getGoal1() < g.getGoal2()) loss++;
                } else {
                    gs += g.getGoal2();
                    ga += g.getGoal1();
                    if (g.getGoal2() > g.getGoal1()) won++;
                    else if (g.getGoal2() < g.getGoal1()) loss++;
                }
                // draw
                if (g.getGoal1() == g.getGoal2()) draw++;
            }
            points = won * 3 + draw;
            teams.add(new Team(s, 0, tGames.size(), won, draw, loss, gs, ga, gd, points));
        });

        List<Team> sortedTeams = teams.stream()
                .sorted(Comparator
                        .comparing(Team::getPts).reversed() // points
                        .thenComparing(Team::getGd) // goal difference
                        .thenComparing(Team::getName)) // name
                .collect(Collectors.toList());
        int i = 0;
        for (Team t : sortedTeams) {
            t.setPosition(++i);
        }

        return sortedTeams;
    }

    public void addDataFromTournamentObject(Tournament t) {
        games.addAll(t.getGames());
    }

    public void addDataFromXmlFiles(List<Path> xmlFiles) {
        for (Path p : xmlFiles) {
            if (!p.toFile().getName().startsWith("Spiel_")) continue;
            games.add(JAXB.unmarshal(p.toFile(), Game.class));
        }
    }

    public void removeDuplicates1() {
        int i = 0;
        while (i < games.size()) {
            int j = i + 1;
            while (j < games.size()) {
                if (games.get(i).equals(games.get(j))) {
                    games.remove(j);
                    j--;
                }
                j++;
            }
            i++;
        }
    }

    public void removeDuplicates2() {
        games = games.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
