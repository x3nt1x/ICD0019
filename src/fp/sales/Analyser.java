package fp.sales;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser
{
    private final List<Entry> entries;

    public Analyser(Repository repository)
    {
        this.entries = repository.getEntries();
    }

    public Double getTotalSales()
    {
        return entries.stream().mapToDouble(Entry::getAmount).sum();
    }

    public Double getSalesByCategory(String category)
    {
        return entries.stream().filter(entry -> entry.getCategory().equals(category)).mapToDouble(Entry::getAmount).sum();
    }

    public Double getSalesBetween(LocalDate start, LocalDate end)
    {
        return entries.stream()
                      .filter(entry -> entry.getDate().isAfter(start) && entry.getDate().isBefore(end))
                      .mapToDouble(Entry::getAmount)
                      .sum();
    }

    public String mostExpensiveItems()
    {
        return String.join(", ", entries.stream()
                     .sorted(Comparator.comparing(Entry::getAmount).reversed())
                     .limit(3)
                     .map(Entry::getProductId).sorted()
                     .toList());
    }

    public String statesWithBiggestSales()
    {
        return String.join(", ", entries.stream()
                     .collect(Collectors.toMap(Entry::getState, Entry::getAmount, Double::sum)).entrySet().stream()
                     .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                     .limit(3)
                     .map(Map.Entry::getKey)
                     .toList());
    }
}