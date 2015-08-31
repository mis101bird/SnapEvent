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

}


