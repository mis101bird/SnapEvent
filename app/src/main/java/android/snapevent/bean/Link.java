package android.snapevent.bean;

import org.simpleframework.xml.Attribute;

/**
 * Created by ser on 2015/10/2.
 */
public class Link {
    @Attribute(name="href",required = true)
    private String url;

    public String getUrl() {
        return url;
    }
}
