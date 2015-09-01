package android.snapevent.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.snapevent.VolleyTAG;
import android.snapevent.bean.xmlBeanList;
import android.snapevent.bean.xmlEventBean;
import android.snapevent.volleyResponse.SimpleXmlRequest;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by hsuan-ju on 2015/8/26.
 */
public class AppController extends Application{
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue eventRequestQueue;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        Log.i("requestQueue", "Into GET requestQueue");
        if (eventRequestQueue == null) {
            eventRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return eventRequestQueue;
    }

    public void GetKKTIXRequestToEventBean(Context context){

        cancelPendingRequests(VolleyTAG.KKTIX_ALL.getTAG()); //cancel the past request

        if(ifInternetOpen(context)) {

            SimpleXmlRequest<xmlBeanList> simpleRequest = new SimpleXmlRequest<xmlBeanList>(Request.Method.GET, AppRemoteConfig.getInstance().getKKTIX_ALL_url(), xmlBeanList.class,
                    new Response.Listener<xmlBeanList>() {
                        @Override
                        public void onResponse(xmlBeanList response) {
                            List<xmlEventBean> datas = response.getMatches();
                            for (xmlEventBean bean : datas) {
                                Log.i("success", "title: " + bean.getTitle() + " and " + bean.getAuthor().getName());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("error", error.getMessage());
                        }
                    }
            );
            AppController.getInstance().addToRequestQueue(simpleRequest, VolleyTAG.KKTIX_ALL.getTAG());
        }else{
            Log.i("Internet Error","user phone didn't open internet.");
        }

    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        Log.i("addToRequestQueue","tag mame: " + tag);
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }


    public void cancelPendingRequests(String tag) {
        Log.i("cancelPendingRequests", "tag mame: " + tag);
        if (eventRequestQueue != null) {
            eventRequestQueue.cancelAll(tag);
        }
    }

    public boolean ifInternetOpen(Context context){

        final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( wifi.isAvailable() || mobile.isAvailable()){
            return true;
        }
        else{
            Toast.makeText(context, "請開起網路連線" , Toast.LENGTH_LONG).show();
            return false;
        }


    }

}
