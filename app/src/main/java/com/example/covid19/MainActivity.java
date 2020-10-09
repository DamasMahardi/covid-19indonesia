  package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covid19.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

  public class MainActivity extends AppCompatActivity {
      private final  String TAG = "MainActivity";
      private TextView textView;
      private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewResult);
        progressBar = findViewById(R.id.progresBar);
        getDataFromApi();

    }


    private void getDataFromApi(){
        showLoading(true);

        ApiService.endpoint().getData()
                .enqueue(new Callback<List<MainModel>>() {
                    @Override
                    public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                        showLoading(false);
                        Log.d(TAG, response.toString());
                        if (response.isSuccessful()){
                            List<MainModel> results = response.body();
                            showResult(results);
                        }
                    } 

                    @Override
                    public void onFailure(Call<List<MainModel>> call, Throwable t) {
                        Log.d(TAG, t.toString());
                        showLoading(false);

                    }
                });

    }
    private void showLoading(Boolean loading){
        if (loading){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showResult(List<MainModel> mainModels){
        MainModel result = mainModels.get(0);
        textView.setText(
                "Positif: "+result.getPositif()+"\nSembuh: "+result.getSembuh()+"\nMeninggal: "+result.getMeninggal()
                +"\nDirawat: "+result.getDirawat()
        );
    }
}