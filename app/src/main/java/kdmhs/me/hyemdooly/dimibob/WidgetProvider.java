package kdmhs.me.hyemdooly.dimibob;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        //내가 작성한다~~~~~~
        String mAction = intent.getAction();

        if(AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(mAction)) {

        } else if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(mAction)) {
            AppWidgetManager mManager = AppWidgetManager.getInstance(context);
            initWidget(context, mManager, mManager.getAppWidgetIds(new ComponentName(context, getClass())));
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for(int i = 0; i < appWidgetIds.length; i++) {
            final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_today);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
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


    public void initWidget(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_today);

        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }

        /*final HttpRequest httpRequest = new HttpRequest();

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
        }).start();*/



    }


}
