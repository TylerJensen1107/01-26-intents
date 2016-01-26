package edu.uw.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**DEMO**";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View launchButton = findViewById(R.id.btnLaunch);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Launch button pressed");

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("MASSAGE", "this is a massage");


                startActivity(intent);


            }
        });


        View callButton = findViewById(R.id.btnDial);
        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Call button pressed");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:253-225-6313"));
                startActivity(intent);

            }
        });


        View cameraButton = findViewById(R.id.btnPicture);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Camera button pressed");

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }


            }
        });


        View messageButton = findViewById(R.id.btnMessage);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Message button pressed");


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap map = null;
        //if camera responds with a "yup"
        if(requestCode == 1 && resultCode == RESULT_OK) {
            map = (Bitmap) data.getExtras().get("data");
        }

        Bitmap newBitMap = Bitmap.createBitmap(map.getWidth(), map.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(newBitMap);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setRotate(0, 0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(map, 0, 0, paint);

        int[] colors = new int[5];
        colors[1] = Color.YELLOW;
        colors[2] = Color.CYAN;
        colors[3] = Color.CYAN;
        colors[4] = Color.MAGENTA;
        colors[0] = Color.TRANSPARENT;


        for(int i = 1; i < map.getWidth() - 1; i++) {
            for(int j = 1; j < map.getHeight() - 1; j++) {

                    newBitMap.setPixel(i, j, map.getPixel( (int) Math.round(i + Math.random() * 2) - 1 , (int) Math.round(j + Math.random() * 2) - 1));

            }
        }

        ImageView view = (ImageView) findViewById(R.id.imgThumbnail);
        view.setImageBitmap(newBitMap);
    }
}
