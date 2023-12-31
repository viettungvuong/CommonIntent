package com.tung.testintent;

import static java.net.Proxy.Type.HTTP;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

public class functions {
    private static String phoneNumber;
    private static String content;
    public static Intent sendMessage(String content) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("sms_body", content);
        return intent;
    }

    public static Intent call(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;


    }

    public static Intent wifi(){
        return new Intent(Settings.ACTION_WIFI_SETTINGS);
    }

    public static EditText createEditText(boolean call, Context context){
        EditText editText=new EditText(context);
        if (call){
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        editText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (call){
                            phoneNumber=s.toString();
                        }
                        else{
                            content=s.toString();
                        }
                    }
                }
        );
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(50,50,50,50);
        editText.setLayoutParams(layoutParams);
        return editText;
    }

    public static Button createButton(boolean call, Context context){
        Button newBtn=new Button(context);
        newBtn.setTextColor(Color.BLACK);
        if (call){
            newBtn.setText("Call");
            newBtn.setBackgroundColor(Color.GREEN);
            newBtn.setOnClickListener(
                    view->{
                        Intent intent = call(phoneNumber);
                        if (intent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(intent);
                        }
                    }
            );
        }
        else{
            newBtn.setText("Send");
            newBtn.setBackgroundColor(Color.YELLOW);

            newBtn.setOnClickListener(
                    view->{
                        Intent intent = sendMessage(content);
                        if (intent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(intent);
                        }
                    }
            );
        }
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(50,50,50,50);
        newBtn.setLayoutParams(layoutParams);
        return newBtn;
    }

    public static void createSettingsButtons(Context context, LinearLayout widgets){
        widgets.removeAllViews();
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        for (int i=0; i<4; i++){
            MaterialButton newBtn=new MaterialButton(context);
            newBtn.setBackgroundColor(Color.MAGENTA);

            Intent intent;

            switch (i){
                case 0:
                {
                    intent= new Intent(Settings.ACTION_WIFI_SETTINGS);
                    newBtn.setText("Wifi");
                    newBtn.setIcon(AppCompatResources.getDrawable(context, R.drawable.wifi));
                    break;
                }
                case 1:{
                    intent= new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                    newBtn.setText("Bluetooth");
                    newBtn.setIcon(AppCompatResources.getDrawable(context, R.drawable.bluetooth));
                    break;
                }
                case 2:{
                    intent= new Intent(Settings.ACTION_DATE_SETTINGS);
                    newBtn.setText("Date");
                    newBtn.setIcon(AppCompatResources.getDrawable(context, R.drawable.date));
                    break;
                }
                default:{
                    intent= new Intent(Settings.ACTION_SETTINGS);
                    newBtn.setText("General settings");
                    newBtn.setIcon(AppCompatResources.getDrawable(context, R.drawable.settings));
                    break;
                }
            }
            newBtn.setLayoutParams(params);
            newBtn.setOnClickListener(
                    v->{
                        context.startActivity(intent);
                    }
            );
            widgets.addView(newBtn);
        }
    }

}
