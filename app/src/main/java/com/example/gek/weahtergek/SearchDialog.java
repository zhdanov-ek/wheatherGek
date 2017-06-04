package com.example.gek.weahtergek;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gek.weahtergek.models.City;
import com.example.gek.weahtergek.rest.ApiFactory;
import com.example.gek.weahtergek.rest.WeatherInterface;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gek on 03.06.17.
 */

public class SearchDialog extends DialogFragment {
    private static final String TAG = "SEARCH_D";
    EditText etSearch;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.mes_input_city);

        builder.setView(R.layout.edit_text_search);

        builder.setPositiveButton(R.string.search, (dialog, id) -> {
                    etSearch = (EditText) ((AlertDialog)dialog).findViewById(R.id.etSearch);
                    makeRequest(etSearch);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancelled the dialog
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void makeRequest(EditText etSearch){
        if ((etSearch == null) || (etSearch.getText().length() < 3)){
            Log.d(TAG, "makeRequest: etSearch empty or short");
        } else {
            String request = Const.SEARCH_CITY + Const.KEY + "q=" + etSearch.getText().toString();
            Log.d(TAG, "makeRequest: " + request);

            WeatherInterface api = ApiFactory.getWeatherInterface();
            Call<List<City>> call = api.getCities(Const.KEY, etSearch.getText().toString());
            call.enqueue(new Callback<List<City>>() {
                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                    if ((response.isSuccessful()) && (!response.body().isEmpty())){
                        List<City> cities = response.body();
                        Realm realm = Realm.getDefaultInstance();
                        try {
                            for (City city: cities) {
                                RealmResults<City> result = realm.where(City.class)
                                        .equalTo(Const.CITY_KEY, city.getKey())
                                        .findAll();

                                // if city new then add to DB
                                if (result.size() == 0){
                                    realm.beginTransaction();
                                    realm.insertOrUpdate(city);
                                    realm.commitTransaction();
                                }
                            }

                        } finally {
                            realm.close();
                        }
                    } else {
                        Log.d(TAG, "onResponse: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    Log.d(TAG, "Error " + t.getMessage());
                }
            });
        }

    }
}
