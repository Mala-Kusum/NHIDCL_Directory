package com.example.nhidcldirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class page1 extends AppCompatActivity {

    Button button;
    FloatingActionButton add;
    public static String selected;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        Context context=page1.this;
        sharedPref = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        ListView listView=findViewById(R.id.list);
        add=findViewById(R.id.add_fab);
        String[] s={"Managing Director","Director","Executive Director","Chief Vigilance Officer","General Manager","Deputy General Manager","Senior Manager","Manager","Deputy Manager","Private Secretary","Assistant Manager","Junior Manager","Graduate Engineer","Trainee Graduate Engineer","Accountant","Office Assistant","IT Engineer",
                "Young Legal Professional","Site Engineer","Data Entry Operator","System Engineer","SAP Consultant","Technical Financial Executive","Stenographer","Architect Trainee","Legal Advisor","Legal Professional","Other"};
        ArrayAdapter<String> designation=new ArrayAdapter<String>(page1.this,android.R.layout.simple_list_item_1,s);
        button=findViewById(R.id.allsearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(page1.this,AllSearch.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(page1.this,add_data.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(designation);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent,View view,int position,long id) {
                selected = (String) parent.getItemAtPosition(position);
                Intent intent2=new Intent(page1.this,Executive_Director.class);
                startActivity(intent2);
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!MainActivity.guest) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.kebab_menu, menu);
        }
//        finish();
        return true;
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.item3){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", "");
            editor.apply();
            Log.d("logouttag", sharedPref.getString("email",""));
            Intent intent=new Intent(page1.this,MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.item2) {
            Intent intent=new Intent(page1.this,Change_password.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.item1) {
            Intent intent=new Intent(page1.this,Profile.class);
            startActivity(intent);
        }
        return true;
    }*/
}