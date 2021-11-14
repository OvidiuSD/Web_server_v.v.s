package web.server.configuration;

public class Config {
    private int port;
    private String webroot;

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getWebRoot() {
        return webroot;
    }
    public void setWebRoot(String webroot) {
        this.webroot = webroot;
    }
}
