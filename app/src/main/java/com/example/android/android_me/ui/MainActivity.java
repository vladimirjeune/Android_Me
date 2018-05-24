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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

import static com.example.android.android_me.ui.BodyPartFragment.BODY;
import static com.example.android.android_me.ui.BodyPartFragment.BODY_TEXT;
import static com.example.android.android_me.ui.BodyPartFragment.HEAD;
import static com.example.android.android_me.ui.BodyPartFragment.HEAD_TEXT;
import static com.example.android.android_me.ui.BodyPartFragment.LEGS;
import static com.example.android.android_me.ui.BodyPartFragment.LEGS_TEXT;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{


    private static final String TAG = MainActivity.class.getSimpleName();

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legsIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();


        // TODO (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        int bodyPartIndex = position / 12;
        int specificBodyPartIndex = position - bodyPartIndex*12;

        switch (bodyPartIndex) {
            case HEAD:
                headIndex = specificBodyPartIndex;
                break;
            case BODY:
                bodyIndex = specificBodyPartIndex;
                break;
            case LEGS:
                legsIndex = specificBodyPartIndex;
                break;
            default:
                Log.d(TAG, "onImageSelected: Invalid Body Part Index");
                break;
        }

        // TODO (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle bodySpecs = new Bundle();
        bodySpecs.putInt(HEAD_TEXT, headIndex);
        bodySpecs.putInt(BODY_TEXT, bodyIndex);
        bodySpecs.putInt(LEGS_TEXT, legsIndex);

        final Intent makeBodyIntent = new Intent(this, AndroidMeActivity.class); // Explicit Intent
        makeBodyIntent.putExtras(bodySpecs);



        // TODO (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        // The way this is set up the Next Button does not seem to work unless an image is selected.
        Button nextButton = (Button) findViewById(R.id.button_next);
        nextButton.setOnClickListener(view -> {
            startActivity(makeBodyIntent);
        });

    }

}
