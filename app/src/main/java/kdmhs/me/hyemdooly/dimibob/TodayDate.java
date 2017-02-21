package kdmhs.me.hyemdooly.dimibob;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by songhyemin on 2017. 2. 22..
 */

public class TodayDate {


    public Map<String, String> todayDate(){

        Calendar calendar = Calendar.getInstance();
        Map<String, String> date= new HashMap<>();
        date.put("year", String.valueOf(calendar.YEAR));
        date.put("month", String.valueOf(calendar.MONTH));
        date.put("day", String.valueOf(calendar.DAY_OF_MONTH));

        return date;

    }


}
