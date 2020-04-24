package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Add_Combination extends AppCompatActivity {
    public static TextView resulttextview;

    Button Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__combination);


        Enter = findViewById(R.id.Enter);


        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Combination.this, newCombi_list.class);
                startActivity(intent);
            }
        });
    }
}
