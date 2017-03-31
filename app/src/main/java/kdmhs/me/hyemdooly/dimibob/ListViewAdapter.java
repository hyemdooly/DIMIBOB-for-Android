package kdmhs.me.hyemdooly.dimibob;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by songhyemin on 2017. 3. 28..
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();
    Context context;
    ArrayList<Boolean> toggleUseList = new ArrayList<>();
    SharedPreferences preference;
    SharedPreferences.Editor editor;


    public ListViewAdapter(Context context) {
        this.context = context;
        preference = context.getSharedPreferences("ToggleStatus", 0);
        editor = preference.edit();

    }


    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.list_title);
        Switch aSwitch = (Switch) convertView.findViewById(R.id.ok_toggle);


        aSwitch.setChecked(preference.getBoolean("ToggleStatus", false));

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putBoolean("ToggleStatus", true);
                    editor.commit();
                    Log.d(TAG, "onCheckedChanged: "+preference.getBoolean("ToggleStatus", false));
                }else {
                    editor.putBoolean("ToggleStatus", false);
                    editor.commit();
                    Log.d(TAG, "onCheckedChanged: "+preference.getBoolean("ToggleStatus", false));
                }
            }
        });
        ListViewItem listViewItem = listViewItems.get(position);
        titleView.setText(listViewItem.getTitle());

        if(!toggleUseList.get(position)){
            aSwitch.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    public void addItem(String title, boolean toggleUse){
        ListViewItem item = new ListViewItem();
        item.setTitle(title);
        toggleUseList.add(toggleUse);
        listViewItems.add(item);

    }
}
