package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class BodyPartFragment extends Fragment {

    public BodyPartFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Obtain RootView
        boolean attachToRoot = false;
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, attachToRoot);

        // Get reference to ImageView in RootView
        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_head);

        // Set image resource to display
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        return rootView;
    }
}
