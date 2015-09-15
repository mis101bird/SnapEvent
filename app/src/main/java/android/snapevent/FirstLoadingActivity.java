package android.snapevent;

import android.app.Activity;
import android.os.Bundle;
import android.snapevent.app.AppController;

/**
 * Created by Hsuan-Ju on 2015/9/15.
 */
public class FirstLoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
        AppController.getInstance().GetKKTIXRequestToEventBean(FirstLoadingActivity.this);
    }

}
