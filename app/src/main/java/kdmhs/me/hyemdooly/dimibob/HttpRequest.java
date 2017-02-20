package kdmhs.me.hyemdooly.dimibob;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by songhyemin on 2017. 2. 21..
 */

public class HttpRequest {
    OkHttpClient client = new OkHttpClient();

    String date;
    String breakfast;
    String lunch;
    String dinner;
    String snack;

    String run(String url) throws IOException{

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("d", date).build();
        url = urlBuilder.toString();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        return response.body().toString();

    }


    public Map<String, String> getRequest(String date) throws IOException{

        this.date = date;
        String response = run("http://dimigo.in/pages/dimibob_getdata.php");

        Map<String, String> result = JsonConverter.jsonConvert(response);

        return result;
    }



}
