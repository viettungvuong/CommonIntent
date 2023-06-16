package com.tung.testintent;

import static java.net.Proxy.Type.HTTP;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class functions {
    private static String phoneNumber;
    private static String content;
    public static Intent sendMessage(String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("sms:"));  // Only SMS apps respond to this.
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

}
