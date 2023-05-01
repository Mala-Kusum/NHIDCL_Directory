package com.example.nhidcldirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhidcldirectory.model.Employee;
import com.example.nhidcldirectory.retrofit.EmployeeApi;
import com.example.nhidcldirectory.retrofit.retrofitService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_data extends AppCompatActivity {

    private static final String Name="NAME",Email="EMAIL",Designation="DESIGNATION",Department="DEPARTMENT",
            State="STATE",Address="ADDRESS",Mobile="MOBILE",Landline="LANDLINE",Password="PASSWORD",Type="TYPE";
    EditText name,des,dep,add,tel,mob,st;
    TextView email;
    Button b;
    String nam,desi,depar,addr,emai,tele,mobi,DocID,sta,password,ty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_data);
        super.onCreate(savedInstanceState);
        b=findViewById(R.id.save);
        name=findViewById(R.id.namep);
        des=findViewById(R.id.designationp);
        dep=findViewById(R.id.departmentp);
        add=findViewById(R.id.addressp);
        email=findViewById(R.id.emailp);
        tel=findViewById(R.id.telephonep);
        mob=findViewById(R.id.mobilep);
        st=findViewById(R.id.statep);
        retrofitService retroservice = new retrofitService();
        EmployeeApi employeeapi=retroservice.getRetrofit().create(EmployeeApi.class);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nam=name.getText().toString();
                desi=des.getText().toString();
                depar=dep.getText().toString();
                addr=add.getText().toString();
                emai=email.getText().toString();
                tele=tel.getText().toString();
                mobi=mob.getText().toString();
                sta=st.getText().toString();
                password=emai.substring(0,3)+sta.substring(sta.length()-3);

                /*Intent intent=new Intent(add_data.this,page1.class);
                startActivity(intent);*/
                Employee employee=new Employee();
                employee.setName(nam);
                employee.setEmail(emai);
                employee.setDepartment(depar);
                employee.setDesignation(desi);
                employee.setAddress(addr);
                employee.setState(sta);
                employee.setType("user");
                employee.setMobile(mobi);
                employee.setLandline(tele);
                employee.setPassword(emai.substring(0,3)+sta.substring(sta.length()-3));
                try {
                    employeeapi.save(employee).enqueue(new Callback<Employee>(){

                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            Toast.makeText(add_data.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Employee> call, Throwable t) {
                            Toast.makeText(add_data.this, "Save failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch(Exception e){
                    Log.d("error adding",e.toString());
                }
                //employee.setPin();
                Intent intent=new Intent(add_data.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}