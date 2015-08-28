package android.snapevent.app;

import android.app.Application;
import android.content.Context;
import android.snapevent.volleyResponse.VolleyResponseHandler;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

    public void GetStringRequestToEventBean(String url,VolleyResponseHandler handler,String TAG,Context context){

        getRequestQueue().cancelAll(TAG);//cancel forword request first.
        StringRequest sr=new StringRequest(Request.Method.GET,url,handler.getSuccessResponse(),handler.getErrorResponse());
        addToRequestQueue(sr, TAG);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        Log.i("addToRequestQueue","tag mame: " + tag);
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        Log.i("cancelPendingRequests","tag mame: "+tag);
        if (eventRequestQueue != null) {
            eventRequestQueue.cancelAll(tag);
        }
    }


}
