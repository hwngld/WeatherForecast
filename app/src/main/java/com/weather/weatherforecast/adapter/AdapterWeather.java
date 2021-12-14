package com.weather.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.weather.weatherforecast.R;
import com.weather.weatherforecast.model.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder> {

    private List<Weather> weatherList = new ArrayList<>();
    private Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather,parent,false);

        return new ViewHolder(view);
    }

    public AdapterWeather(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);

        SimpleDateFormat formatDate = new SimpleDateFormat("EEE");
        Date date = new Date(weather.getDate()*1000);
        holder.tvDay.setText(formatDate.format(date));
        holder.tvTempRange.setText(String.valueOf(weather.getTempMin())+"°-"+String.valueOf(weather.getTempMax())+"°");
        Picasso.with(context).load("http://openweathermap.org/img/wn/"+weather.getIcon()+"@2x.png").into(holder.imgWeather);
    }
    public void setData(List<Weather> list){
        this.weatherList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDay, tvTempRange;
        ImageView imgWeather;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvTempRange = itemView.findViewById(R.id.tvTempRange);
            imgWeather = itemView.findViewById(R.id.imgWeather);
        }
    }
}
