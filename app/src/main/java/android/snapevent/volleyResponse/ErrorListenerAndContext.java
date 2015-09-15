package android.snapevent.volleyResponse;

import android.content.Context;

import com.android.volley.Response;

/**
 * Created by ser on 2015/9/15.
 */
public  abstract class ErrorListenerAndContext implements Response.ErrorListener{
    Context currendActivity=null;

    public ErrorListenerAndContext(Context m) {
        currendActivity=m;
    }
    public Context getCurrendActivity(){
        return currendActivity;
    }
}
