package com.example.bonuslib.preferenceExtensions.TablePreference;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.bonuslib.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.TreeMap;

/**
 * Created by ivan on 5/23/17.
 */

public class TablePreference extends Preference {
    private String json = "";
    private @IdRes int layoutResId = R.layout.pref_table;
    private RecyclerView tableRoot; // TableLayout for TablePreferenceAdapter's
    private FloatingActionButton fab; // fragment fab
    private TreeMap<Float, Float> priceMap; // prices-to-values map - for discount amounts, etc.
    private TablePreferenceAdapter adapter;

    public TablePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFragment(TablePreferenceLayout.class.getName());
    }

    public TablePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFragment(TablePreferenceLayout.class.getName());
    }

    public TablePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFragment(TablePreferenceLayout.class.getName());
    }

    public TablePreference(Context context) {
        super(context);
        setFragment(TablePreferenceLayout.class.getName());
    }

    public TreeMap<Float, Float> getJson() {
        Gson gson = new Gson();
        Type type = new TypeToken<TreeMap<Float, Float>>(){}.getType();
        TreeMap<Float, Float> priceMap = gson.fromJson(json, type);
        return priceMap;
    }

    public void setJson(TreeMap<Float, Float> priceMap) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(priceMap);
        this.json = json;
        persistString(json);
    }

//    @Override
//    public void onBindViewHolder(PreferenceViewHolder rootView) {
//        super.onBindViewHolder(rootView);
//        //find table layout
//        tableRoot = (RecyclerView) rootView.findViewById(R.id.prices_rv);
//        // find FAB
//        fab = (FloatingActionButton) rootView.findViewById(R.id.table_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onFABClick();
//            }
//        });
//        renderTable();
//    }
//
//    protected void renderTable() {
//       adapter = new TablePreferenceAdapter(getContext(), priceMap);
//       tableRoot.setAdapter(adapter);
//       tableRoot.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//    protected void onFABClick() {
//        priceMap.put(new Float(0.0), null);
//        // find the position of new item in TreeMap for notify the adapter
//        int p = 0;
//        for (; !priceMap.keySet().toArray()[p].equals(new Float(0.0)); p++);
//        adapter.notifyItemInserted(p);
//        tableRoot.scrollToPosition(p);
//    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setJson(new TreeMap<Float, Float>());
    }

//    @Override
//    public int getDialogLayoutResource() {
//        return layoutResId;
//    }
}