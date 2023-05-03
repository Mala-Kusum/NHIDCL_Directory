package com.example.nhidcldirectory;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.nhidcldirectory.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Executive_Director extends AppCompatActivity {
   
    ArrayList<Employee> list;
    ArrayList<Employee> stateList,nameList;
    RecyclerView recycler;
    boolean flag,flag2;
    MAdapter adapt;
    EditText filter1,filter2;
    SharedPreferences sharedPref;;
    String[] s={"Managing Director","Director","Executive Director","Chief Vigilance Officer","General Manager","Deputy General Manager","Senior Manager","Manager","Deputy Manager","Private Secretary","Assistant Manager","Junior Manager","Graduate Engineer","Trainee Graduate Engineer","Accountant","Office Assistant","IT Engineer",
            "Young Legal Professional","Site Engineer","Data Entry Operator","System Engineer","SAP Consultant","Technical Financial Executive","Stenographer","Architect Trainee","Legal Advisor","Legal Professional"};
    String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventChangeListener();
        flag=true;
        flag2=true;
        Log.e("Taaag", String.valueOf(list));
        Context context=Executive_Director.this;
        adapt=new MAdapter(this,list);
        sharedPref = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        recycler.setAdapter(adapt);
    }
    protected void EventChangeListener() {
        setContentView(R.layout.activity_data_list);
        filter1=findViewById(R.id.filter1);
        filter2=findViewById(R.id.filter2);
        recycler = findViewById(R.id.executive_director);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        Log.e("tag",page1.selected);
        switch (page1.selected){
            case  "Managing Director":
                query = "SELECT * FROM employee WHERE designation='Managing Director';";
                //Log.e("Taaag", "query");
                break;
            case  "Graduate Engineer":
                query = "SELECT * FROM employee WHERE designation='Graduate Engineer';";
                // Log.e("Taaag", "query");
                break;
            case  "Director":
                query = "SELECT * FROM employee WHERE designation='Director';";
                break;
            case  "Executive Director":
                query = "SELECT * FROM employee WHERE designation='Executive Director';";
                break;
            case  "Chief Vigilance Officer":
                query = "SELECT * FROM employee WHERE designation='Chief Vigilance Officer';";
                break;
            case  "General Manager":
                query = "SELECT * FROM employee WHERE designation='General Manager';";
                break;
            case  "Deputy General Manager":
                query = "SELECT * FROM employee WHERE designation='Deputy General Manager';";
                break;
            case  "Senior Manager":
                query = "SELECT * FROM employee WHERE designation='Senior Manager';";
                break;
            case  "Manager":
                query = "SELECT * FROM employee WHERE designation='Manager';";
                break;
            case  "Private Secretary":
                query = "SELECT * FROM employee WHERE designation='Private Secretary';";
                break;
            case  "Assistant Manager":
                query = "SELECT * FROM employee WHERE designation='Assistant Manager';";
                break;
            case  "Junior Manager":
                query = "SELECT * FROM employee WHERE designation='Junior Manager';";
                break;
            case  "Deputy Manager":
                query = "SELECT * FROM employee WHERE designation='Deputy Manager';";
                break;
            case  "Trainee Graduate Engineer":
                query = "SELECT * FROM employee WHERE designation='Trainee Graduate Engineer';";
                Log.e("Taaag", "query");
                break;
            case  "Accountant":
                query = "SELECT * FROM employee WHERE designation='Accountant';";
                Log.e("Taaag", "query");
                break;
            case  "Office Assistant":
                query = "SELECT * FROM employee WHERE designation='Office Assistant';";
                Log.e("Taaag", "query");
                break;
            case  "IT Engineer":
                query = "SELECT * FROM employee WHERE designation='IT Engineer';";
                Log.e("Taaag", "query");
                break;
            case  "Young Legal Professional":
                query = "SELECT * FROM employee WHERE designation='Young Legal Professional';";
                Log.e("Taaag", "query");
                break;
            case  "Site Engineer":
                query = "SELECT * FROM employee WHERE designation='Site Engineer';";
                Log.e("Taaag", "query");
                break;
            case  "System Engineer":
                query = "SELECT * FROM employee WHERE designation='System Engineer';";
                Log.e("Taaag", "query");
                break;
            case  "SAP Consultant":
                query = "SELECT * FROM employee WHERE designation='SAP Consultant';";
                Log.e("Taaag", "query");
                break;
            case  "Technical Financial Executive":
                query = "SELECT * FROM employee WHERE designation='Technical Financial Executive';";
                Log.e("Taaag", "query");
                break;
            case  "Stenographer":
                query = "SELECT * FROM employee WHERE designation='Stenographer';";
                Log.e("Taaag", "query");
                break;
            case  "Architect Trainee":
                query = "SELECT * FROM employee WHERE designation='Architect Trainee';";
                Log.e("Taaag", "query");
                break;
            case  "Legal Advisor":
                query = "SELECT * FROM employee WHERE designation='Legal Advisor';";
                Log.e("Taaag", "query");
                break;
            case  "Legal Professional":
                query = "SELECT * FROM employee WHERE designation='Legal Professional';";
                Log.e("Taaag", "query");
                break;
            case  "Data Entry Operator":
                query = "SELECT * FROM employee WHERE designation='Data Entry Operator';";
                Log.e("Taaag", "query");
                break;
            case  "Other":
                flag=false;
                Log.e("incase", String.valueOf(flag));
                query = "SELECT * FROM employee WHERE designation NOT IN ('Managing Director','Director','Executive Director','Chief Vigilance Officer','General Manager','Deputy General Manager','Senior Manager','Manager','Deputy Manager','Private Secretary','Assistant Manager','Junior Manager','Graduate Engineer','Trainee Graduate Engineer','Accountant','Office Assistant','IT Engineer','Young Legal Professional','Site Engineer','Data Entry Operator','System Engineer','SAP Consultant','Technical Financial Executive','Stenographer','Architect Trainee','Legal Advisor','Legal Professional');";
                Log.e("Taaag", "query");
                break;

        }
        //query = noteRef.whereEqualTo(Designation, "Executive Director").orderBy(Name);
        list = new ArrayList<Employee>();
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                Log.e("Taaag", String.valueOf(flag));
                if (flag==true) {
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            list.add(dc.getDocument().toObject(Employee.class));
                        }
                        adapt.notifyDataSetChanged();
                    }
                }
                else{
                    Log.e("TAGese","in else");
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            Employee m=dc.getDocument().toObject(Employee.class);
                            Log.e("TAGm",m.getNAME());
                            for(int i=0;i<s.length;i++) {
                                if(m.getDESIGNATION().equals(s[i])) {
                                    flag2=false;
                                    break;
                                }
                            }
                            if(flag2){
                                list.add(m);
                            }
                            adapt.notifyDataSetChanged();
                        }
                    }
                }

            }
        });
        addTextListener();
    }
    public void addTextListener(){

        filter1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                String q = query.toString().toLowerCase();

                nameList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).getNAME().toLowerCase();
                    if (text.contains(q)) {

                        nameList.add(list.get(i));
                    }
                }
                if (nameList.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    Toast.makeText(Executive_Director.this, "No Data Found..", Toast.LENGTH_SHORT).show();
                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    adapt.filterList(nameList);
                } // data set changed
            }
        });


        filter2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                stateList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).getSTATE().toLowerCase();
                    if (text.contains(query)) {

                        stateList.add(list.get(i));
                    }
                }if (stateList.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    Toast.makeText(Executive_Director.this, "No Data Found..", Toast.LENGTH_SHORT).show();
                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    adapt.filterList(stateList);
                }   // data set changed
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!MainActivity.guest) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.kebab_menu, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.item3){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", "");
            editor.apply();
            Log.d("logouttag", sharedPref.getString("email",""));
            Intent intent=new Intent(Executive_Director.this,MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.item2) {
            Intent intent=new Intent(Executive_Director.this,Change_password.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.item1) {
            Intent intent=new Intent(Executive_Director.this,Profile.class);
            startActivity(intent);
        }
        return true;
    }

}

