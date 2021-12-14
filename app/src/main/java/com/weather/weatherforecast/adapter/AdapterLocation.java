package com.weather.weatherforecast.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherforecast.R;
import com.weather.weatherforecast.datalocal.DataManager;
import com.weather.weatherforecast.model.ItemLocation;

import java.util.ArrayList;
import java.util.List;

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.ViewHolder> implements Filterable {
    private List<ItemLocation> list;
    private Context context;
    private IAdapter iAdapter;
    private List<ItemLocation> listOld;
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    list = listOld;
                }else{
                    List<ItemLocation> listSearch = new ArrayList<>();
                    for(ItemLocation i: listOld){
                        if(i.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            listSearch.add(i);
                        }
                    }
                    list = listSearch;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<ItemLocation>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface IAdapter{
        public void clickChanged(ItemLocation itemLocation);
    }

    public AdapterLocation(Context context, IAdapter iAdapter){
        this.context = context;
        this.iAdapter = iAdapter;
    }
    public void setData(List<ItemLocation> list){
        this.list = list;
        listOld = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemLocation item = list.get(position);
        if(item.isSelected()){
            holder.layout.setBackground(context.getDrawable(R.drawable.selected));
            holder.imgLocation.setColorFilter(ContextCompat.getColor(context,R.color.blue), PorterDuff.Mode.MULTIPLY);
        }else{
            holder.imgLocation.setColorFilter(ContextCompat.getColor(context,R.color.gray), PorterDuff.Mode.MULTIPLY);
            holder.layout.setBackground(null);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iAdapter.clickChanged(item);
                item.setSelected(true);
                notifyDataSetChanged();
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setMessage("Bạn có muốn xóa địa điểm \""+item.getName()+"\"")
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(item);
                                if(list.size() <=0){
                                    list.add(new ItemLocation("Hà Nội", "HaNoi",true));
                                    iAdapter.clickChanged(list.get(0));
                                }
                                if(item.isSelected()){

                                    list.get(0).setSelected(true);
                                    iAdapter.clickChanged(list.get(0));
                                }
                                List<ItemLocation> locationList = DataManager.getListAllLocation();
                                for(ItemLocation i : locationList){
                                    if(i.getId().equals(item.getId()) && !i.getId().equalsIgnoreCase("Hanoi")){
                                        i.setSelected(false);
                                    }
                                }
                                DataManager.setListAllLocation(locationList);
                                DataManager.setListLocation(list);
                                notifyDataSetChanged();
                            }
                        })
                        .show();
                return false;
            }
        });
        holder.tvLocation.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        if(list.size() > 0){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLocation;
        TextView tvLocation;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLocation = itemView.findViewById(R.id.imgLocation);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            layout = itemView.findViewById(R.id.itemLayout);
        }
    }
}
