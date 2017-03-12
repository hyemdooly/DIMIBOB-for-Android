package kdmhs.me.hyemdooly.dimibob;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {

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

        final HttpRequest httpRequest = new HttpRequest();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> response = new HashMap<>();
                try{
                    response = httpRequest.getRequest();



                } catch(Exception e){
                    e.printStackTrace();
                }




            }
        });


        t.start();

    }


}
