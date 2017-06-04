package com.example.gek.weahtergek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gek.weahtergek.models.City;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN";
    private Realm mRealm;
    private RecyclerView rv;

    private final RealmChangeListener<RealmResults<City>> changeListener =
            cities -> updateUi(cities);

    private void updateUi(RealmResults<City> cities){
        if (rv.getAdapter() == null){
            rv.setAdapter(new CitiesAdapter(cities, getBaseContext()));
        } else {
            rv.getAdapter().notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getDefaultInstance();

        loadCities();

        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            openSearchDialog();
        });

        findViewById(R.id.btnClone).setOnClickListener(v -> {
            cloneCity();
        });
    }

    private void openSearchDialog(){
        SearchDialog searchDialog = new SearchDialog();
        searchDialog.show(getFragmentManager(), "dialog search");
    }


    private void loadCities(){
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final RealmResults<City> cities = mRealm.where(City.class).findAll();
        cities.addChangeListener(changeListener);
        updateUi(cities);
    }

    private void cloneCity(){
        City city = mRealm
                .where(City.class)
                .findFirst();
        City newCity = new City();
        int num = new Random().nextInt(50);
        newCity.setEnglishName(city.getEnglishName() + num);
        newCity.setLocalizedName(city.getLocalizedName() + num);
        newCity.setKey(city.getKey() + num);
        newCity.setCountry(city.getCountry());

        mRealm.beginTransaction();
        mRealm.insert(newCity);
        mRealm.commitTransaction();
    }


    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }
}
