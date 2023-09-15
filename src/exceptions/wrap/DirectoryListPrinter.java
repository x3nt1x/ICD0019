package exceptions.wrap;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryListPrinter
{
    public void printDirectoryList()
    {
        var currentDirectory = Paths.get(".");

        for (var item : getDirectoryFileList(currentDirectory))
        {
            var type = item.toFile().isDirectory() ? "dir" : "file";

            System.out.printf("%s, (%s) %s", item, type, System.lineSeparator());
        }
    }

    private DirectoryStream<Path> getDirectoryFileList(Path currentDirectory)
    {
        try {
            return Files.newDirectoryStream(currentDirectory);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}