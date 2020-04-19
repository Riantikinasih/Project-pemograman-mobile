package id.web.rkinasih.jualbelihpproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.web.rkinasih.jualbelihpproject.R;
import id.web.rkinasih.jualbelihpproject.model.Handphone;

public class ListAdapterHandphone extends implements Filterable {
    private Context context;
    private List<Handphone> list, filterd;

    public ListAdapterHandphone(Context context, List<Handphone> List) {
        this.context = contex;
        this.list = list;
        this.filterd = this.list;
        this.filterd = this.list;
    }
    @Override
    public int getCount(){
        return filterd.size();
    }
    @Override
    public Object getItem(int position) {
        return filterd.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_row,null);
        }
        Handphone hp = filterd.get(position);
        TextView textNama = (TextView) convertView.findViewById(R.id.text_nama);
        TextView textHarga = (TextView) convertView.findViewById(R.id.text_harga);
        TextHarga.setText(hp.getHarga());
    }
    @Override
    public Filter getFilter(){
        HandphoneFilter filter = new HandphoneFilter();
        return filter;  
        
    }
    private class HandphoneFilter extends Filter {
        @Override
        protected FilterResults perfomeFiltering(CharSequence constraint){
            List<Handphone> filteredData = new ArrayList<Handphone>();
            FilterResults result = new FilterResults();
            String filterString = constraint.toString().toLowerCase();
            for (Handphone hp : list){
            if (hp.getNama().toLowerCase().contains(filterString)) {
                filteredData.add(hp);
            }
        }
        result.count = filteredData.size();
        result.values = filteredData;
        return result;
    }
    @Override
    protected void publishResults(CharSequence contraint,FilterResults results) {
        filterd = (List<Handphone>) results.values;
        notifyDataSetChanged();
    }
}

