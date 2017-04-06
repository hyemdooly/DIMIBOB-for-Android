package kdmhs.me.hyemdooly.dimibob;

import android.content.Context;

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

    public RealmDataController(Context context) {
        this.context = context;
    }

    public void addData(Map<String, String> response){
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


}
