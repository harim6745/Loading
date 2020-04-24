package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ShowGraph extends AppCompatActivity {

    private static String IP_ADDRESS = "13.209.147.9";
    private static String TAG = "foodchemii24";

    PieChart pieChart;
    HorizontalBarChart nChart;
    private TextView mTextViewResult;

    //===== 따라하긔
    private EditText mEditTextName;
    private EditText mEditTextCountry;
    private ArrayList<GraphData> mArrayList;
    private GraphAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);



        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mRecyclerView = (RecyclerView) findViewById(R.id.listView_main_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        mArrayList = new ArrayList<>();

        mAdapter = new GraphAdapter(this, mArrayList);

        mRecyclerView.setAdapter(mAdapter);

        Button button_all = (Button) findViewById(R.id.button_main_all);
        button_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mArrayList.clear();
                mAdapter.notifyDataSetChanged();

                GetData task = new GetData();
                task.execute( "http://" + IP_ADDRESS + "/health_prod.php", "");
            }
        });
    } // onCreate

    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowGraph.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){
                mTextViewResult.setText(errorString);
            }
            else {
                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = params[1];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

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

                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "GetData : Error ", e);
                errorString = e.toString();

                return null;
            }
        } // doInBackground
    } // GetData

    private void showResult() {

        String TAG_JSON = "webnautes";
        String TAG_NAME = "prod_name";
        String TAG_BARCODE = "prod_barcode";
        String TAG_CARBO = "prod_carbo";
        String TAG_PROTEIN = "prod_protein";
        String TAG_SODIUM = "prod_sodium";
        String TAG_FAT = "prod_fat";
        String TAG_SUGAR = "prod_sugar";
        String TAG_SATURATEDFAT = "prod_saturated_fat";
        String TAG_KCAL = "prod_kcal";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String prod_name = item.getString(TAG_NAME);
                String prod_barcode = item.getString(TAG_BARCODE);
                String prod_carbo = item.getString(TAG_CARBO);
                String prod_protein = item.getString(TAG_PROTEIN);
                String prod_sodium = item.getString(TAG_SODIUM);
                String prod_fat = item.getString(TAG_FAT);
                String prod_sugar = item.getString(TAG_SUGAR);
                String prod_saturated_fat = item.getString(TAG_SATURATEDFAT);
                String prod_kcal = item.getString(TAG_KCAL);

                GraphData personalData = new GraphData();

                personalData.setProd_name(prod_name);
                personalData.setProd_barcode(prod_barcode);
                personalData.setProd_carbo(prod_carbo);
                personalData.setProd_protein(prod_protein);
                personalData.setProd_sodium(prod_sodium);
                personalData.setProd_fat(prod_fat);
                personalData.setProd_sugar(prod_sugar);
                personalData.setProd_saturated_fat(prod_saturated_fat);
                personalData.setProd_kcal(prod_kcal);

                mArrayList.add(personalData);
                mAdapter.notifyDataSetChanged();
                //----- 그래프


                pieChart = (PieChart)findViewById(R.id.piechart);

                pieChart.setUsePercentValues(true);
                pieChart.getDescription().setEnabled(false);
                pieChart.setExtraOffsets(5,10,5,5);

                pieChart.setDragDecelerationFrictionCoef(0.95f);

                pieChart.setDrawHoleEnabled(false);
                pieChart.setHoleColor(Color.WHITE);
                pieChart.setTransparentCircleRadius(61f);

                //-----------여기에 값을 바꿔야 함 ----------------
                ArrayList<PieEntry> yValues = new ArrayList<>();

                yValues.add(new PieEntry(Float.parseFloat(prod_carbo),"탄수화물"));
                yValues.add(new PieEntry(Float.parseFloat(prod_protein),"단백질"));
                yValues.add(new PieEntry(Float.parseFloat(prod_fat),"지방"));
                //-----------어떻게 바꾸지 ;; ㅠㅠ ----------------

                pieChart.animateY(2500, Easing.EasingOption.EaseInOutCubic);

                PieDataSet dataSet = new PieDataSet(yValues,"영양소");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                PieData data = new PieData((dataSet));
                data.setValueTextSize(15f);
                data.setValueTextColor(Color.YELLOW);

                pieChart.setData(data);
                //----- 그래프 끝
            }
        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }

    } // showResult

    /*
    private void setData(int count, int range){

        ArrayList<BarEntry> yVals = new ArrayList<>();
        float barWidth = 9f;
        float spaceForBar = 10f;

        for(int i=0; i<count; i++){
            float val = (float) (Math.random()*range);
            yVals.add(new BarEntry(i*spaceForBar,val));
        }

        BarDataSet set1,set2,set3;
        set1 = new BarDataSet(yVals,"나트륨");

        BarData data = new BarData(set1);
        data.setBarWidth(barWidth);

        nChart.animateY(2500, Easing.EasingOption.EaseInOutCubic);
        nChart.setData(data);
    } // setData
*/
}

