package kdmhs.me.hyemdooly.dimibob.model;

import io.realm.RealmObject;

/**
 * Created by songhyemin on 2017. 4. 6..
 */

public class Meal extends RealmObject {
    String breakfast;
    String lunch;
    String dinner;
    String snack;


    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }
}