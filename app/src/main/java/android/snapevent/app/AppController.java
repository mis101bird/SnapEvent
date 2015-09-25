package android.snapevent.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.snapevent.MainActivity;
import android.snapevent.R;
import android.snapevent.VolleyTAG;
import android.snapevent.bean.xmlBeanList;
import android.snapevent.bean.xmlEventBean;
import android.snapevent.volleyResponse.ErrorListenerAndContext;
import android.snapevent.volleyResponse.ListenerAndContext;
import android.snapevent.volleyResponse.SimpleXmlRequest;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hsuan-ju on 2015/8/26.
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue eventRequestQueue;
    private static AppController mInstance;
    private static HashMap<String, List> eventbeans = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public HashMap<String,List> getEventbeans(){
        return eventbeans;
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

    public void GetKKTIXRequestToEventBean(Context context) {

        cancelPendingRequests(VolleyTAG.KKTIX_ALL.getTAG()); //cancel the past request

        if (ifInternetOpen(context)) {

            SimpleXmlRequest<xmlBeanList> simpleRequest = new SimpleXmlRequest<xmlBeanList>(Request.Method.GET, AppRemoteConfig.getInstance().getKKTIX_ALL_url(), xmlBeanList.class,
                    new ListenerAndContext<xmlBeanList>(context) {

                        @Override
                        public void onResponse(xmlBeanList response) {
                            List<xmlEventBean> datas = response.getMatches();
                            eventbeans.put("KKTIX", datas);
                            Log.i("success", "title: " + datas.get(0).getTitle() + " and " + datas.get(0).getAuthor().getName());
                            View rootView = ((Activity)getCurrendActivity()).getWindow().getDecorView();
                            ((ImageView)rootView.findViewById(R.id.topup)).setVisibility(View.GONE);
                        }
                    },
                    new ErrorListenerAndContext(context) {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(this.getCurrendActivity(), "請檢察wifi認證或請重開App", Toast.LENGTH_LONG).show();
                            Log.i("error", error.getMessage());
                        }
                    }
            );
            AppController.getInstance().addToRequestQueue(simpleRequest, VolleyTAG.KKTIX_ALL.getTAG());
        } else {
            Log.i("Internet Error", "user phone didn't open internet.");
        }

    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        Log.i("addToRequestQueue", "tag mame: " + tag);
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }


    public void cancelPendingRequests(String tag) {
        Log.i("cancelPendingRequests", "tag mame: " + tag);
        if (eventRequestQueue != null) {
            eventRequestQueue.cancelAll(tag);
        }
    }

    public boolean ifInternetOpen(Context context) {

        final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable()) {
            return true;
        } else {
            Toast.makeText(context, "請開起手機網路才能使用本App", Toast.LENGTH_LONG).show();
            return false;
        }


    }

}
