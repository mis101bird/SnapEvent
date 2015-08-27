package android.snapevent;

import android.graphics.Bitmap;

/**
 * Created by ser on 2015/8/26.
 */
public class EventBean {
    public String time;
    public String location;
    public String img_url;
    public Bitmap img=null;
    public String description;
    public String title;

    public EventBean(String time, String location, String img_url,String description, String title) {
        this.time = time;
        this.location = location;
        this.img_url = img_url;
        this.description = description;
        this.title = title;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
