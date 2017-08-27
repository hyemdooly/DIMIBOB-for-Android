package kdmhs.me.hyemdooly.dimibob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private long backPressed;
    DeveloperSayDialog dialog;
    Realm realm;
    RealmDataController dataController;
    Thread t;
    Map<String, String> response = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        dataController = new RealmDataController(getApplicationContext());


        ListView listView = (ListView) findViewById(R.id.listview);
        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext());

        listView.setAdapter(adapter);
        adapter.addItem("상단바 알림 설정", true);
        adapter.addItem("개발자의 말", false);
        adapter.addItem("데이터 다시 받아오기", false);

        dialog = new DeveloperSayDialog(MainActivity.this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 1:
                        dialog.dialogShow();
                        break;
                    case 2:
                        try{
                            updateData();
                            t.join();
                            dataController.addData();
                            Toast.makeText(getApplicationContext(), "가져오기에 성공했습니다!", Toast.LENGTH_LONG).show();

                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "가져오기에 실패했습니다!", Toast.LENGTH_LONG).show();
                        }

                        break;

                }

            }
        });

    }

    @Override
    public void onBackPressed() {

        if(backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();

        } else{
            Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();

    }


    public void updateData() throws IOException {

        t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    response = new HttpRequest().getRequest();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

    }



}
