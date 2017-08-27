package kdmhs.me.hyemdooly.dimibob;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by songhyemin on 2017. 4. 7..
 */

public class DataGetServices extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
