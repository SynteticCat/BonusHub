package com.example.bonuslib.preferenceExtensions.TablePreference;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
    public void onBindViewHolder (final ViewHolder holder, int pos) {
        Float price = null;
        Float bonus = null;
        try {
            price = (Float) priceMap.keySet().toArray()[pos];
            bonus = priceMap.get(price);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        holder.price.setText(price != null ? price.toString() : "");
        holder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                onPriceChanged(holder);
            }
        });
        holder.bonus.setText(bonus != null ? bonus.toString() : "");
        holder.bonus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                onBonusChanged(holder);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return priceMap.size();
    }

    public void removeItem(final ViewHolder holder) {
        try {
            priceMap.remove(Float.parseFloat(holder.price.getText().toString()));
            notifyItemRemoved(holder.getAdapterPosition());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void onPriceChanged(final ViewHolder holder) {
        try {
            Float key = (Float) priceMap.keySet().toArray()[holder.getAdapterPosition()];
            priceMap.put(Float.valueOf(holder.price.getText().toString()), priceMap.remove(key));
            notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void onBonusChanged(final ViewHolder holder) {
        try {
            Float key = Float.parseFloat(holder.price.getText().toString());
            Float value = Float.parseFloat(holder.bonus.getText().toString());
            priceMap.put(key, value);
            notifyItemChanged(holder.getAdapterPosition());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
