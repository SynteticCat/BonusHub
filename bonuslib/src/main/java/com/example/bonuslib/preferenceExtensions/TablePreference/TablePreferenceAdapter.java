package com.example.bonuslib.preferenceExtensions.TablePreference;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bonuslib.R;

import java.util.TreeMap;


/**
 * Created by ivan on 5/22/17.
 */

public class TablePreferenceAdapter extends RecyclerView.Adapter<TablePreferenceAdapter.ViewHolder> {
    private Context context;
    private TreeMap<Float, Float> priceMap;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public EditText price;
        public EditText bonus;
        public ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            price = (EditText) view.findViewById(R.id.price);
            bonus = (EditText) view.findViewById(R.id.bonus);
            deleteButton = (ImageButton) view.findViewById(R.id.delete_button);
        }
    }

    public TablePreferenceAdapter(Context context, TreeMap<Float, Float> priceMap) {
        this.context = context;
        this.priceMap = priceMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pref_table_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int pos) {
        Float price = null;
        Float bonus = null;
        try {
            price = priceMap.keySet().toArray()[pos];
            bonus = priceMap.get(price);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        holder.price.setText(price != null ? price.toString() : "");
        holder.bonus.setText(bonus != null ? bonus.toString() : "");
    }

    @Override
    public int getItemCount() {
        return priceMap.size();
    }
}
