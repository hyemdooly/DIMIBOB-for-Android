package kdmhs.me.hyemdooly.dimibob;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songhyemin on 2017. 2. 21..
 */

public class JsonConverter {

    public static Map<String, String> jsonConvert(String json){
        JSONObject jsonObject;
        Map<String, String> result = new HashMap<>();

        try{
            jsonObject = new JSONObject(json);
            result.put("breakfast", jsonObject.get("breakfast").toString());
            result.put("lunch", jsonObject.get("lunch").toString());
            result.put("dinner", jsonObject.get("dinner").toString());
            result.put("snack", jsonObject.get("snack").toString());

        }catch(Exception e){
            result.put("breakfast", "급식정보가 없습니다.");
            result.put("lunch", "급식정보가 없습니다.");
            result.put("dinner", "급식정보가 없습니다.");
            result.put("snack", "급식정보가 없습니다.");

        }

        return result;

    }




}
