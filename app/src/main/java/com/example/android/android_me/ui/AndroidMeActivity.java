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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

import static com.example.android.android_me.ui.BodyPartFragment.BODY;
import static com.example.android.android_me.ui.BodyPartFragment.HEAD;
import static com.example.android.android_me.ui.BodyPartFragment.LEGS;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {


    private final String TAG = AndroidMeActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

//        // Create a new head BodyPartFragment
//        BodyPartFragment headFragment = new BodyPartFragment();
//
//        // TODO (4) Set the list of image id's for the head fragment and set the position to the second image in the list
//        headFragment.setBodyParts(AndroidImageAssets.getHeads());
//        headFragment.setPosition(1);
//
//        // Add the fragment to its container using a FragmentManager and a Transaction
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        fragmentManager.beginTransaction()
//                .add(R.id.head_container, headFragment)
//                .commit();

        displayBodyFragment(HEAD);
        // TODO (5) Create and display the body and leg BodyPartFragments
        displayBodyFragment(BODY);

        displayBodyFragment(LEGS);

    }


    /**
     * DISPLAYBODYFRAGMENT - Displays the body fragment indicated by the inputted integer.
     * HEAD => Display head
     * BODY => Display body
     * LEGS => Display legs
     */
    private void displayBodyFragment(int bodyType) {

        List<Integer> bodyList = null;
        int bodyContainerId = -1; // Just default

        switch (bodyType) {
            case HEAD:
                bodyList = AndroidImageAssets.getHeads();
                bodyContainerId = R.id.head_container;
                break;
            case BODY:
                bodyList = AndroidImageAssets.getBodies();
                bodyContainerId = R.id.body_container;
                break;

            case LEGS:
                bodyList = AndroidImageAssets.getLegs();
                bodyContainerId = R.id.leg_container;
                break;
            default:
                Log.e(TAG, "displayBodyFragment: Invalid body type entered"
                        , new IllegalArgumentException());
        }

        // Create a new BodyPartFragment
        BodyPartFragment bodyPartFragment = new BodyPartFragment();

        // TODO (4) Set the list of image id's for the head fragment and set the position to the second image in the list
        bodyPartFragment.setBodyParts(bodyList);
        bodyPartFragment.setPosition(1);  // 2nd image

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(bodyContainerId, bodyPartFragment)
                .commit();
    }
}
