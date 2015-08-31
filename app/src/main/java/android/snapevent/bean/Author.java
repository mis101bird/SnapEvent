package android.snapevent.bean;

import org.simpleframework.xml.Element;

public class Author{

    @Element(name="name",required = true)
    private String name;
    @Element(name="url",required = false)
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
