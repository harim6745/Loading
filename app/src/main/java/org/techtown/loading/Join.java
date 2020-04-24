package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Join extends AppCompatActivity {

    private static String IP_ADDRESS = "13.209.147.9";
    private static String TAG = "foodchemii24";


    private EditText user_id;
    private EditText user_pw;
    private EditText user_pwc;
    private EditText user_sex;
    private EditText user_age;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        user_id = (EditText) findViewById(R.id.user_id);
        user_pw = (EditText) findViewById(R.id.user_pw);
        user_pwc = (EditText) findViewById(R.id.user_pwc);
        user_sex = (EditText) findViewById(R.id.user_sex);
        user_age = (EditText) findViewById(R.id.user_age);
        mTextViewResult = (TextView)findViewById(R.id.text_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        Button go_registerButton = (Button) findViewById(R.id.go_registerButton);
        go_registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user_id.getText().toString();
                String password = user_pw.getText().toString();
                String pwc = user_pwc.getText().toString();
                String sex = user_sex.getText().toString();
                String age = user_age.getText().toString();


                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insert.php", id, password, pwc, sex, age);

                user_id.setText("");
                user_pw.setText("");
                user_pwc.setText("");
                user_sex.setText("");
                user_age.setText("");

                // ---- 회원가입 후 로그인 화면으로 전환되게 하는 중 ------
/*
                Intent i = new Intent(getApplicationContext(),Login.class);

                i.putExtra("id","user_id");
                i.putExtra("password","user_id");
                i.putExtra("pwc","pwc");
                i.putExtra("sex","user_sex");
                i.putExtra("age","user_age");

                startActivity(i);

 */
                /*
                String[]array = {"","","",""} // 송신 할 배열
                Option option = new option("",""); // 송신 할 클래스

                Intent i = new Intent(getApplicationContext(),Login.class);

                i.putExtra("age",20); // 송신
                i.putExtra("array",array);
                i.putExtra("class",option);

                startActivity(i);

                 */
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Join.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }

        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[1];
            String password = (String)params[2];
            String pwc = (String)params[3];
            String sex = (String)params[4];
            String age = (String)params[5];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&password="+password+"&pwc="+ pwc +"&sex=" +sex+"&age="+age;

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

                    Intent i = new Intent(getApplicationContext(),Login.class);
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
}

