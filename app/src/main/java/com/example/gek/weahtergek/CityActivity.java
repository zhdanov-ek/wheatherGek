package com.example.gek.weahtergek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gek.weahtergek.models.City;
import com.example.gek.weahtergek.models.Condition;

import io.realm.Realm;
import io.realm.RealmResults;

public class CityActivity extends AppCompatActivity {
    private Condition condition;
    private String cityName;
    private String cityCountry;
    private String cityKey;
    private TextView tvCityName;
    private TextView tvWeatherText;

    private Button btnRemove;
    private Button btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);


        tvWeatherText = (TextView) findViewById(R.id.tvWeatherText);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnDetail = (Button) findViewById(R.id.btnDetail);


        if ((getIntent() != null) && (getIntent().hasExtra(Const.EXTRA_CITY_KEY))){
            cityKey = getIntent().getStringExtra(Const.EXTRA_CITY_KEY);
            cityName = getIntent().getStringExtra(Const.EXTRA_CITY_NAME);
            cityCountry = getIntent().getStringExtra(Const.EXTRA_CITY_COUNTRY);
            tvCityName.setText(cityName + " (" + cityCountry + ")");

        } else {
            tvCityName.setText("Problem with data");
            btnDetail.setVisibility(View.GONE);
            btnRemove.setVisibility(View.GONE);
        }

        btnRemove.setOnClickListener(v -> removeCity());

    }

    private void removeCity(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<City> cities = realm.where(City.class)
                .equalTo(Const.CITY_KEY, cityKey)
                .findAll();
        realm.beginTransaction();
        cities.get(0).deleteFromRealm();
        realm.commitTransaction();
        finish();
    }
}
