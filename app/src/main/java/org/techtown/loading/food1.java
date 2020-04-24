package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class food1 extends AppCompatActivity {
    public static TextView resulttextview;
    Button button_back,button_honey_commu,scanbutton,buttontoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food1);

        resulttextview = findViewById(R.id.barcodetextview);
        scanbutton = findViewById(R.id.scanbtn);
        buttontoast = findViewById(R.id.buttontoast);

        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarcodeScanner.class));
            }
        });

        buttontoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(food1.this, resulttextview.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        button_back = findViewById(R.id.button_back);
        button_honey_commu = findViewById(R.id.button_honey_commu);

        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(food1.this,Select.class);
                startActivity(intent);
            }
        });

        button_honey_commu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(food1.this,Honey_check.class);
                startActivity(intent);
            }
        });

    }
    /*
    public void showCameraBtn(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);

    }*/
}
