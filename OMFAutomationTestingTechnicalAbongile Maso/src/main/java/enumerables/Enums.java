package enumerables;

public class Enums
{
    public enum LocatorType
    {
        ID, NAME, XPATH, PARTIAL_LINK_TEXT, LINK_TEXT, CSS, CLASS_NAME
    }

    public enum BrowserTypes
    {
        Chrome,
        FireFox
    }

    public enum Environment
    {
        Dev("https://www.oldmutualfinance.co.za/"),
        UAT(""),
        Prod("");

        public final String urlUnderTest;


        Environment(String environmentURL)
        {
            this.urlUnderTest = environmentURL;
        }

    }
}
