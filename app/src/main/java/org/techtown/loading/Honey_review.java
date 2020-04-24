package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Honey_review extends AppCompatActivity {
    public static TextView resulttextview;

    Button WriteReviewButton;
    TextView good_num, bad_num = null;
    ImageButton good, bad = null;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_review);


        WriteReviewButton = findViewById(R.id.WriteReviewButton);
        good = (ImageButton) findViewById(R.id.good);
        good_num = (TextView)findViewById(R.id.good_num);
        bad = (ImageButton) findViewById(R.id.bad);
        bad_num = (TextView)findViewById(R.id.bad_num);



        WriteReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Honey_review.this, Honey_review_write.class);
                startActivity(intent);
            }
        });

        good.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                count++;
                good_num.setText(""+count);
            }
        });

        bad.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                count++;
                bad_num.setText(""+count);
            }
        });

    }
}