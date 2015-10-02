package android.snapevent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.snapevent.bean.xmlEventBean;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by ser on 2015/10/2.
 */
public class EventItemListener implements AdapterView.OnItemClickListener{

    List<xmlEventBean> beans;
    Activity activity;

    public EventItemListener(List beans,Activity activity) {
        this.beans=beans;
        this.activity=activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        if(beans.get(pos).getLink().getUrl().substring(0,9).equals("<![CDATA[")) {
            i.setData(Uri.parse(beans.get(pos).getLink().getUrl().substring(9, beans.get(pos).getLink().getUrl().length() - 3)));
        }
        else
        {
            i.setData(Uri.parse(beans.get(pos).getLink().getUrl()));

        }
        activity.startActivity((i)); //start intent to Activity
    }

}
