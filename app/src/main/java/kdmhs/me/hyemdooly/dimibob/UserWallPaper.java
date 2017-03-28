package kdmhs.me.hyemdooly.dimibob;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;

/**
 * Created by songhyemin on 2017. 3. 22..
 */

public class UserWallPaper {
    Context context;
    Palette palette;

    public UserWallPaper(Context context){
        this.context = context;
    }

    public void getUserWallPaper(){

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) wallpaperManager.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        if(bitmap != null && !bitmap.isRecycled()){
            palette = Palette.from(bitmap).generate();
        }

        palette.getDominantSwatch();




    }
}
