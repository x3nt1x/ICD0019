package exceptions.basic;

public class TryCatchSample
{
    public String readDataFrom(Resource resource)
    {
        try
        {
            resource.open();

            return resource.read();
        }
        catch (Exception e) {
            return "someDefaultValue";
        }
        finally {
            resource.close();
        }
    }
}