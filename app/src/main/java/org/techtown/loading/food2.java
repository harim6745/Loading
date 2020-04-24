package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class food2 extends AppCompatActivity {
    //public static TextView resulttextview;
    Button button_back,button_honey_commu,scanbutton,buttontoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food2);

        scanbutton = (Button)findViewById(R.id.scanbtn);
        //resulttextview = findViewById(R.id.barcodetextview);
        //buttontoast = findViewById(R.id.buttontoast);

        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarcodeScanner.class));
            }
        });

        /*
        buttontoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(food2.this, Health_check.resulttextview.getText(), Toast.LENGTH_SHORT).show();
            }
        });
*/

        button_back = findViewById(R.id.button_back);
        button_honey_commu = findViewById(R.id.button_honey_commu);

        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(food2.this,Select.class);
                startActivity(intent);
            }
        });

        button_honey_commu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(food2.this,Health_check.class);
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
