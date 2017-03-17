package kdmhs.me.hyemdooly.dimibob;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.Map;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                // 디미고 api로부터 request값을 받아오는 부분
                HttpRequest httpRequest = new HttpRequest();
                Map<String, String> response = null;

                // views 안의 textview에 setText해주는 부분
                try {
                    response = httpRequest.getRequest();
                    updateViews.setTextViewText(R.id.breakfast, response.get("breakfast").toString());
                    updateViews.setTextViewText(R.id.dinner, response.get("dinner").toString());
                    updateViews.setTextViewText(R.id.lunch, response.get("lunch").toString());
                    updateViews.setTextViewText(R.id.snack, response.get("snack").toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        t.start();

        appWidgetManager.updateAppWidget(appWidgetId, updateViews);
    }
}
