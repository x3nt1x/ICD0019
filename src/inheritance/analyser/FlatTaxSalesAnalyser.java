package inheritance.analyser;

import java.util.List;

public final class FlatTaxSalesAnalyser extends AbstractTaxSalesAnalyser
{
    public FlatTaxSalesAnalyser(List<SalesRecord> records)
    {
        super(records);
    }

    @Override
    protected double getRegularTax()
    {
        return 1.2;
    }

    @Override
    protected double getReducedTax()
    {
        return 1.2;
    }
}