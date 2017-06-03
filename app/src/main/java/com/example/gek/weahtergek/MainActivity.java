package com.example.gek.weahtergek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gek.weahtergek.models.Condition;
import com.example.gek.weahtergek.rest.ApiFactory;
import com.example.gek.weahtergek.rest.ConditionInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            openSearchDialog();
        });

        ConditionInterface api = ApiFactory.getConditionInterface();
        Call<List<Condition>> call = api.getCondition(Const.KEY);
        call.enqueue(new Callback<List<Condition>>() {
            @Override
            public void onResponse(Call<List<Condition>> call, Response<List<Condition>> response) {
                Log.d(TAG, "onResponse: ");
                if (response.isSuccessful()){
                    List<Condition> cond = response.body();
                    Log.d(TAG, "onResponse: successful");
                }

            }

            @Override
            public void onFailure(Call<List<Condition>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void openSearchDialog(){
        SearchDialog searchDialog = new SearchDialog();
        searchDialog.show(getFragmentManager(), "dialog search");
    }
}
