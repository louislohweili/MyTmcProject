/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Help extends AppCompatActivity {
    private ImageSwitcher sw;
    private Button b1,b2;
    private final String[] imageNames= {"home", "menu", "aboutus"
            , "course", "viewevent"
            , "register","contactus","login"};
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        sw = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        // Animation when switching to another image.
        Animation out= AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation in= AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        // Set animation when switching images.
        sw.setInAnimation(in);
        sw.setOutAnimation(out);

        //
        sw.setFactory(new ViewSwitcher.ViewFactory() {

            // Returns the view to show Image
            // (Usually should use ImageView)
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());

                imageView.setBackgroundColor(Color.LTGRAY);
                imageView.setScaleType(ImageView.ScaleType.CENTER);

                ImageSwitcher.LayoutParams params= new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });

        this.currentIndex=0;
        this.showImage(this.currentIndex);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousImage();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });
    }


    private void previousImage()  {
        if(currentIndex > 0) {
            currentIndex--;
        }else  {
            Toast.makeText(getApplicationContext(), "No Previous Image", Toast.LENGTH_SHORT).show();
            return;
        }
        this.showImage(currentIndex);
    }

    private void nextImage()  {
        if(currentIndex < this.imageNames.length-1) {
            currentIndex++;
        }else  {
            Toast.makeText(getApplicationContext(), "No Next Image", Toast.LENGTH_SHORT).show();
            return;
        }
        this.showImage(currentIndex);
    }


    private void showImage(int imgIndex)  {
        String imageName= this.imageNames[imgIndex];

        int resId= getDrawableResIdByName(imageName);
        if(resId!=  0) {
            this.sw.setImageResource(resId);
        }
    }

    // Find Image ID corresponding to the name of the image (in the drawable folder).
    public int getDrawableResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("MyLog", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

}