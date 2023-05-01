package com.example.nhidcldirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button1,guest_button;
    ImageButton helperph,helperem;
    EditText id;
    TextInputEditText pswd;
    TextView link;
    public static String EMAIL,PIN;
    public static boolean guest = false;
    public static final String Name="NAME",Email="EMAIL",Designation="DESIGNATION",Department="DEPARTMENT",
            State="STATE",Address="ADDRESS",Mobile="MOBILE",Landline="LANDLINE",Password="PASSWORD",Type="TYPE",Pin="PIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context=MainActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        EMAIL=sharedPref.getString("email","");
        Log.d("sharedpref", sharedPref.getString("email",""));
/*        if(!sharedPref.getString("email","").equals("")){
            Log.d("in if","in if");
            Intent intent=new Intent(MainActivity.this,LoggedInUser.class);
            startActivity(intent);
        }*/
        setContentView(R.layout.activity_main);
        guest_button=findViewById(R.id.guest_button);
        button1=findViewById(R.id.button);
        helperem=findViewById(R.id.emailh);
        helperph=findViewById(R.id.phoneh);
        id=findViewById(R.id.id);
        pswd=findViewById(R.id.pswd);
        link=findViewById(R.id.link);
        //Log.d("TAG-mail", sharedPref.getString("email",""));
        //id.setText(sharedPref.getString("email",""));
        /*guest_button.setOnClickListener(view -> {
            MainActivity.guest=true;
            Intent intent=new Intent(MainActivity.this,page1.class);
            startActivity(intent);
        });*/
        helperem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                //emailIntent.putExtra(Intent.EXTRA_, " ");
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sinha.shreya@nhidcl.com"});
                emailIntent.putExtra(Intent.EXTRA_CC, "");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Problem with NHIDCL Directory");
                emailIntent.putExtra(Intent.EXTRA_TEXT, " ");
                startActivity(emailIntent);
            }
        });
        helperph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"01123461600"));
                startActivity(intent);
            }
        });
        /*link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });*/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EMAIL=id.getText().toString();
                PIN=pswd.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email", EMAIL);
                editor.apply();
                MainActivity.guest=false;
                /*Intent intent=new Intent(MainActivity.this,page1.class);
                startActivity(intent);*/
                Intent intent=new Intent(MainActivity.this,add_data.class);
                startActivity(intent);
            }
        });
    }
}