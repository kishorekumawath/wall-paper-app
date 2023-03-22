package com.example.wallypexels;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.IOException;

public class homewallpaperdownload extends AppCompatActivity {
    ImageView wallpaperimg, blurimg;
    WallpaperManager wallpaperManager;
    Button button;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homewallpaperdownload);
        wallpaperimg = findViewById(R.id.wallpaperimghome);
        button = findViewById(R.id.Button);
        blurimg = findViewById(R.id.blurimg);
        String url = getIntent().getStringExtra("imgurl");
        Glide.with(this).load(url).into(wallpaperimg);
        Glide.with(this).load(url).into(blurimg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blurimg.setRenderEffect(RenderEffect.createBlurEffect(35, 35, Shader.TileMode.MIRROR));
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(homewallpaperdownload.this).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        Toast.makeText(homewallpaperdownload.this, "failed to load image", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        try {
                         Bitmap bitmap = ((BitmapDrawable) wallpaperimg.getDrawable()).getBitmap();
                         wallpaperManager.setBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(homewallpaperdownload.this, "fail to set wallpaper", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                }).submit();
                FancyToast.makeText(homewallpaperdownload.this, "wallpaper Setted to Your home screen", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            }
        });

    }

}

