package generics.recursion;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Recursion
{
    public List<String> getParts(Path path)
    {
        var result = new ArrayList<String>();

        for (; path != null; path = path.getParent()) {
            result.add(path.getFileName().toString());
        }

        return result;
    }

    public List<String> getParts2(Path path)
    {
        if (path == null) {
            return null;
        }

        System.out.println(path.getFileName().toString());

        return getParts2(path.getParent());
    }

    public List<String> getParts3(Path path, List<String> parts)
    {
        if (path == null) {
            return parts;
        }

        parts.add(path.getFileName().toString());

        return getParts3(path.getParent(), parts);
    }

    public List<String> getParts4(Path path)
    {
        return getParts3(path, new ArrayList<>());
    }
}