package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Honey_check extends AppCompatActivity {
    public static TextView resulttextview;

    ImageButton hotbaButton;
    Button AddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_check);


        hotbaButton = findViewById(R.id.hotbaButton);
        AddButton = findViewById(R.id.AddButton);

        hotbaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Honey_check.this, About_Combination.class);
                startActivity(intent);
            }
            });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Honey_check.this, Add_Combi_1.class);
                startActivity(intent);
            }
        });
    }
}