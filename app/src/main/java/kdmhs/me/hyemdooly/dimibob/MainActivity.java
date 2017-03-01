package kdmhs.me.hyemdooly.dimibob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HttpRequest httpRequest = new HttpRequest();

        final TextView textView = (TextView) findViewById(R.id.textview);

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Map<String, String> response = new HashMap<>();
//
//                try {
//                    response = httpRequest.getRequest();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                final Map<String, String> finalResponse = response;
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(finalResponse.toString());
//                    }
//                });
//
//
//            }
//        });
//
//        t.start();



    }


}
