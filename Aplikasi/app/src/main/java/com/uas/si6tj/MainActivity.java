package com.uas.si6tj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EDosenJsonPlaceHolderAPI jsonPlaceHolderAPI;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.flot);
        //Ketika di klik
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EdosenActivity.class);
                startActivity(i);
            }
        });

        //Penampil data
        textView = findViewById(R.id.text_edosen_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.79.14/php/") //jika data tidak tampil ubah ip
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderAPI = retrofit.create(EDosenJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts() {
        Map<String, String > parameters = new HashMap<>();
        Call<List<EDosenPost>> call = jsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<EDosenPost>>() {
            @Override
            public void onResponse(Call<List<EDosenPost>> call, Response<List<EDosenPost>> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code: " + response.code());
                    return;
                }
                List<EDosenPost> posts = response.body();
                for (EDosenPost post : posts) {
                    String content = "";
                    content += "NIDN: " + post.getNidn() + "\n";
                    content += "Nama Dosen : " + post.getNamaDosen() + "\n";
                    content += "Jabatan : " + post.getJabatan() + "\n";
                    content += "Golongan Pangkat : " + post.getGolPang() + "\n";
                    content += "Keahlian : " + post.getKeahlian() + "\n";
                    content += "Program Studi : " + post.getProgramStudi() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<EDosenPost>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}