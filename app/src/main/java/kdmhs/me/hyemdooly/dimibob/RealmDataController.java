package kdmhs.me.hyemdooly.dimibob;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

import io.realm.Realm;
import kdmhs.me.hyemdooly.dimibob.model.Meal;
import kdmhs.me.hyemdooly.dimibob.model.Meals;

/**
 * Created by songhyemin on 2017. 4. 6..
 */

public class RealmDataController {

    Realm realm = Realm.getDefaultInstance();
    Context context;
    Map<String, String> response;
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                response = new HttpRequest().getRequest();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

    public RealmDataController(Context context) {
        this.context = context;
    }

    public void addData(){
        getData();
        realm.beginTransaction();
        Meals today = new Meals();
        today.setName("Today");
        today.setMeal(transformMealData(response));
        realm.copyToRealmOrUpdate(today);
    }


    public Meal transformMealData(Map<String, String> response){
        Meal meal = new Meal();
        meal.setBreakfast(response.get("breakfast"));
        meal.setLunch(response.get("lunch"));
        meal.setDinner(response.get("dinner"));
        meal.setSnack(response.get("snack"));

        return meal;
    }

    public void getData(){
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Toast.makeText(context, "데이터를 저장하는데 문제가 생겼습니다!", Toast.LENGTH_LONG).show();
        }
    }


}
