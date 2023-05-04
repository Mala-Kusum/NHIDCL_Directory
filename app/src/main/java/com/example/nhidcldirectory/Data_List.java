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
import com.example.nhidcldirectory.retrofit.EmployeeApi;
import com.example.nhidcldirectory.retrofit.retrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Data_List extends AppCompatActivity {
   
    List<Employee> list,list2;
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
        setContentView(R.layout.activity_data_list);
       // EventChangeListener();
        flag=true;
        flag2=true;
        Log.e("Taaag", String.valueOf(list));
        Context context=Data_List.this;
        recycler=findViewById(R.id.executive_director);
        recycler.setLayoutManager(new LinearLayoutManager(Data_List.this));
        sharedPref = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        switch (page1.selected){
            case  "Managing Director":
                query = "Managing Director";
                //Log.e("Taaag", "query");
                break;
            case  "Graduate Engineer":
                query = "Graduate Engineer";
                // Log.e("Taaag", "query");
                break;
            case  "Director":
                query = "Director";
                break;
            case  "Executive Director":
                query = "Executive Director";
                break;
            case  "Chief Vigilance Officer":
                query = "Chief Vigilance Officer";
                break;
            case  "General Manager":
                query = "General Manager";
                break;
            case  "Deputy General Manager":
                query = "Deputy General Manager";
                break;
            case  "Senior Manager":
                query = "Senior Manager";
                break;
            case  "Manager":
                query = "Manager";
                break;
            case  "Private Secretary":
                query = "Private Secretary";
                break;
            case  "Assistant Manager":
                query = "Assistant Manager";
                break;
            case  "Junior Manager":
                query = "Junior Manager";
                break;
            case  "Deputy Manager":
                query = "Deputy Manager";
                break;
            case  "Trainee Graduate Engineer":
                query = "Trainee Graduate Engineer";
                Log.e("Taaag", "query");
                break;
            case  "Accountant":
                query = "Accountant";
                Log.e("Taaag", "query");
                break;
            case  "Office Assistant":
                query = "Office Assistant";
                Log.e("Taaag", "query");
                break;
            case  "IT Engineer":
                query = "IT Engineer";
                Log.e("Taaag", "query");
                break;
            case  "Young Legal Professional":
                query = "Young Legal Professional";
                Log.e("Taaag", "query");
                break;
            case  "Site Engineer":
                query = "Site Engineer";
                Log.e("Taaag", "query");
                break;
            case  "System Engineer":
                query = "System Engineer";
                Log.e("Taaag", "query");
                break;
            case  "SAP Consultant":
                query = "SAP Consultant";
                Log.e("Taaag", "query");
                break;
            case  "Technical Financial Executive":
                query = "Technical Financial Executive";
                Log.e("Taaag", "query");
                break;
            case  "Stenographer":
                query = "Stenographer";
                Log.e("Taaag", "query");
                break;
            case  "Architect Trainee":
                query = "Architect Trainee";
                Log.e("Taaag", "query");
                break;
            case  "Legal Advisor":
                query = "Legal Advisor";
                Log.e("Taaag", "query");
                break;
            case  "Legal Professional":
                query = "Legal Professional";
                Log.e("Taaag", "query");
                break;
            case  "Data Entry Operator":
                query = "Data Entry Operator";
                Log.e("Taaag", "query");
                break;
           /* case  "Other":
                flag=false;
                Log.e("incase", String.valueOf(flag));
                query = "SELECT * FROM employee WHERE designation NOT IN ('Managing Director','Director','Executive Director','Chief Vigilance Officer','General Manager','Deputy General Manager','Senior Manager','Manager','Deputy Manager','Private Secretary','Assistant Manager','Junior Manager','Graduate Engineer','Trainee Graduate Engineer','Accountant','Office Assistant','IT Engineer','Young Legal Professional','Site Engineer','Data Entry Operator','System Engineer','SAP Consultant','Technical Financial Executive','Stenographer','Architect Trainee','Legal Advisor','Legal Professional');";
                Log.e("Taaag", "query");
                break;
*/
        }

        retrofitService retroservice = new retrofitService();
        EmployeeApi employeeapi=retroservice.getRetrofit().create(EmployeeApi.class);
        employeeapi.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                System.out.println(response.body());
                list=(ArrayList<Employee>) response.body();
                list2=new ArrayList<Employee>();
                for (Employee e:list) {
                    if(e.getDesignation().equals(query)){
                        list2.add(e);
                    }
                }
                adapt=new MAdapter(Data_List.this, (ArrayList<Employee>) list2);
                recycler.setAdapter(adapt);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(Data_List.this, "data fetch failed!", Toast.LENGTH_SHORT).show();
                Log.d("data fetch failed",t.toString());
            }
        });
    }
   /* protected void EventChangeListener() {



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
*/    public boolean onCreateOptionsMenu(Menu menu) {
        if(!MainActivity.guest) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.kebab_menu, menu);
        }
        return true;
    }
   /* @Override
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
    }*/

}

