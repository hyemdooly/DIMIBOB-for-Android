package kdmhs.me.hyemdooly.dimibob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by songhyemin on 2017. 4. 10..
 */
public class TimeCheckReceiver extends BroadcastReceiver{
    RealmDataController controller;



    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getAction();
        controller = new RealmDataController(context);
        if(Intent.ACTION_TIME_TICK.equals(name)){
            controller.addData();
            Log.d("getdata : ", "OK!");
        }
    }
}
