package at.kaindorf.visitor;

import java.io.File;
import java.nio.file.Path;

public class DirectorySizeVisitor implements IDirectoryVisitor {
    private int xmlFiles = 0;
    private int dirs = 0;

    @Override
    public void enterDirectory(Path dir) {
        dirs++;
    }

    @Override
    public void leaveDirectory(Path dir) {
    }

    @Override
    public void visitFile(Path file) {
        if (file.toFile().getName().endsWith(".xml")) xmlFiles++;
    }

    public int getXmlFiles() {
        return xmlFiles;
    }

    public int getDirs() {
        return dirs;
    }
}
