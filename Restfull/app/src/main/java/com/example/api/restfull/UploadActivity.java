package com.example.api.restfull;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    Button btnUpload;
    ImageView imgUpload;
    final static int PICK_IMAGE_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        btnUpload=(Button)findViewById(R.id.btnUpload);
        imgUpload=(ImageView)findViewById(R.id.imgUpload);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if(requestCode==PICK_IMAGE_REQUEST
              && resultCode==RESULT_OK
              && data.getData()!=null)
      {

          Uri uriPathFile=data.getData();
          String realPathFile=ImageFilePath.getPath(UploadActivity.this,uriPathFile);
        //  File file=new File(realPathFile);

          Log.d(UploadActivity.class.getName(),"Image Path : "+realPathFile);
          try {
              Bitmap  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriPathFile);
              imgUpload.setImageBitmap(bitmap);
              uploadPhoto(bitmap);
          } catch (IOException e) {
              e.printStackTrace();
          }

      }

    }

    private void uploadPhoto(final Bitmap bitmap)
    {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BodyRequest bodyRequest =
                        BodyRequestFactory.CreateSendPhoto(bitmap);
                try {
                    new HttpUtils().SendRequest(bodyRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (PushNotificationServerException e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
    }
}
