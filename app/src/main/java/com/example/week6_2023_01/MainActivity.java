package com.example.week6_2023_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public class myTask extends AsyncTask<String,Void,String>{



        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings)
        { //variable Arguments
            StringBuffer sb;
            if(strings!=null) {
                 sb = new StringBuffer(strings[0]);
                for(int i=1;i<strings.length;i++)
                    sb.append(" ").append(strings[i]);
            }
            else
                sb= new StringBuffer("BOŞ PARAMETRE");
            return sb.toString();

        }
        @Override
        protected void onPostExecute(String s) {
            Log.d("MCK",s);
        }
    }


    public class myHttpConntector extends AsyncTask<String,Void,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer sb= new StringBuffer("");

            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection con= (HttpsURLConnection) url.openConnection();

                InputStream in= con.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data= reader.read();
                while(data!=-1){
                   sb.append((char) data);
                   data=reader.read();
                }


            } catch (MalformedURLException e) {
                Log.d("MCK",e.getMessage());
            } catch (IOException e) {
                Log.d("MCK",e.getMessage());
            }


            return sb.toString();

        }
    }

    TextView tv;
    boolean Flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.textView);
            myTask m= new myTask();
            m.execute("Merhaba","Dünya","Nasılsın","Https://www.ticaret.edu.tr");


            myHttpConntector task= new myHttpConntector();
        try {
            String html= task.execute("https://www.ticaret.edu.tr").get();
            Log.d("MCK",html);
        } catch (ExecutionException e) {
            Log.d("MCK",e.getMessage());
        } catch (InterruptedException e) {
            Log.d("MCK",e.getMessage());
        }

    }




    public void HideMe(View v){

        if(Flag) {
            tv.setVisibility(View.INVISIBLE);
            Flag=false;
        }
        else {
            Flag=true;
            tv.setVisibility(View.VISIBLE);
        }

    }
}