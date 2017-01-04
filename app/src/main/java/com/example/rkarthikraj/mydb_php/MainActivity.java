package com.example.rkarthikraj.mydb_php;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText getID, getName, getPlace;
    String ID, Name, Place;
    TextView nameTV, placeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getID = (EditText) findViewById(R.id.id1);
        getName = (EditText) findViewById(R.id.name);
        getPlace = (EditText) findViewById(R.id.place);
        nameTV = (TextView) findViewById(R.id.nametv);
        placeTV = (TextView) findViewById(R.id.placetv);
    }

    public void onClickInsert(View v) {
        Name = getName.getText().toString();
        Place = getPlace.getText().toString();
        AsyncTaskDBINS runnerINS = new AsyncTaskDBINS();
        runnerINS.execute(Name, Place);
    }

    public void onClickUpdate(View v) {
        ID = getID.getText().toString();
        Name = getName.getText().toString();
        Place = getPlace.getText().toString();
        AsyncTaskDBUPD runnerUPD = new AsyncTaskDBUPD();
        runnerUPD.execute(ID, Name, Place);
    }

    public void onClickDelete(View v) {
        ID = getID.getText().toString();
        AsyncTaskDBDEL runnerDEL = new AsyncTaskDBDEL();
        runnerDEL.execute(ID);
    }

    public void onClickSelect(View v) {
        ID = getID.getText().toString();
        AsyncTaskDBSEL runnerSEL = new AsyncTaskDBSEL();
        runnerSEL.execute(ID);
    }

        public void onClickReset(View v) {
        getID.setText("");
        getName.setText("");
        getPlace.setText("");
    }

    //INSERT
    class AsyncTaskDBINS extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String place = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.213/androphp.php");
                String urlParams = "name=" + name + "&place=" + place;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Data INSERTED successfully.", Toast.LENGTH_LONG).show();
        }
    }

    //UPDATE
    class AsyncTaskDBUPD extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            String name = params[1];
            String place = params[2];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.213/androphpupd.php");
                String urlParams = "id=" + id + "&name=" + name + "&place=" + place;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Data UPDATED successfully.", Toast.LENGTH_LONG).show();
        }
    }


    //DELETE
    class AsyncTaskDBDEL extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            int tmp;
            String data = "";

            try {
                URL url = new URL("http://192.168.1.213/androphpdel.php");
                String urlParams = "id=" + id;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Data DELETED successfully.", Toast.LENGTH_LONG).show();
        }
    }

//SELECT

    class AsyncTaskDBSEL extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String data = "";

            try {
                URL url = new URL("http://192.168.1.213/androphpselect.php/?id=" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);//connection.setRequestMethod("POST");c
                //connection.setRequestProperty("Content-Type", "application/json");
               /* OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                //osw.write(String.format( String.valueOf(json)));
                osw.flush();
                osw.close();*/

                InputStream stream = connection.getInputStream();
                InputStreamReader isReader = new InputStreamReader(stream);
                BufferedReader br = new BufferedReader(isReader);
                data = br.readLine();
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject json = null;
            String name = "";
            String place = "";

            try {
                json = new JSONObject(s);
                nameTV.setText("Name" + json.getString("name"));
                placeTV.setText("Place" + json.getString("place"));

            } catch (JSONException j) {

            }
        }
    }
}
