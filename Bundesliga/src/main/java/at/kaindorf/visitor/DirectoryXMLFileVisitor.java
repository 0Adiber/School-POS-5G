package at.kaindorf.visitor;

import lombok.Data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
public class DirectoryXMLFileVisitor implements IDirectoryVisitor {
    private List<Path> xmlFileList = new ArrayList<>();

    @Override
    public void enterDirectory(Path dir) {
    }

    @Override
    public void leaveDirectory(Path dir) {
    }

    @Override
    public void visitFile(Path file) {
        if (file.toFile().getName().endsWith(".xml")) xmlFileList.add(file);
    }
}
