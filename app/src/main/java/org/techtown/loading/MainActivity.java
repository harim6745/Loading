package org.techtown.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static  int SPLASH_SCREEN = 4000;

    Animation topAnim,BottonAnim,q1Anim,q4Anim,foodAnim,food2Anim,food3Anim;
    ImageView image_char,image_bar,image_q1,image_q2,image_q3,image_q4,image_logo;
    ImageView image_sam, image_do, image_ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        BottonAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        q1Anim = AnimationUtils.loadAnimation(this,R.anim.q1_animation);
        q4Anim = AnimationUtils.loadAnimation(this,R.anim.q4_animation);
        foodAnim = AnimationUtils.loadAnimation(this,R.anim.food_animation);
        food2Anim = AnimationUtils.loadAnimation(this,R.anim.food2_animation);
        food3Anim = AnimationUtils.loadAnimation(this,R.anim.food3_animation);

        image_char = findViewById(R.id.imageView);
        image_char.setAnimation(topAnim);

        image_bar = findViewById(R.id.imageView7);
        image_bar.setAnimation(BottonAnim);

        image_logo = findViewById(R.id.imageView10);
        image_logo.setAnimation(BottonAnim);

        image_q1 = findViewById(R.id.imageView2);
        image_q1.setAnimation(foodAnim);

        image_q2 = findViewById(R.id.imageView5);
        image_q2.setAnimation(food2Anim);

        image_q3 = findViewById(R.id.imageView4);
        image_q3.setAnimation(food3Anim);

        image_q4 = findViewById(R.id.imageView3);
        image_q4.setAnimation(q4Anim);

        image_sam = findViewById(R.id.imageView6);
        image_sam.setAnimation(topAnim);

        image_do = findViewById(R.id.imageView8);
        image_do.setAnimation(topAnim);

        image_ra = findViewById(R.id.imageView9);
        image_ra.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
                                  },SPLASH_SCREEN);

    }
}
