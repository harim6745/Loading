package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Health_check extends AppCompatActivity {

    private static String IP_ADDRESS = "13.209.147.9";
    private static String TAG = "foodchemii24";

    public static TextView resulttextview;
    PieChart pieChart;

    ImageButton hotbaButton;
    Button AddButton,buttontoast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);

        resulttextview = findViewById(R.id.barcodetextview);
        buttontoast = findViewById(R.id.buttontoast);


        hotbaButton = findViewById(R.id.hotbaButton);
        AddButton = findViewById(R.id.AddButton);
        //----- 바코드 결과 ---------

        buttontoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Health_check.this, Health_check.resulttextview.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        // ---- 그래프 그리기 도전 -------


        pieChart = (PieChart)findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"탄수화물"));
        yValues.add(new PieEntry(23f,"단백질"));
        yValues.add(new PieEntry(14f,"지방"));
        yValues.add(new PieEntry(35,"당"));
        yValues.add(new PieEntry(40,"포화지방"));
        yValues.add(new PieEntry(23,"이민지짱"));

        /*
        Description description = new Description();
        description.setText("푸드케미24");
        description.setTextSize(15);
        pieChart.setDescription(description);
 */
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues,"영양소");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);





        hotbaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Health_check.this, About_Combination2.class);
                startActivity(intent);
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Health_check.this, Add_Combination2.class);
                startActivity(intent);
            }
        });
    }
}