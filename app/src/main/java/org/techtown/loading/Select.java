package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select extends AppCompatActivity {
    Button intent_bt_food1, intent_bt_food2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        intent_bt_food1 = findViewById(R.id.intent_bt_food1);
        intent_bt_food2 = findViewById(R.id.intent_bt_food2);

        intent_bt_food1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Select.this,food1.class);
                startActivity(intent);
            }
        });

        intent_bt_food2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Select.this,food2.class);
                startActivity(intent);
            }
        });
    }
}
