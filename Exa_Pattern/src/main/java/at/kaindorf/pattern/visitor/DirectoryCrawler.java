package at.kaindorf.pattern.visitor;

import java.io.File;
import java.nio.file.Paths;

public class DirectoryCrawler {
    public void traverse(File startDir, DirectoryVisitor visitor) {
        if (!startDir.isDirectory()) {
            throw new IllegalArgumentException(startDir.getName() + " is not a directory!");
        }
        File[] dirContent = startDir.listFiles();
        visitor.enterDirectory(startDir);
        for (File file : dirContent) {
            if (file.isDirectory()) {
                traverse(file, visitor);
            }
            else {
                visitor.visitFile(file);
            }
        }
        visitor.leaveDirectory(startDir);
    }

    public static void main(String[] args) {
        DirectoryCrawler directoryCrawler = new DirectoryCrawler();
        File startDir = Paths.get(System.getProperty("user.dir")).toFile();
        directoryCrawler.traverse(startDir, new PrintDirectoryVisitor());

        SizeDirectoryVisitor sizeDirectoryVisitor = new SizeDirectoryVisitor();
        directoryCrawler.traverse(startDir, sizeDirectoryVisitor);
        System.out.println("Number of directories: " + sizeDirectoryVisitor.getDirs());
        System.out.println("Number of files: " + sizeDirectoryVisitor.getFiles());
        System.out.println("Size of files in bytes: " + sizeDirectoryVisitor.getSize());
    }
}
