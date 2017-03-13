package kdmhs.me.hyemdooly.dimibob;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.RemoteViews;

import java.util.Map;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {

    RemoteViews.RemoteView remoteView;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        updateWidget(context, appWidgetManager, appWidgetIds);

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



    public void updateWidget(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetId){

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_today);

        final HttpRequest httpRequest = new HttpRequest();

        final android.os.Handler handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();

                remoteViews.setTextViewText(R.id.breakfast, bundle.getString("breakfast"));
                remoteViews.setTextViewText(R.id.lunch, bundle.getString("lunch"));
                remoteViews.setTextViewText(R.id.dinner, bundle.getString("dinner"));
                remoteViews.setTextViewText(R.id.snack, bundle.getString("snack"));

            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Map<String, String> response = httpRequest.getRequest();

                    Message msg = handler.obtainMessage();
                    Bundle bundle = new Bundle();

                    bundle.putCharSequence("breakfast",response.get("breakfast").toString());
                    bundle.putCharSequence("lunch",response.get("lunch").toString());
                    bundle.putCharSequence("dinner",response.get("dinner").toString());
                    bundle.putCharSequence("snack",response.get("snack").toString());

                    msg.setData(bundle);

                    handler.sendMessage(msg);

                } catch(Exception e){
                    e.printStackTrace();
                }




            }
        }).start();


        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);


    }



}
