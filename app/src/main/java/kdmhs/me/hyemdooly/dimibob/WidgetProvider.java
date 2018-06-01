package kdmhs.me.hyemdooly.dimibob;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {

    Map<String, String> response = new HashMap<>();
    ArrayList<Integer> mealIdList = new ArrayList<>(Arrays.asList(R.id.breakfast, R.id.lunch, R.id.dinner, R.id.snack));
    ArrayList<String> mealNameList = new ArrayList<>(Arrays.asList("breakfast", "lunch", "dinner", "snack"));
    Thread t;
    boolean checkOk;

    Map<String, String> lastMeals;


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        initWidget();

        ComponentName thisAppWidgetIds = new ComponentName(context.getPackageName(), getClass().getName());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int ids[] = appWidgetManager.getAppWidgetIds(thisAppWidgetIds);

        for(int appWidgetId : ids){
            updateWidget(context, appWidgetManager, appWidgetId);
        }



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        initWidget();

        for(int appWidgetId : appWidgetIds){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_today);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
            updateWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }




    public void updateWidget(final Context context, AppWidgetManager appWidgetManager, int appWidgetId) {


        final RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget_today);
        try {
            t.join();
            if(checkOk){
                lastMeals = response;
                for(int i = 0 ; i < 4; i++){
                    updateViews.setTextViewText(mealIdList.get(i), response.get(mealNameList.get(i).toString()));
                }
            }else {
                if(!response.isEmpty()){
                    for(int i = 0 ; i < 4; i++){
                        updateViews.setTextViewText(mealIdList.get(i), lastMeals.get(mealNameList.get(i).toString()));
                    }

                }else{
                    for(int i = 0 ; i < 4; i++){
                        updateViews.setTextViewText(mealIdList.get(i), "인터넷이 원활하지 않습니다.");
                    }
                }

            }



        } catch (InterruptedException e) {
            for(int i = 0 ; i < 4; i++){
                updateViews.setTextViewText(mealIdList.get(i), "오류가 발생했습니다.");
            }
        }


        Intent intent = new Intent(context, WidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        updateViews.setOnClickPendingIntent(R.id.refresh_button,pendingIntent);
        Log.d("refresh", "ok");

        appWidgetManager.updateAppWidget(appWidgetId, updateViews);
    }


    public void initWidget(){


        t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    response = new HttpRequest().getRequest();
                    checkOk = true;

                } catch (Exception e) {
                    e.printStackTrace();
                    checkOk = false;
                }

            }
        });

        t.start();


    }
}
