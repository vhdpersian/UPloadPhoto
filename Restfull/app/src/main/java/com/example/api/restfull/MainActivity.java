package com.example.api.restfull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnTokenRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTokenRegister = (Button) findViewById(R.id.btnTokenRegister);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BodyRequest bodyRequest =
                        BodyRequestFactory.CreateTokenRegisterRequest("666666");
                try {
                    new HttpUtils().SendRequest(bodyRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (PushNotificationServerException e) {
                    e.printStackTrace();
                }
            }

        });
        btnTokenRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });


    }
}
