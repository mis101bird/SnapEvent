package android.snapevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by hsuan-ju on 2015/9/24.
 */
public class LoadingListView extends ListView implements AbsListView.OnScrollListener{

    View foot;
    boolean loading=false;
    int totalItemCount=0;
    int endVisibleItem=0;
    InLoad load;
    public LoadingListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public LoadingListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    /**
     * Loading the foot layout part.
     * @param context
     */
    private void initView(Context context){
        LayoutInflater layout = LayoutInflater.from(context);
        foot = layout.inflate(R.layout.listview_foot,null);
        this.addFooterView(foot);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.i("onScrollStateChanged","endVisibleItem: "+endVisibleItem+"and totalItemCount: "+totalItemCount);
      if(endVisibleItem==totalItemCount && scrollState==OnScrollListener.SCROLL_STATE_IDLE){
          if(!loading){
              loading=true;
              foot.findViewById(R.id.Loading).setVisibility(View.VISIBLE);
              //Add more datas
              load.onLoad();
              Log.i("onScrollStateChanged","in loading.");
          }

      }
    }

    @Override
    public void onScroll(AbsListView view, int VisibleItem, int visibleItemCount, int totalItemCount) {

       this.endVisibleItem=VisibleItem+visibleItemCount;
       this.totalItemCount=totalItemCount;
    }

    public void setInLoad(InLoad load){
        this.load=load;
    }
    public interface InLoad{
       public void onLoad();
    }
    public void completeLoading(){
        loading=false;
        foot.findViewById(R.id.Loading).setVisibility(View.GONE);
    }
}
