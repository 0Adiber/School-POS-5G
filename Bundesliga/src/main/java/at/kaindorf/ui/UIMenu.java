package at.kaindorf.ui;

import at.kaindorf.observer.Console;
import at.kaindorf.observer.Html;
import at.kaindorf.observer.IObserver;
import at.kaindorf.observer.Subject;
import at.kaindorf.singleton.Calculator;
import at.kaindorf.singleton.XmlDal;
import at.kaindorf.visitor.SearchXMLFiles;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UIMenu extends Subject {
    private static final Path tournamentPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", "bundesliga2122.xml");

    public static void main(String[] args) {
        UIMenu ui = new UIMenu();
        Html html = new Html();
        Console console = new Console();
        ui.register(html);
        ui.register(console);

        if (args.length != 1)
            throw new IllegalArgumentException("invalid amount of arguments supplied (supplied: " + args.length + "; needed: 1)");

        ui.startMenu(args[0]);

        ui.unregister(html);
        ui.unregister(console);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update();
        }
    }

    public void startMenu(String argDir) {
        Scanner scan = new Scanner(System.in);
        do {

            System.out.print("0 ... Beenden" +
                    "\n1 ... Ergebnis hinzufügen" +
                    "\n2 ... Daten von der Datei 'bundesliga2122.xml' laden" +
                    "\n3 ... Daten vom Verzeichnis 'args[0]' Verzeichnis laden" +
                    "\n4 ... Duplikate entfernen (mit WHILE Schleife)" +
                    "\n5 ... Duplikate entfernen (mit Java-Stream)" +
                    "\nWahl [0-5] = ");

            int choice = scan.nextInt();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    break;
                case 2:
                    Calculator.getInstance().addDataFromTournamentObject(XmlDal.getInstance().loadTournament(tournamentPath));
                    break;
                case 3:
                    SearchXMLFiles search = new SearchXMLFiles();
                    search.setWorkingDirectory(Paths.get(argDir));
                    Calculator.getInstance().addDataFromXmlFiles(search.getXmlFiles());
                    break;
                case 4:
                    Calculator.getInstance().removeDuplicates1();
                    break;
                case 5:
                    Calculator.getInstance().removeDuplicates2();
                    break;
            }

            this.notifyObservers();
        } while (true);
    }
}
