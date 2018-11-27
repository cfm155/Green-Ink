package com.example.cartermccall.greenink;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TutorialActivity extends AppCompatActivity {

    private Button nextButton;
    private Button finishButton;
    private ImageView map;
    private int alpha, red, orange, green;
    private float color = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        red = getRed();
        orange = getOrange();
        green = getGreen();


        map = (ImageView) findViewById(R.id.imageView);
        map.setColorFilter(adjustAlpha(green,(float).7), PorterDuff.Mode.SRC_ATOP);
        finishButton = (Button) findViewById(R.id.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = (float)((color + .25) % 3);
                if(color < 1){
                    alpha = adjustAlpha(green,(float).7);
                }
                else if(color < 2){
                    alpha = adjustAlpha(orange, (float)(color - .5)/2);
                }
                else if(color < 3){
                    alpha = adjustAlpha(red, (float)(color - 1.5)/2);
                }
                map.setColorFilter(alpha, PorterDuff.Mode.SRC_ATOP);
            }
        });

    }

    public void animateImageView(final ImageView v) {

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaRed = adjustAlpha(red, mul);
                v.setColorFilter(alphaRed, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    v.setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(1500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        colorAnim.start();

    }

    public int getRed(){
        return getResources().getColor(android.R.color.holo_red_dark);
    }

    public int getOrange(){
        return getResources().getColor(android.R.color.holo_orange_dark);
    }

    public int getGreen(){
        return getResources().getColor(android.R.color.holo_green_dark);
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
