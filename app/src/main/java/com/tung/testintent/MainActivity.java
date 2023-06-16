package com.tung.testintent;

import static java.net.Proxy.Type.HTTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout widgets=findViewById(R.id.items);

        Button btnMessage=findViewById(R.id.messageBtn);
        btnMessage.setOnClickListener(
                v->{
                    widgets.removeAllViews();
                    widgets.addView(functions.createEditText(false,this));
                    widgets.addView(functions.createButton(false,this));

                }
        );

        Button btnCall=findViewById(R.id.phoneButton);
        btnCall.setOnClickListener(
                v->{
                    widgets.removeAllViews();
                    widgets.addView(functions.createEditText(true,this));
                    widgets.addView(functions.createButton(true,this));
                }
        );

        Button btnWeb=findViewById(R.id.webButton);
        btnWeb.setOnClickListener(
                v->{
                    Intent intent=functions.wifi();
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
        );

    }


}