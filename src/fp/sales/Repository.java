package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Repository
{
    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries()
    {
        var entries = new ArrayList<Entry>();

        try
        {
            for (var line : Files.readAllLines(Paths.get(FILE_PATH)).stream().skip(1).toList())
            {
                var data = line.split("\t");

                var id = data[2];
                var category = data[3];
                var amount = Double.valueOf(data[5].replace(",", "."));
                var state = data[1];
                var date = LocalDate.parse(data[0], formatter);

                entries.add(new Entry(id, category, amount, state, date));
            }
        }
        catch (IOException exception)
        {
            throw new RuntimeException("Error during reading from the file");
        }

        return entries;
    }
}