/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.List;

public class BodyPartFragment extends Fragment {

    public static final int HEAD = 0;
    public static final int BODY = 1;
    public static final int LEGS = 2;

    public final String TAG = BodyPartFragment.class.toString();


    // TODO (1) Create a setter method and class variable to set and store of a list of image resources
    public List<Integer> mBodyParts;

    // TODO (2) Create another setter method and variable to track and set the index of the list item to display
        // ex. index = 0 is the first image id in the given list , index 1 is the second, and so on
    public int mPosition;


    /**
     * SETBODYPARTS - Takes a list of integers representing body parts to display.
     * @param bodyParts - List<Integer>
     */
    public void setBodyParts(List<Integer> bodyParts) {
        mBodyParts = bodyParts;
    }


    /**
     * SETPOSITION - Takes an integer representing the position that should be shown in the list
     * of body parts.
     */
    public void setPosition(int position) {
        mPosition = position;
    }


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public BodyPartFragment() {
    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // Set the image to the first in our list of head images
//        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        // TODO (3) If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
        if (mBodyParts != null) {

            int bodyId = mBodyParts.get(mPosition);

            imageView.setImageResource(bodyId);

        } else {
            Log.i(TAG, "onCreateView: has a null list for parts.");
        }

        // Return the rootView
        return rootView;
    }

}
