package at.kaindorf.visitor;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class SearchXMLFiles {
    private Path workingDirectory;
    private int files;
    private int directories;

    public void searchXmlFiles(Path workDir) {
        // size visitor
        DirectorySizeVisitor visitor = new DirectorySizeVisitor();
        traverse(workDir, visitor);
        System.out.format("Directories: %d\n" +
                "XML Files: %d", visitor.getDirs(), visitor.getXmlFiles());
    }

    public List<Path> getXmlFiles() {
        DirectoryXMLFileVisitor visitor = new DirectoryXMLFileVisitor();
        traverse(workingDirectory, visitor);
        return visitor.getXmlFileList();
    }

    private void traverse(Path dir, IDirectoryVisitor visitor) {
        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException(dir.toFile().getName() + " is not a directory!");
        }
        File[] dirContent = dir.toFile().listFiles();
        visitor.enterDirectory(dir);
        for (File file : dirContent) {
            if (file.isDirectory()) {
                traverse(file.toPath(), visitor);
            } else {
                visitor.visitFile(file.toPath());
            }
        }
        visitor.leaveDirectory(dir);
    }

    public Path getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
}
