package inheritance.analyser;

import junit.framework.AssertionFailedError;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SalesAnalyserTest
{
    List<SalesRecord> records = List.of(
            new SalesRecord("i1", 1, 12),
            new SalesRecord("i2", 2, 24),
            new SalesRecord("i1", 1, 6),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i4", 24, 2, true));

    @Test
    public void calculatesTotalSalesWithFlatTaxRate()
    {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getTotalSales(), is(closeTo(295)));
    }

    @Test
    public void calculatesTotalSalesByProductIdWithFlatTaxRate()
    {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getTotalSalesByProductId("i1"), is(closeTo(15)));
    }

    @Test
    public void calculatesTotalSalesWithTaxFreeRate()
    {
        TaxFreeSalesAnalyser analyser = new TaxFreeSalesAnalyser(records);

        assertThat(analyser.getTotalSales(), is(closeTo(354)));
    }

    @Test
    public void calculatesTotalSalesByProductIdWithTaxFreeRate()
    {
        TaxFreeSalesAnalyser analyser = new TaxFreeSalesAnalyser(records);

        assertThat(analyser.getTotalSalesByProductId("i1"), is(closeTo(18)));
    }

    @Test
    public void calculatesTotalSalesWithDifferentiatedTaxRate()
    {
        DifferentiatedTaxSalesAnalyser analyser = new DifferentiatedTaxSalesAnalyser(records);

        assertThat(analyser.getTotalSales(), is(closeTo(298.6)));
    }

    @Test
    public void findsMostPopularSalesItem()
    {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getIdOfMostPopularItem(), is("i2"));
    }

    @Test
    public void findsTheItemWithTheLargestTotalSales()
    {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getIdOfItemWithLargestTotalSales(), is("i5"));
    }

    @Test
    public void allAnalysersHaveCommonAbstractSuperclass()
    {
        Class<?> s1 = FlatTaxSalesAnalyser.class.getSuperclass();
        Class<?> s2 = TaxFreeSalesAnalyser.class.getSuperclass();
        Class<?> s3 = DifferentiatedTaxSalesAnalyser.class.getSuperclass();

        assertThat(s1.getName(), is(s2.getName()));
        assertThat(s1.getName(), is(s3.getName()));

        assertTrue("Superclass should be abstract", Modifier.isAbstract(s1.getModifiers()));
    }

    @Test
    public void commonSuperclassIsSealed()
    {
        assertTrue("Superclass should be sealed", FlatTaxSalesAnalyser.class.getSuperclass().isSealed());
    }

    @Test
    public void specificAnalysersHaveOnlyMinimalCode()
    {
        assertThat(FlatTaxSalesAnalyser.class.getDeclaredMethods().length, lessThanOrEqualTo(2));
        assertThat(TaxFreeSalesAnalyser.class.getDeclaredMethods().length, lessThanOrEqualTo(2));
        assertThat(DifferentiatedTaxSalesAnalyser.class.getDeclaredMethods().length, lessThanOrEqualTo(2));
    }

    @Test
    public void specificAnalysersHaveOnlyProtectedMethods()
    {
        assertContainsOnlyProtectedMethods(FlatTaxSalesAnalyser.class);
        assertContainsOnlyProtectedMethods(TaxFreeSalesAnalyser.class);
        assertContainsOnlyProtectedMethods(DifferentiatedTaxSalesAnalyser.class);
    }

    private void assertContainsOnlyProtectedMethods(Class<?> clazz)
    {
        for (Method method : clazz.getDeclaredMethods())
        {
            if (!Modifier.isProtected(method.getModifiers()))
            {
                throw new AssertionFailedError(String.format("Class %s contains non protected methods", clazz.getName()));
            }
        }
    }

    private Matcher<Double> closeTo(double value)
    {
        double precision = 0.1;

        return Matchers.closeTo(value, precision);
    }
}