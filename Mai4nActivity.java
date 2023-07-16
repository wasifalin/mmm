package com.example.pgm4_wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button wallpaperChange;
    Timer mytimer;
    Drawable drawable;
    WallpaperManager wpm;
    int prev=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytimer=new Timer();
        wpm = WallpaperManager.getInstance(this);
        wallpaperChange= findViewById(R.id.button1);
        wallpaperChange.setOnClickListener(view -> setwallpaper());
    }

    private void setwallpaper() {
        Toast.makeText(this,"setting Wallpaper please wait.",Toast.LENGTH_LONG).show();
        mytimer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                if(prev==1) {
                    drawable = getResources().getDrawable(R.drawable.pic1); prev = 2; //Wallpaper Activity
                }
                else if(prev==2) {
                    drawable = getResources().getDrawable(R.drawable.pic2); prev=3;
                }
                else if(prev==3) {
                    drawable = getResources().getDrawable(R.drawable.pic3); prev=4;
                }
                else if(prev==4) {
                    drawable = getResources().getDrawable(R.drawable.pic4); prev=5;
                }
                else if(prev==5) {
                    drawable = getResources().getDrawable(R.drawable.pic3); prev=1;
                }
                Bitmap wallpaper = ((BitmapDrawable)drawable).getBitmap(); try {
                wpm.setBitmap(wallpaper);
            }
            catch (IOException e)
            { e.printStackTrace();
            } }
        },0,4000);
    }
}
