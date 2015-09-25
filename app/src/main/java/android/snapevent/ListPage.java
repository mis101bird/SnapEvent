package android.snapevent;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.snapevent.app.AppController;
import android.snapevent.app.AppRemoteConfig;
import android.snapevent.bean.xmlEventBean;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListPage extends Fragment implements LoadingListView.InLoad {

    private LoadingListView listView;
    ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
    private SimpleAdapter adapter;
    private int adapter_data_count=0;
    HashMap events =null;
    List<xmlEventBean> datalist = null;
    public ListPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       adapter = new SimpleAdapter(
                getActivity(),
                list, R.layout.listview_layout,
                new String[] { "title","image","detail"},new int[] { R.id.textView,R.id.imageView3, R.id.textView2 });
       new InitDataLoading().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_page, container, false);
        listView = (LoadingListView)view.findViewById(R.id.listView);
        listView.setInLoad(this);
        listView.setOnScrollListener(listView);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    private class InitDataLoading extends AsyncTask<Void, Void, Void> { //Add the first 10 items

        @Override
        protected Void doInBackground(Void... params) {

            while(true){
                events = AppController.getInstance().getEventbeans();
                datalist=(List<xmlEventBean>) events.get("KKTIX"); //make big in the future
                if(datalist!=null){
                    addDataToList(adapter_data_count);
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listView.setAdapter(adapter); //Add adapter to listView

        }
    }
    public void addDataToList(int count) {
        for (int i = count; i < count+10; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>(); //裝每個ListView item的data
            item.put("title", (datalist.get(i)).getTitle());
            addImageToList("KKTIX",item);
            item.put("detail", (datalist.get(i)).getTimeANDplace());
            list.add(item);
            Log.i("ListView", "on " + i);
        }
        adapter_data_count = count + 10;
    }
    public void addImageToList(String sourse,HashMap<String, Object> item){
        switch (sourse){
            case "KKTIX":
                item.put("image",R.drawable.kktix_img);
                break;

        }
    }

    @Override
    public void onLoad() {
        addDataToList(adapter_data_count);
        adapter.notifyDataSetChanged();
        listView.completeLoading();
    }

}
