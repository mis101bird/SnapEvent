package android.snapevent.volleyResponse;

import android.util.Log;

import com.android.volley.VolleyError;

/**
 * Created by hsuan-ju on 2015/8/27.
 */
public class KKTIXresponseHandler extends VolleyResponseHandler{


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("error",error.getMessage());
    }

    @Override
    public void onResponse(Object response) {
        Log.i("success",response.toString());
    }
}
