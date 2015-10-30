package android.snapevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.snapevent.bean.xmlEventBean;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by ser on 2015/10/2.
 */
public class EventItemListener implements AdapterView.OnItemClickListener{

    List<xmlEventBean> beans=null;
    xmlEventBean bean=null;
    Context activity;

    public EventItemListener(List beans,Context activity) {
        this.beans=beans;
        this.activity=activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        Intent i = new Intent(activity,ShowEventActivity.class);
        if(beans!=null) {
            if (beans.get(pos).getLink().getUrl().substring(0, 9).equals("<![CDATA[")) {
                i.putExtra("url", beans.get(pos).getLink().getUrl().substring(9, beans.get(pos).getLink().getUrl().length() - 3));

            } else {
                i.putExtra("url", beans.get(pos).getLink().getUrl());
            }
            i.putExtra("title", beans.get(pos).getTitle());
        }
        activity.startActivity(i); //start intent to Activity
    }

}
