package kdmhs.me.hyemdooly.dimibob;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.Map;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {

    Map<String, String> response;
    Thread t;
    boolean checkOk;

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
                updateViews.setTextViewText(R.id.breakfast, response.get("breakfast").toString());
                updateViews.setTextViewText(R.id.dinner, response.get("dinner").toString());
                updateViews.setTextViewText(R.id.lunch, response.get("lunch").toString());
                updateViews.setTextViewText(R.id.snack, response.get("snack").toString());
            }else {
                updateViews.setTextViewText(R.id.breakfast, "인터넷이 원활하지 않습니다.");
                updateViews.setTextViewText(R.id.dinner, "인터넷이 원활하지 않습니다.");
                updateViews.setTextViewText(R.id.lunch, "인터넷이 원활하지 않습니다.");
                updateViews.setTextViewText(R.id.snack, "인터넷이 원활하지 않습니다.");
            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        appWidgetManager.updateAppWidget(appWidgetId, updateViews);
    }


    public void initWidget(){
        t = new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    response = new HttpRequest().getRequest();
                    checkOk = true;
                    Log.d("responseasdfasdfasdf : ", response.toString());


                } catch (IOException e) {
                    e.printStackTrace();
                    checkOk = false;
                }

            }
        });

        t.start();
    }
}
