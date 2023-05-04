package com.example.nhidcldirectory.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitService {
   private Retrofit retrofit;
   public retrofitService(){
       initializeRetrofit();
   }

    private void initializeRetrofit() {
       retrofit = new Retrofit.Builder()
               .baseUrl("http://192.168.23.87:8181")
               .addConverterFactory(GsonConverterFactory
               .create(new Gson())).build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
