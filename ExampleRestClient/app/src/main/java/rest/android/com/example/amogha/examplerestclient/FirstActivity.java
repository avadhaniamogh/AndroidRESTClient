
package rest.android.com.example.amogha.examplerestclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstActivity extends AppCompatActivity {

    private static String INSERT_BASE_URL = "http://192.168.0.104:8080/root/webapi/user/insert";
    private Button insertBtn;
    private Button allUsersBtn;
    private EditText idET;
    private EditText fnET;
    private EditText lnET;
    private EditText ageET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        insertBtn = (Button) findViewById(R.id.insBtn);
        allUsersBtn = (Button) findViewById(R.id.allUsersBtn);
        idET = (EditText) findViewById(R.id.idET);
        fnET = (EditText) findViewById(R.id.firstnameET);
        lnET = (EditText) findViewById(R.id.lastnameET);
        ageET = (EditText) findViewById(R.id.ageET);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        insertBtn.setOnClickListener(insertClick);
        allUsersBtn.setOnClickListener(allUsersClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener insertClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StringBuilder urlString = new StringBuilder(INSERT_BASE_URL);
            if (checkForWhiteSpace(idET.getText().toString())
                    || checkForWhiteSpace(fnET.getText().toString())
                    || checkForWhiteSpace(lnET.getText().toString())
                    || checkForWhiteSpace(ageET.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Should not contain white spaces",
                        Toast.LENGTH_SHORT).show();

            } else {
                urlString.append("/" + idET.getText().toString());
                urlString.append("/" + fnET.getText().toString());
                urlString.append("/" + lnET.getText().toString());
                urlString.append("/" + ageET.getText().toString());
                String requestMethod = "POST";
                WebServicePostTask task = new WebServicePostTask(FirstActivity.this, requestMethod);
                String[] params = {
                        urlString.toString()
                };
                task.execute(params);
            }
        }
    };

    View.OnClickListener allUsersClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(FirstActivity.this, SecondActivity.class);
            FirstActivity.this.startActivity(myIntent);
        }
    };

    private boolean checkForWhiteSpace(String string) {
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(string);
        boolean found = matcher.find();
        return found;
    }
}
