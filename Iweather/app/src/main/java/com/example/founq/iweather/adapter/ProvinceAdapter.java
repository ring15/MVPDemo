package com.example.founq.iweather.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.founq.iweather.weather.view.MainActivity;
import com.example.founq.iweather.R;
import com.example.founq.iweather.choosecity.view.ChooseCityActivity;
import com.example.founq.iweather.choosecountry.view.ChooseCountryActivity;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> provinces;
    private List<Integer> codes;
    private int tag;
    private int provincecode;

    public ProvinceAdapter(Context context, List<String> provinces,  List<Integer> codes, int tag,int provincecode){
        mContext = context;
        this.provinces = provinces;
        this.codes = codes;
        this.tag = tag;
        this.provincecode = provincecode;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder viewHolder = null;
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_list,viewGroup,false);
        viewHolder =new  MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        if(provinces.get(i) != null){
            viewHolder.tvArea.setText(provinces.get(i));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag == 0){
                    Intent intent = new Intent(mContext,ChooseCityActivity.class);
                    intent.putExtra("name",provinces.get(i));
                    intent.putExtra("code",codes.get(i));
                    mContext.startActivity(intent);
                }else if(tag == 1){
                    Intent intent = new Intent(mContext,ChooseCountryActivity.class);
                    intent.putExtra("name",provinces.get(i));
                    intent.putExtra("code",provincecode);
                    intent.putExtra("citycode",codes.get(i));
                    mContext.startActivity(intent);
                }else {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("city",provinces.get(i));
                    editor.commit();
                    Intent intent = new Intent(mContext,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvArea;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArea = itemView.findViewById(R.id.tv_area);
        }
    }
}
