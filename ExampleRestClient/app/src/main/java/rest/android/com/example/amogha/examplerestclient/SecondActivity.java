
package rest.android.com.example.amogha.examplerestclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    UserAdapter adapter;
    private static String GET_BASE_URL = "http://192.168.0.104:8080/root/webapi/user/users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView) findViewById(R.id.usersList);

        StringBuilder urlString = new StringBuilder(GET_BASE_URL);
        WebServiceGetTask task = new WebServiceGetTask(SecondActivity.this);
        String[] params = {
                urlString.toString()
        };
        task.execute(params);
    }

    public void addUsers(String result) {
        ArrayList<User> users = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                User user = new User();
                user.setId(obj.getInt("id"));
                user.setFirstname(obj.getString("firstName"));
                user.setLastname(obj.getString("lastName"));
                user.setAge(obj.getInt("age"));
                users.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new UserAdapter(this,
                R.layout.list_item, users);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
