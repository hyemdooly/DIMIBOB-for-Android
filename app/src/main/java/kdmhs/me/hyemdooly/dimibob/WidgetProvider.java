package kdmhs.me.hyemdooly.dimibob;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import io.realm.Realm;
import kdmhs.me.hyemdooly.dimibob.model.Meals;

/**
 * Created by songhyemin on 2017. 3. 7..
 */

public class WidgetProvider extends AppWidgetProvider {


    Realm realm = Realm.getDefaultInstance();


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
        Meals meals = realm.where(Meals.class).equalTo("name", "Today").findFirst();

        if(meals == null){
            String ERROR = "급식 정보가 없습니다.";
            updateViews.setTextViewText(R.id.breakfast, ERROR);
            updateViews.setTextViewText(R.id.lunch, ERROR);
            updateViews.setTextViewText(R.id.dinner, ERROR);
            updateViews.setTextViewText(R.id.snack, ERROR);
        } else {
            updateViews.setTextViewText(R.id.breakfast, meals.getMeal().getBreakfast());
            updateViews.setTextViewText(R.id.lunch, meals.getMeal().getLunch());
            updateViews.setTextViewText(R.id.dinner, meals.getMeal().getDinner());
            updateViews.setTextViewText(R.id.snack, meals.getMeal().getSnack());
        }


        // setOnClickPendingIntent
        Intent intent = new Intent(context, WidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        updateViews.setOnClickPendingIntent(R.id.refresh_button,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, updateViews);
    }


}
