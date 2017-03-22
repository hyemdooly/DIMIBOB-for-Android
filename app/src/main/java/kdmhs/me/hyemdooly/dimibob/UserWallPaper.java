package kdmhs.me.hyemdooly.dimibob;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by songhyemin on 2017. 3. 22..
 */

public class UserWallPaper {
    Context context;

    public UserWallPaper(Context context){
        this.context = context;
    }

    public void getUserWallPaper(){

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) wallpaperManager.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();




    }
}
