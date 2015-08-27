package android.snapevent;

/**
 * Created by hsuan-ju on 2015/8/27.
 */
public enum VolleyTAG {


    KKTIX_ALL("KKTIX_ALL"),
    GOV_EVENT("GOV_EVENT"); //限制選項

    private String TAG=null;

    VolleyTAG(String TAG) {
        this.TAG=TAG;
    }

    public String getTAG(){
        return TAG;
    }

}
