package com.example.nhidcldirectory.retrofit;

import com.example.nhidcldirectory.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmployeeApi {
    @GET("/employee/get-all")
    Call<List<Employee>> getAllEmployees();
    @GET("/employee")
    Call<List<Employee>> getEmployeeByDesignation(@Query("designation") String designation);
    @POST("/employee/save")
    Call<Employee> save(@Body Employee employee);
}
