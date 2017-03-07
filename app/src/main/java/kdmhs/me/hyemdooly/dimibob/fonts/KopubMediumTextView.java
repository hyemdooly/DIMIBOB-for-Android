package kdmhs.me.hyemdooly.dimibob.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by songhyemin on 2017. 3. 3..
 */

public class KopubMediumTextView extends android.support.v7.widget.AppCompatTextView {

    public KopubMediumTextView(Context context) {
        super(context);

    }

    public KopubMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyTypeface(context, attrs);
    }

    public KopubMediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyTypeface(context, attrs);
    }

    private void applyTypeface(Context context, AttributeSet attrs){
        //TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.KopubTextView);
        String typefaceName = "KoPubDotumMedium.ttf"; //arr.getString(R.styleable.KopubTextView_kopubFont);
        Typeface typeface = null;

        try{
            typeface = Typeface.createFromAsset(context.getAssets(), typefaceName);
            setTypeface(typeface);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
