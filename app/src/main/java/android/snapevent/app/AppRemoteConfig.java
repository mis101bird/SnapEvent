package android.snapevent.app;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hsuan-ju on 2015/8/26.
 */
public class AppRemoteConfig {

    private static AppRemoteConfig config=null;
    //Taiwan culture department's exhibits search
    //keyword parameter use the same day
    //public static String GOV_Exhibit_url = "http://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&keyword=";

    public String KKTIX_ALL_url="https://kktix.com/events.atom?end_at="+getTimeForKKTIX()+"&locale=zh-TW&start_at="+getTimeForKKTIX();

    public static AppRemoteConfig getInstance(){
        if(config==null){
            config=new AppRemoteConfig();
        }
        return config;
    }
    public String getKKTIX_ALL_url(){
        Log.i("getKKTIX_ALL_url",KKTIX_ALL_url);
        return KKTIX_ALL_url;
    }
    private String getTimeForKKTIX(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); //set format
        Date dt=new Date(); //get the same time
        String dts=sdf.format(dt); //turn to string format
     return dts;
    }
}