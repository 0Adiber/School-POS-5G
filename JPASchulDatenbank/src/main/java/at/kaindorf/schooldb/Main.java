package at.kaindorf.schooldb;

import at.kaindorf.schooldb.console.DataHandler;

public class Main {

    public static void main(String[] args) {
        DataHandler dh = new DataHandler();
        dh.open();
        dh.importTables();
    }
}
