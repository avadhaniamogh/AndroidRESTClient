package rest.android.com.example.amogha.examplerestclient;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AMOGH A on 24-Feb-16.
 */
public class WebServiceGetTask extends AsyncTask<String, Integer, String> {

    private SecondActivity mActivity;

    public WebServiceGetTask(SecondActivity activity) {
        this.mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... urls) {
        String urlString = urls[0];
        String result = "";

        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int status = connection.getResponseCode();
            InputStream is;
            if (status >= 400)
                is = connection.getErrorStream();
            else
                is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            result = response.toString();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        mActivity.addUsers(result);
    }
}
