package android.snapevent.volleyResponse;

import android.content.Context;

import com.android.volley.Response;

/**
 * Created by ser on 2015/9/15.
 */
public abstract class ListenerAndContext<E> implements Response.Listener<E>{
    Context currendActivity=null;

    public ListenerAndContext(Context m) {
       currendActivity=m;
    }
    public Context getCurrendActivity(){
        return currendActivity;
    }

}
