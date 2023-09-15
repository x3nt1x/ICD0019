package exceptions.translate;

import org.junit.Test;

public class ConfigurationLoaderTests
{
    @Test
    public void exceptionTranslationExample()
    {
        var configPath = "./";

        new ConfigurationLoader().loadConfiguration(configPath);
    }
}