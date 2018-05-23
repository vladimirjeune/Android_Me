package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    public MasterListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        boolean attachRoot = false;
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, attachRoot);
        GridView gridView = (GridView) rootView.findViewById(R.id.master_list_grid_view);

        gridView.setAdapter(new MasterListAdapter(getContext(), AndroidImageAssets.getAll()));

        return rootView;
    }
}
