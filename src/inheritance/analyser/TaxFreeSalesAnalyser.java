package inheritance.analyser;

import java.util.List;

public final class TaxFreeSalesAnalyser extends AbstractTaxSalesAnalyser
{
    public TaxFreeSalesAnalyser(List<SalesRecord> records)
    {
        super(records);
    }

    @Override
    protected double getRegularTax()
    {
        return 1;
    }

    @Override
    protected double getReducedTax()
    {
        return 1;
    }
}