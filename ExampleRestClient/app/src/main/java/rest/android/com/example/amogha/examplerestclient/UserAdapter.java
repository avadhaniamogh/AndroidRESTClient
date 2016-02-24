
package rest.android.com.example.amogha.examplerestclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AMOGH A on 24-Feb-16.
 */
public class UserAdapter extends ArrayAdapter<Object> {

    private Context mContext;
    int layoutResourceId;
    ArrayList<User> users;

    public UserAdapter(Context context, int resource, ArrayList<User> users) {
        super(context, resource);
        mContext = context;
        layoutResourceId = resource;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return this.users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            TextView idText = (TextView) row.findViewById(R.id.idText);
            TextView firstnameText = (TextView) row.findViewById(R.id.firstnameText);
            TextView lastnameText = (TextView) row.findViewById(R.id.lastnameText);
            TextView ageText = (TextView) row.findViewById(R.id.ageText);

            User user = users.get(position);
            idText.setText(String.valueOf(user.getId()));
            firstnameText.setText(user.getFirstname());
            lastnameText.setText(user.getLastname());
            ageText.setText(String.valueOf(user.getAge()));
        }
        return row;
    }
}
