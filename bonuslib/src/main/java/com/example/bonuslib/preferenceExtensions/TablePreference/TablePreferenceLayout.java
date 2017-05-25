package com.example.bonuslib.preferenceExtensions.TablePreference;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bonuslib.R;

import java.util.TreeMap;
import java.util.Map;

public class TablePreferenceLayout extends Fragment {
    private final static String KEY = "table_pref_key";
    private TablePreference tablePref;
    private CoordinatorLayout rootView;
    private RecyclerView tableRoot; // TableLayout for TablePreferenceAdapter's
    private FloatingActionButton fab; // fragment fab
    private TreeMap<Float, Float> priceMap; // prices-to-values map - for discount amounts, etc.
    private TablePreferenceAdapter adapter;

    public TablePreferenceLayout() {
        // public constructor required
    }

    public static TablePreferenceLayout newInstance(String key) {
        TablePreferenceLayout fragment = new TablePreferenceLayout();
        Bundle args = new Bundle();
        args.putString(KEY, key);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onBindDialogView(View rootView) {
//        super.onBindDialogView(rootView);
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
//        // get list of prices and values for them
//        if (getPreference() instanceof TablePreference) {
//            TablePreference pref = (TablePreference) getPreference();
//            priceMap = pref.getJson();
//        }
//        renderTable();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // set layout
        rootView = (CoordinatorLayout) inflater.inflate(R.layout.pref_table, container, false);
        //find table layout
        tableRoot = (RecyclerView) rootView.findViewById(R.id.prices_rv);
        // find FAB
        fab = (FloatingActionButton) rootView.findViewById(R.id.table_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFABClick();
            }
        });
        // get list of prices and values for them
//        if (getPreference() instanceof TablePreference) {
//            TablePreference pref = (TablePreference) getPreference();
//            priceMap = pref.getJson();
//        }
        renderTable();
        return rootView;
    }

    protected void renderTable() {
       adapter = new TablePreferenceAdapter(getActivity(), priceMap);
       tableRoot.setAdapter(adapter);
       tableRoot.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    protected void onFABClick() {
        priceMap.put(new Float(0.0), null);
        // find the position of new item in TreeMap for notify the adapter
        int p = 0;
        for (; !priceMap.keySet().toArray()[p].equals(new Float(0.0)); p++);
        adapter.notifyItemInserted(p);
        tableRoot.scrollToPosition(p);
    }

//    // save value on dialog closed
//    @Override
//    public void onDialogClosed(boolean positiveResult) {
//        if (getPreference() instanceof TablePreference) {
//            TablePreference tp = (TablePreference) getPreference();
//            if (tp.callChangeListener(priceMap)) {
//                tp.setJson(priceMap);
//            }
//        }
//    }

    // save value on dialog closed
    @Override
    public void onPause() {
        super.onPause();
//        if (getPreference() instanceof TablePreference) {
//            TablePreference tp = (TablePreference) getPreference();
//            if (tp.callChangeListener(priceMap)) {
//                tp.setJson(priceMap);
//            }
//        }
    }

//    public TablePreference getPreference() {
//        if (tablePref == null) {
//            final String key = getArguments().getString(KEY);
//            final TablePreference.TargetFragment fragment =
//                    (TablePreference.TargetFragment) getTargetFragment();
//            tablePref = (TablePreference) fragment.findPreference(key);
//        }
//        return tablePref;
//    }
}
