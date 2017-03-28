package kdmhs.me.hyemdooly.dimibob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by songhyemin on 2017. 3. 28..
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();

    public ListViewAdapter() {

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

        ListViewItem listViewItem = listViewItems.get(position);

        titleView.setText(listViewItem.getTitle());

        return convertView;
    }

    public void addItem(String title){
        ListViewItem item = new ListViewItem();
        item.setTitle(title);

        listViewItems.add(item);

    }
}
