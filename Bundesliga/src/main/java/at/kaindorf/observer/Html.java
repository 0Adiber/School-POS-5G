package at.kaindorf.observer;

import at.kaindorf.pojos.Team;
import at.kaindorf.singleton.Calculator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Html implements IObserver {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update() {
        Calculator calc = Calculator.getInstance();
        Path p = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "teams.html");
        // automatically close the writers
        try (FileWriter fw = new FileWriter(p.toFile().getAbsolutePath(), StandardCharsets.UTF_8);) {
            try (PrintWriter pw = new PrintWriter(fw)) {
                pw.write("<!DOCTYPE html><html>" +
                        "\n<head>" +
                            "\n<meta charset=\"UTF-8\">" +
                            "\n<title>Turnier</title>" +
                        "\n</head>" +
                        "\n<body><h1>Turnier</h1>");
                pw.write("<h2>" + LocalDateTime.now().format(DTF) + "</h2>");
                pw.write("<table>" +
                        "<thead>" +
                        "<tr><th></th><th></th><th>Sp</th><th>S</th><th>U</th><th>N</th><th>T</th><th>Pt</th></tr>" +
                        "</thead><tbody>");

                for (Team t : calc.generateTable()) {
                    pw.write("<tr>");
                    pw.write(td(t.getPosition()) +
                            td(t.getName()) +
                            td(t.getPlayed()) +
                            td(t.getWon()) +
                            td(t.getDraw()) +
                            td(t.getLoss()) +
                            td(t.getGs() + ":" + t.getGa()) +
                            td(t.getPts()));
                    pw.write("</tr>");
                }

                pw.write("</tbody></table>");

                pw.write("</body></html>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String td(Object text) {
        return "<td>" + text + "</td>";
    }
}
