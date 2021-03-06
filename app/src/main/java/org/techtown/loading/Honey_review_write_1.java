package org.techtown.loading;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;


public class Honey_review_write_1 extends AppCompatActivity {

    private static String IP_ADDRESS = "13.209.147.9";
    private static String TAG = "foodchemii24";

    private EditText mEditTextUser_id;
    private EditText mEditTextContent;
    private EditText mEditTextGrade;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_review_write_1);

        mEditTextUser_id = (EditText)findViewById(R.id.editText_main_user_id);
        mEditTextContent = (EditText)findViewById(R.id.editText_main_content);
        mEditTextGrade = (EditText)findViewById(R.id.editText_main_grade);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());


        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mEditTextUser_id.getText().toString();
                String content = mEditTextContent.getText().toString();
                String grade = mEditTextGrade.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/write_honey_review.php", user_id, content, grade );


                mEditTextUser_id.setText("");
                mEditTextContent.setText("");
                mEditTextGrade.setText("");

            }
        });

    }



    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Honey_review_write_1.this,
                    "Please Wait", "", true, true);
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

            String user_id = (String)params[1];
            String content = (String)params[2];
            String grade = (String)params[3];

            String serverURL = (String)params[0];
            String postParameters = "user_id=" + user_id + "&content=" + content + "&grade=" + grade;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                Log.d(TAG, "로그  - " +  httpURLConnection);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


}