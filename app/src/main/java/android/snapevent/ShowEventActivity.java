package android.snapevent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hsuan-ju on 2015/10/8.
 */
public class ShowEventActivity extends AppCompatActivity {

    LinearLayout layout;
    TextView infotitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showevent_activity);
        layout = (LinearLayout) this.findViewById(R.id.show_mark);
        infotitle = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true); //BACK botton which just available on Android 5.0, and have to set parentActivity.

        Bundle extras = getIntent().getExtras();

        String myURL = extras.getString("url");
        infotitle.setText(extras.getString("title"));

        WebView myBrowser=(WebView)findViewById(R.id.webView);

        WebSettings websettings = myBrowser.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setJavaScriptEnabled(true);

        myBrowser.setWebViewClient(new WebViewClient());
        myBrowser.loadUrl(myURL);

    }

}
