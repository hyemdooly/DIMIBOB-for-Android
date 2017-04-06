package kdmhs.me.hyemdooly.dimibob.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by songhyemin on 2017. 4. 6..
 */

public class Meals extends RealmObject {

    Meal meal;

    @PrimaryKey
    String name; // meal name, today or tomorrow


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
