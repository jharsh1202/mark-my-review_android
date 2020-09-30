package com.example.markmyreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.squareup.haha.perflib.Main;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class CreateQR extends AppCompatActivity {
    Bitmap bitmap;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_q_r);
        imageView=findViewById(R.id.imageView);
        Intent intent=getIntent();
        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        String url=intent.getStringExtra("editedUrl");
        QRGEncoder qrgEncoder = new QRGEncoder(url, null, QRGContents.Type.TEXT, 512);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.i("TAG", e.toString());
        }
        // Save with location, value, bitmap returned and type of Image(JPG/PNG).
        QRGSaver qrgSaver = new QRGSaver();
        //saves the QR code in Android/data/com.example.markmyreview
        qrgSaver.save(getExternalCacheDir().toString(), url.substring(url.indexOf("edit")-6,url.indexOf("edit")-1), bitmap, QRGContents.ImageType.IMAGE_JPEG);
        Log.i("androidpath",getExternalCacheDir().toString());
    }


}