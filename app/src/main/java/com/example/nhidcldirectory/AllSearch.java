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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllSearch extends AppCompatActivity {
   
    private static final String Name="NAME",Email="EMAIL",Designation="DESIGNATION",Department="DEPARTMENT",State="STATE",Address="ADDRESS",Mobile="MOBILE",Landline="LANDLINE",Password="PASSWORD";
    List<Employee> list;
    ArrayList<Employee> stateList,nameList;
    RecyclerView recycler;
    MAdapter adapt;
    EditText filter1,filter2;
    Context context;
    SharedPreferences sharedPref;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=AllSearch.this;
        recycler=findViewById(R.id.executive_director);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        sharedPref = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);

        retrofitService retroservice = new retrofitService();
        EmployeeApi employeeapi=retroservice.getRetrofit().create(EmployeeApi.class);
        employeeapi.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                adapt=new MAdapter(AllSearch.this, (ArrayList<Employee>) response.body());
                recycler.setAdapter(adapt);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(AllSearch.this, "data fetch failed!", Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adapt);
    }
    protected void EventChangeListener() {
        setContentView(R.layout.activity_all_search);
        filter1=findViewById(R.id.filter1);
        filter2=findViewById(R.id.filter2);
        recycler = findViewById(R.id.executive_director);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

       // addTextListener();
    }
/*
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
                    Toast.makeText(AllSearch.this, "No Data Found..", Toast.LENGTH_SHORT).show();
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

                String q = query.toString().toLowerCase();

                stateList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).getSTATE().toLowerCase();
                    if (text.contains(q)) {

                        stateList.add(list.get(i));
                    }
                }
                if (stateList.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    Toast.makeText(AllSearch.this, "No Data Found..", Toast.LENGTH_SHORT).show();
                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    adapt.filterList(stateList);
                } // data set changed
            }
        });
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            Intent intent=new Intent(AllSearch.this,MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.item2) {
            Intent intent=new Intent(AllSearch.this,Change_password.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.item1) {
            Intent intent=new Intent(AllSearch.this,Profile.class);
            startActivity(intent);
        }
        return true;
    }*/
}

