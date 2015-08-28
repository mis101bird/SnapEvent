package android.snapevent.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by hsuan-ju on 2015/8/28.
 */
public class xmlBeanList {

    @Root(name="feed",strict = false)

        @ElementList(entry = "entry", inline = true)
        private List<xmlEventBean> beans;

        public xmlBeanList() {
        }

        public List<xmlEventBean> getMatches() {
            return beans;
        }

    @Root(name="entry",strict = false)
    public static class xmlEventBean {

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
        public class Author{
            @Element(name="name",required = true)
            private String name;
            @Element(name="url",required = false)
            private String url;
        }

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



    }

}

