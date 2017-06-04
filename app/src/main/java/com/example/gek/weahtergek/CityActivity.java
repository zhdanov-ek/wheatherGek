package com.example.gek.weahtergek;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gek.weahtergek.models.City;
import com.example.gek.weahtergek.models.Condition;
import com.example.gek.weahtergek.rest.ApiFactory;
import com.example.gek.weahtergek.rest.ConditionInterface;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityActivity extends AppCompatActivity {
    private final static String TAG = "CITY";
    private Condition mCondition;
    private String cityName;
    private String cityCountry;
    private String cityKey;
    private TextView tvCityName;
    private TextView tvWeatherText;

    private Button btnRemove;
    private Button btnDetail;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        mRealm = Realm.getDefaultInstance();

        tvWeatherText = (TextView) findViewById(R.id.tvWeatherText);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnDetail = (Button) findViewById(R.id.btnDetail);

        if ((getIntent() != null) && (getIntent().hasExtra(Const.EXTRA_CITY_KEY))){
            cityKey = getIntent().getStringExtra(Const.EXTRA_CITY_KEY);
            cityName = getIntent().getStringExtra(Const.EXTRA_CITY_NAME);
            cityCountry = getIntent().getStringExtra(Const.EXTRA_CITY_COUNTRY);
            tvCityName.setText(cityName + " (" + cityCountry + ")");
            getCondition(cityKey);
        } else {
            tvCityName.setText("Problem with data");
            btnDetail.setVisibility(View.GONE);
            btnRemove.setVisibility(View.GONE);
        }

        btnRemove.setOnClickListener(v -> removeCity());
        btnDetail.setOnClickListener(v -> showDetails());

    }


    //todo now request work with static key (need change interface in Retrofit)
    private void getCondition(String key){
        ConditionInterface api = ApiFactory.getConditionInterface();
        Call<List<Condition>> call = api.getCondition(Const.KEY);
        call.enqueue(new Callback<List<Condition>>() {
            @Override
            public void onResponse(Call<List<Condition>> call, Response<List<Condition>> response) {
                Log.d(TAG, "onResponse: ");
                if ((response.isSuccessful()) && (response.body() != null)){
                    mCondition = response.body().get(0);
                    tvWeatherText.setText(mCondition.getWeatherText());
                    btnDetail.setEnabled(true);
                    Log.d(TAG, "onResponse: successful");
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Condition>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private void removeCity(){
        RealmResults<City> cities = mRealm.where(City.class)
                .equalTo(Const.CITY_KEY, cityKey)
                .equalTo(Const.CITY_ENGLISH_NAME, cityName)
                .findAll();
        mRealm.beginTransaction();
        cities.get(0).deleteFromRealm();
        mRealm.commitTransaction();
        finish();
    }

    private void showDetails(){
        if ((mCondition.getMobileLink() != null) && (mCondition.getMobileLink().length() > 0)){
            Intent intentDetails = new Intent(Intent.ACTION_VIEW);
            intentDetails.setData(Uri.parse(mCondition.getMobileLink()));
            startActivity(intentDetails);
        }
    }

    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }
}
