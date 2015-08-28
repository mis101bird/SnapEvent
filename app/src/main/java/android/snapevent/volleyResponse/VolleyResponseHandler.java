package android.snapevent.volleyResponse;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by ser on 2015/8/26.
 * Goal is to transfer json/xml info into Bean when it receives the response.
 */
public abstract class VolleyResponseHandler implements Response.Listener,Response.ErrorListener{

    @Override
    public abstract void onErrorResponse(VolleyError error);


    @Override
    public abstract void onResponse(Object response);


    public Response.Listener getSuccessResponse(){
        return (Response.Listener)this;
    }
    public Response.ErrorListener getErrorResponse(){
        return (Response.ErrorListener)this;
    }

}
