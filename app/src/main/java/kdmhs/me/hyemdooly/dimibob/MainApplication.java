package kdmhs.me.hyemdooly.dimibob;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by songhyemin on 2017. 4. 6..
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("meals.realm").build();
        Realm.setDefaultConfiguration(config);

    }

}
