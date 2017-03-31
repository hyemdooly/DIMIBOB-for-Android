package kdmhs.me.hyemdooly.dimibob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by songhyemin on 2017. 3. 30..
 */

public class DeveloperSayDialog {

    AlertDialog.Builder builder;
    Context context;
    AlertDialog alertDialog;

    public DeveloperSayDialog(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
        setDialog();
        alertDialog = builder.create();
    }

    public void setDialog(){
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_content).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

    }

    public void dialogShow(){
        alertDialog.show();
    }

}
