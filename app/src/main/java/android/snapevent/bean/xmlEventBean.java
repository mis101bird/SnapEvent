package android.snapevent.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public class xmlEventBean {

    @Root(name="entry",strict = false)

    @Element(name = "title", required = true)
    private String title;

    @Element(name="link",required = true)
    public class Link{
        @Attribute(name="href",required = true)
        private String url;
    }
    @Element(name = "summary", required = false)
    private String summary;

    @Element(name = "content", required = true)
    private String timeANDplace;

    @Element(name = "author", required = false)
    private Author author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTimeANDplace() {
        return timeANDplace;
    }

    public void setTimeANDplace(String timeANDplace) {
        this.timeANDplace = timeANDplace;
    }

    public Author getAuthor() {
        return author;
    }
}
