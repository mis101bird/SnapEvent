package android.snapevent;

import android.content.Context;
import android.os.Bundle;
import android.snapevent.app.AppController;
import android.snapevent.app.AppRemoteConfig;
import android.snapevent.bean.xmlBeanList;
import android.snapevent.volleyResponse.SimpleXmlRequest;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsuan-ju on 2015/8/10.
 */
public class MainActivity extends AppCompatActivity {

    AppController controller=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);


        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        //ab.setDisplayHomeAsUpEnabled(true);
        List<Fragment> fl=new ArrayList<Fragment>();
        fl.add(new FirstPage());
        fl.add(new ListPage());
      ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            viewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager(), fl , MainActivity.this));
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        AppController.getInstance().GetKKTIXRequestToEventBean(MainActivity.this); //Loading online events data
    }

}
