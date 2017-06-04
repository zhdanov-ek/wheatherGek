package com.example.gek.weahtergek;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gek.weahtergek.models.City;

import java.util.List;
import java.util.zip.Inflater;

import io.realm.RealmResults;

/**
 * Created by gek on 04.06.17.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private RealmResults<City> cities;
    private Context ctx;
    private Inflater inflater;

    public CitiesAdapter(RealmResults<City> cities, Context ctx) {
        this.cities = cities;
        this.ctx = ctx;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.city_item, parent, false);
        CityViewHolder viewHolder = new CityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.tvCityName.setText(cities.get(position).getEnglishName());
        holder.tvCityCountry.setText(cities.get(position).getCountry().getEnglishName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvCityName;
        private TextView tvCityCountry;

        private CityViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
            tvCityCountry = (TextView) itemView.findViewById(R.id.tvCityCountry);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intentCondition = new Intent(ctx, CityActivity.class);
            intentCondition.putExtra(
                    Const.EXTRA_CITY_NAME,
                    cities.get(getAdapterPosition()).getEnglishName());
            intentCondition.putExtra(
                    Const.EXTRA_CITY_COUNTRY,
                    cities.get(getAdapterPosition()).getCountry().getEnglishName());
            intentCondition.putExtra(
                    Const.EXTRA_CITY_KEY,
                    cities.get(getAdapterPosition()).getKey());
            intentCondition.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intentCondition);
        }
    }
}
