package ibby.core.initializer;

public enum SystemProperties {
    BROWSER_TYPE("ibby.browserName"),
    URL("ibby.url"),
    TIMEOUT("ibby.timeout");

    private String systemName;

    SystemProperties(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemName() {
        return systemName;
    }
}
