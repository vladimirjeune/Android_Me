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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

import static com.example.android.android_me.ui.AndroidMeActivity.BODY;
import static com.example.android.android_me.ui.AndroidMeActivity.BODY_TEXT;
import static com.example.android.android_me.ui.AndroidMeActivity.HEAD;
import static com.example.android.android_me.ui.AndroidMeActivity.HEAD_TEXT;
import static com.example.android.android_me.ui.AndroidMeActivity.LEGS;
import static com.example.android.android_me.ui.AndroidMeActivity.LEGS_TEXT;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    // TODO (3) Create a variable to track whether to display a two-pane or single-pane UI
        // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPanes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO (4) If you are making a two-pane display, add new BodyPartFragments to create an initial Android-Me image
        // Also, for the two-pane display, get rid of the "Next" button in the master list fragment

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPanes = true;

            // Rid yourself of the NEXT Button
            ((Button) findViewById(R.id.next_button)).setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if(savedInstanceState == null) {

                // Retrieve list index values that were sent through an intent; use them to display the desired Android-Me body part image
                // Add the fragment to its container using a FragmentManager and a Transaction

                setSpecificBodyFragment(HEAD);
                setSpecificBodyFragment(BODY);
                setSpecificBodyFragment(LEGS);

            }


        } else {
            mTwoPanes = false;
        }

        // Use setListindex(int index) to set the list index for all BodyPartFragments

    }


    /**
     * SETBODYFRAGMENTS - Will set body fragment for the inputted body type
     * @param bodyTypeParameter - One of 3 allowed body types.
     * AndroidMeActivity.HEAD
     * AndroidMeActivity.BODY
     * AndroidMeActivity.LEGS
     */
    private void setSpecificBodyFragment(int bodyTypeParameter) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Integer> bodyImageIds = null;
        String bodyTag = "";
        int bodyContainer = -1;

        switch (bodyTypeParameter) {
            case HEAD:
                bodyContainer = R.id.head_container;
                bodyImageIds = AndroidImageAssets.getHeads();
                bodyTag = HEAD_TEXT;
                break;
            case BODY:
                bodyContainer = R.id.body_container;
                bodyImageIds = AndroidImageAssets.getBodies();
                bodyTag = BODY_TEXT;
                break;
            case LEGS:
                bodyContainer = R.id.leg_container;
                bodyImageIds = AndroidImageAssets.getLegs();
                bodyTag = LEGS_TEXT;
                break;
            default:
                Log.d(TAG, "onCreate: There is no body type: " + bodyTypeParameter);
                break;
        }

        // Create a new head BodyPartFragment
        BodyPartFragment bodyFragment = new BodyPartFragment();

        // Set the list of image id's for the head fragment and set the position to the second image in the list
        bodyFragment.setImageIds(bodyImageIds);

        fragmentManager.beginTransaction()
                .add(bodyContainer, bodyFragment, bodyTag)
                .commit();

    }


    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // TODO (5) Handle the two-pane case and replace existing fragments right when a new image is selected from the master list
        // The two-pane case will not need a Bundle or Intent since a new activity will not be started;
        // This is all happening in this MainActivity and one fragment will be replaced at a time

        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position /12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        // Phone Branch
        if (!mTwoPanes) {

            // Set the currently displayed item for the correct body part fragment
        switch(bodyPartNumber) {
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;
        }

            // Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            // Attach the Bundle to an intent
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            // The "Next" button launches a new AndroidMeActivity
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        } else {  // Tablet Branch

            FragmentManager fragmentManager = getSupportFragmentManager();
            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            int bodyContainerId = 0;

            // Set the currently displayed item for the correct body part fragment
            switch(bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(headIndex);
                    bodyContainerId = R.id.head_container;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setListIndex(bodyIndex);
                    bodyContainerId = R.id.body_container;
                    break;
                case 2:
                    legIndex = listIndex;
                    bodyPartFragment.setImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setListIndex(legIndex);
                    bodyContainerId = R.id.leg_container;
                    break;
                default:
                    Log.d(TAG, "onImageSelected: Body part number does not exist:" + bodyPartNumber);
                    break;
            }

            fragmentManager.beginTransaction()
                    .replace(bodyContainerId, bodyPartFragment)  // TODO: This is WRONG.  Need SPECIFIC Container!!!!!!!!!!!!!
                    .commit();

        }

    }

}
