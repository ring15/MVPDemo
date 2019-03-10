package com.example.founq.iweather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.founq.iweather.R;
import com.example.founq.iweather.gson.FutureWeather;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context mContext;
    List<FutureWeather> futureWeathers;
    List<Integer> contant;

    int[] imageIcon = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5,R.drawable.icon_6,R.drawable.icon_7,R.drawable.icon_8,R.drawable.icon_9,R.drawable.icon_10,
            R.drawable.icon_11,R.drawable.icon_12,R.drawable.icon_13,R.drawable.icon_14,R.drawable.icon_15,R.drawable.icon_16,R.drawable.icon_17,R.drawable.icon_18,R.drawable.icon_19,R.drawable.icon_20,
            R.drawable.icon_21,R.drawable.icon_22,R.drawable.icon_23,R.drawable.icon_24,R.drawable.icon_25,R.drawable.icon_26,R.drawable.icon_27,R.drawable.icon_28,R.drawable.icon_29,R.drawable.icon_30,
            R.drawable.icon_31,R.drawable.icon_32,R.drawable.icon_33};

    public MainAdapter(Context context, List<FutureWeather> futureWeathers, List<Integer> contant){
        mContext = context;
        this.futureWeathers = futureWeathers;
        this.contant = contant;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder viewHolder = null;
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_city_wether,viewGroup,false);
        viewHolder =new  MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
            viewHolder.tvWeek.setText(futureWeathers.get(i).getWeek());
            viewHolder.tvTemp.setText(futureWeathers.get(i).getTemperature());
            viewHolder.ivWeather.setImageResource(imageIcon[contant.get(i)]);
    }

    @Override
    public int getItemCount() {
        return futureWeathers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvWeek, tvTemp;
        private ImageView ivWeather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeek = itemView.findViewById(R.id.tv_week);
            tvTemp = itemView.findViewById(R.id.tv_temp_item);
            ivWeather = itemView.findViewById(R.id.iv_weather);
        }
    }
}
