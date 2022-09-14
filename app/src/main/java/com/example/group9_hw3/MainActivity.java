/**
 * Homework 3
 * Group9_HW3
 * Phi Ha
 * Srinath Dittakavi
 */

package com.example.group9_hw3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group9_hw3.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BacCalculatorFragment.IListener, AddDrinkFragment.IListener, ViewDrinksFragment.IListener, SetProfileFragment.IListener{

    ActivityMainBinding binding;

    public static double bac = 0;
    public static ArrayList<Drink> drinks = new ArrayList<>();
    final public static String DRINKS_KEY = "DRINKS_KEY";

    TextView weightDisplay;
    TextView numDrinkDisplay;
    TextView bacLevel;
    TextView status;

    Profile profile = new Profile();
    Drink drink = new Drink();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("BAC Calculator");

        // TODO Bac Calculator Fragment (BCF) is created and added to the RelativeLayout
        //getSupportFragmentManager().beginTransaction()

        // TODO Set Profile Fragment is created and replaces Bac Calculator Fragment (back stack)

        // TODO Add Drink Fragment by button (BCF -> Add Drink)

        // TODO View Drinks Fragment by button (BCF -> View Drinks)

        // TODO This needs to be a part of BCF
        weightDisplay = binding.weightDisplay;
        numDrinkDisplay = binding.numDrinkDisplay;

        // TODO This will move to SetProfileFragment.java
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // TODO This will be moved to View Drinks Fragment
        findViewById(R.id.viewDrinksButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drinks.size() > 0) {
                    // If there are drinks, send the ArrayList to ViewDrinksActivity
                    // Need to add the extras (ArrayList) to the Intent
                }
                // If no drinks in the ArrayList - Toast Message "You've had no drinks."
                else {
                    Log.d("TEST", "onClick: No drinks");
                    Toast.makeText(MainActivity.this, "You have no drinks", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TODO Move to Add Drink Fragment
        findViewById(R.id.addDrinkButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    // TODO This will be implemented by the Main Activity via an interface from BCF
    // Reset all content
    public void reset() {

        // Clear the User Profile
        profile = new Profile();

        // Set components' values back to default
        weightDisplay.setText(getResources().getText(R.string.weight_display));
        numDrinkDisplay.setText(getResources().getText(R.string.num_drinks));

        // Disable buttons
        findViewById(R.id.viewDrinksButton).setEnabled(false);
        findViewById(R.id.addDrinkButton).setEnabled(false);

        clearUI();
        Log.d("TEST", "onClick: clearUI successful");
        Log.d("TEST", "onClick: Drinks: " + drinks.size() + ", Profile weight: " +
                profile.weight + ", Profile gender: " + profile.gender);
    }

    // TODO Same as above
    /**
     * This method clears the UI when setting the profile
     */
    public void clearUI(){
        // Clear Drinks List
        drinks.clear();

        // Set status message to default via strings.xml
        status = findViewById(R.id.status);
        status.setText(getResources().getText(R.string.status));
        status.setBackgroundColor(getResources().getColor(R.color.green));

        // Set BAC Level to 0.000 via strings.xml
        bacLevel = findViewById(R.id.bacLevel);
        bacLevel.setText(getResources().getText(R.string.BAC_num));
        bac = 0;
    }

    // TODO Same as above
    /**
     * This method calculates the BAC Level with the given formula
     * % BAC = A * 5.14 / Weight * r
     * @param profile the profile of the user
     * @param drinks the list of drinks
     * @return double value of the % BAC
     */
    public double calculateBAC(Profile profile, ArrayList<Drink> drinks){
        double bac;
        double r;
        double a = 0;

        // Set the r value for female
        if (profile.gender.equals("Female")){
            r = .66;
        }
        // Set the r value for male
        else {
            r = .73;
        }

        for (Drink drink : drinks){
            a += drink.size * drink.alcohol_percentage;
        }

        // BAC Formula
        bac = (a * 5.14) / (profile.weight * r);

        return bac;
    }

    // TODO Same as above
    /**
     * This method checks the BAC Level to determine the status message and updates the UI
     * @param bac double value used to determine the status
     */
    public void updateBacUI(double bac){

        TextView numDrinks = findViewById(R.id.numDrinkDisplay);
        TextView status = findViewById(R.id.status);

        // NUMBER OF DRINKS AND BAC LEVEL
        numDrinks.setText(String.valueOf(drinks.size()));
        bacLevel.setText(String.valueOf(String.format("%.3f", bac)));

        // STATUS MESSAGE

        // If the bac drops to 0.08 or lower, status is set to green and text: "You're safe."
        if (0 <= bac && bac <= 0.08) {
            status.setText(getResources().getText(R.string.status));
            status.setBackgroundColor(getResources().getColor(R.color.green));
            findViewById(R.id.addDrinkButton).setEnabled(true);
            Log.d("TEST", "updateBacUI: Green successful");
        }
        // Sets the status to "Be careful." and changes the color to orange
        else if (0.08 < bac && bac <= 0.2){
            status.setText(getResources().getText(R.string.status2));
            status.setBackgroundColor(getResources().getColor(R.color.orange));
            findViewById(R.id.addDrinkButton).setEnabled(true);
            Log.d("TEST", "updateBacUI: Orange successful");
        }

        // Sets the status to "Over the limit!" and changes the color to red
        else {
            status.setText(getResources().getText(R.string.status3));
            status.setBackgroundColor(getResources().getColor(R.color.red));

            // Once the BAC reaches over 0.25, display Toast Message "No more drinks for you
            // This will disable the "Add Drink" button
            if (bac >= 0.25) {
                Toast.makeText(this, "No more drinks for you.", Toast.LENGTH_LONG).show();
                findViewById(R.id.addDrinkButton).setEnabled(false);
                Log.d("TEST", "updateBacUI: Red successful");
            }
            else {
                findViewById(R.id.addDrinkButton).setEnabled(true);
            }
        }
    }

    @Override
    public void changeFragmentListener(String fragment) {
        // Similar to InClass04 where we obtain the String name of the fragment
        // and replace the current one
        // Call any methods necessary when creating
    }
}