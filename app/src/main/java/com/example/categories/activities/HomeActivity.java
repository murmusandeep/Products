package com.example.categories.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.categories.R;
import com.example.categories.adapters.HomeActivityAdapter;
import com.example.categories.api.models.Data;
import com.example.categories.api.service.DataClient;
import com.example.categories.api.service.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewSection;

    private Data mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle(R.string.categories);

        mRecyclerViewSection = findViewById(R.id.recycle_view_section);
        mRecyclerViewSection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getData();
    }

    private void getData() {

        DataClient dataClient = ServiceGenerator.createService(DataClient.class);
        Call<Data> call = dataClient.getData();

        //showProgress();

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful()) {
                    if(response.body().getCode() != 200) {
                        Toast.makeText(HomeActivity.this, "api_call_error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mData = response.body();
                    /*for (int i = 0; i < mData.getContent().size(); i++) {
                        Log.d("apple", mData.getContent().get(i).getName());
                    }*/
                    HomeActivityAdapter mainActivitySectionAdapter = new HomeActivityAdapter(HomeActivity.this, mData);
                    mRecyclerViewSection.setAdapter(mainActivitySectionAdapter);

                } else {
                    Toast.makeText(HomeActivity.this, "api_response_error", Toast.LENGTH_SHORT).show();

                }
                //hideProgress();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

                // handle error
                if(t instanceof IOException) {
                    Toast.makeText(HomeActivity.this, "internet_response_error", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(HomeActivity.this, "unknown", Toast.LENGTH_SHORT).show();

                }
                //hideProgress();
            }
        });
    }
}
