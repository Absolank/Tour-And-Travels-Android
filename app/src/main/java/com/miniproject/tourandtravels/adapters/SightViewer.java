package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.HotelData;
import com.miniproject.tourandtravels.api.model.Sight;

import java.util.List;

public class SightViewer extends BaseAdapter {

    private List<Sight> sights;

    public void setSights(List<Sight> sights) {
        this.sights = sights;
    }

    @Override
    public int getCount() {
        if(sights != null)
            return sights.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sight_viewer, parent, false);
        }
        Sight sight = sights.get(position);
        new SightViewHolder(convertView).setData(sight);
        return convertView;
    }

    private static class SightViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        SightViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sight_name);
        }
        void setData(Sight sight) {
            textView.setText(sight.getSightName());
        }
    }
}
