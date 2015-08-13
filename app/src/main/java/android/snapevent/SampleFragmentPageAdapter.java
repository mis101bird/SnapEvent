package android.snapevent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.karim.MaterialTabs;

/**
 * Created by ser on 2015/8/13.
 */
public class SampleFragmentPageAdapter extends FragmentPagerAdapter implements MaterialTabs.CustomTabProvider {

    private final int[] ICONS = {R.drawable.ic_search_black_24dp, R.drawable.ic_reorder_black_24dp};

    private Fragment flist[]=new Fragment[]{new MyFragment(),new MyFragment()};
    private Context context;

    public SampleFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
      return flist[position];
    }


    @Override
    public View getCustomTabView(ViewGroup parent, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(context.getResources().getDrawable(ICONS[position]));
        return imageView;
    }

    private class MyFragment extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.sample_fragmentpage, container, false);
            return view;
        }

    }



}
