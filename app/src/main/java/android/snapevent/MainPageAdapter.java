package android.snapevent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.List;

/**
 * Created by ser on 2015/8/12.
 */
public class MainPageAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[] { "Search", "All" };
    private int[] imageResId = {
            R.drawable.ic_search_black_24dp,
            R.drawable.ic_reorder_black_24dp,
    };
    private Context context;
    List<Fragment> fragments;

    public MainPageAdapter(FragmentManager fm , List<Fragment> f,Context context) {
        super(fm);
        this.context=context;
        fragments=f;

    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
