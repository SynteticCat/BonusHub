package com.example.bonuslib.preferenceExtensions.TablePreference;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.TreeMap;
import java.util.Map;

public class TablePreferenceLayout extends Fragment {

    private static final String LAYOUT_ID_ARG = "layout_id";
    private static final String FAB_ID_ARG = "fab_id";
    private static final String PRICE_MAP_ARG = "price_map";
    private static final String TABLE_ROOT_ARG = "table_root";
    private View rootView; // activity
    private RecyclerView tableRoot; // TableLayout for TablePreferenceAdapter's
    private FloatingActionButton fab; // fragment fab
    private Map<Float, Float> priceMap; // prices-to-values map - for discount amounts, etc.
    private TablePreferenceAdapter adapter;

    public TablePreferenceLayout() {
        // public constructor required
    }

    public static TablePreferenceLayout newInstance(@IdRes int layoutId,
                                                    @IdRes int tableRootId,
                                                    @IdRes int fabId,
                                                    TreeMap<Float, Float> priceMap) {
        TablePreferenceLayout fragment = new TablePreferenceLayout();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID_ARG, layoutId);
        args.putInt(TABLE_ROOT_ARG, tableRootId);
        args.putInt(FAB_ID_ARG, fabId);
        args.putSerializable(PRICE_MAP_ARG, priceMap);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // set layout
        rootView = inflater.inflate(getArguments().getInt(LAYOUT_ID_ARG), container, false);
        //find table layout
        tableRoot = (RecyclerView) rootView.findViewById(getArguments().getInt(TABLE_ROOT_ARG));
        // find FAB
        fab = (FloatingActionButton) rootView.findViewById(getArguments().getInt(FAB_ID_ARG));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFABClick();
            }
        });
        // get list of prices and values for them
        priceMap = (Map<Float, Float>) getArguments().getSerializable(PRICE_MAP_ARG);
        renderTable();
        return rootView;
    }

    protected void renderTable() {
       adapter = new TablePreferenceAdapter(getActivity(), priceMap);
       tableRoot.setAdapter(adapter);
       tableRoot.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    protected void onFABClick() {
        priceMap.put(null, null);
        // find the position of new item in TreeMap for notify the adapter
        int p = 0;
        for (; priceMap.keySet().toArray()[p] != null; p++);
        adapter.notifyItemInserted(p);
    }

}
