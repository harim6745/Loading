package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    private static String IP_ADDRESS = "13.209.147.9";
    private static String TAG = "foodchemii24";

    private EditText name;
    private EditText pw;
    private TextView Result;

    /*
    private static String id="";
    private static String password="";
     */

    Button intent_join_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent_join_btn = findViewById(R.id.intent_join_btn);

        name = (EditText) findViewById(R.id.name);
        pw = (EditText) findViewById(R.id.pw);
        Result = (TextView)findViewById(R.id.result);

        Result.setMovementMethod(new ScrollingMovementMethod());



        //---------데이터 받기 ing
        /*
        Intent i = getIntent();
        String id = i.getExtras().getString("name");
        String password = i.getExtras().getString("pw");]

         */

        /*
        Intent intent = getIntent();

        String name = intent.getExtras().getString("name"); // string 형
        tx1.setText(name);

        int age = intent.getExtras().getInt("age"); // int 형
        tx2.setText(String.valueOf(age));

        String array[] = intent.getExtras().getStringArray("array"); // 배열
        String add_array="";
        for(int i =0; i<array.length; i++){
            add_array+=array[i]+",";
        }
        tx3.setText(add_array);

        Option option = (Option)intent.getSerializableExtra("class") // 클래스
        tx4.setText(option.getPhone())
         */

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = name.getText().toString();
                String password = pw.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/android_login_2.php", id, password);

                name.setText("");
                pw.setText("");


                Intent i = new Intent(getApplicationContext(),Select.class);
                startActivity(i);


            }
        });


        /* 로그인 완성 한 후 기능 넣기 !
        intent_bt_go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this,Select.class);
                startActivity(intent);
            }
        });
*/
        intent_join_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this,Join.class);
                startActivity(intent);
            }
        });


    } // onCreate

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Login.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Result.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }

        @Override
        protected String doInBackground(String... params) {


            String id = (String)params[1];
            String password = (String)params[2];

            String serverURL = (String)params[0];

            String postParameters = "id=" + id + "&password=" + password;



            try{
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    // 만약 아이디랑 비번 같으면 페이지 넘어가게
                    Intent i = new Intent(getApplicationContext(),Select.class);
                    startActivity(i);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        } // doInBackground
    } // InsertData
} // Login
