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

    public static ArrayList<Drink> drinks = new ArrayList<>();
    Profile profile = new Profile();

    BacCalculatorFragment bacCalculator = new BacCalculatorFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("BAC Calculator");

        // TODO Bac Calculator Fragment (BCF) is created and added to the RelativeLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, BacCalculatorFragment.newInstance("BAC Calculator", drinks), "BAC Calculator Fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void changeFragmentListener(String fragment) {

        // If Set Profile String
        if (fragment.equals("Set Profile"))
        {
            // Change Fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, SetProfileFragment.newInstance("Set Weight/Gender"), "Set Profile Fragment")
                    .addToBackStack(null)
                    .commit();
        }

        // If View Drinks String
        if (fragment.equals("View Drinks"))
        {
            if (drinks.size() > 0){
                // Change Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, ViewDrinksFragment.newInstance(drinks), "View Drinks Fragment")
                        .addToBackStack(null)
                        .commit();
            }
            // If no drinks in the ArrayList - Toast Message "You've had no drinks."
            else {
                Log.d("TEST", "onClick: No drinks");
                Toast.makeText(MainActivity.this, "You have no drinks", Toast.LENGTH_SHORT).show();
            }
        }

        // If Add Drinks String
        if (fragment.equals("Add Drink"))
        {
            // Change Fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, AddDrinkFragment.newInstance("Add Drink"), "Add Drinks Fragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void deleteDrink(Drink drink) {
        this.drinks.remove(drink);
    }

    @Override
    public void emptyList(ArrayList<Drink> drinks) {
        bacCalculator = (BacCalculatorFragment) getSupportFragmentManager().findFragmentByTag("BAC Calculator Fragment");
        bacCalculator.updateDrinksList(drinks);
    }

    @Override
    public void closeViewDrinks(ArrayList<Drink> drinks) {
        bacCalculator = (BacCalculatorFragment) getSupportFragmentManager().findFragmentByTag("BAC Calculator Fragment");
        bacCalculator.updateDrinksList(drinks);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendProfile(Profile profile) {
        this.profile = profile;
        bacCalculator = (BacCalculatorFragment) getSupportFragmentManager().findFragmentByTag("BAC Calculator Fragment");
        bacCalculator.updateProfile(this.profile);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendDrink(Drink drink) {
        drinks.add(drink);
        bacCalculator =(BacCalculatorFragment) getSupportFragmentManager().findFragmentByTag("BAC Calculator Fragment");
        bacCalculator.updateDrinksList(drinks);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancel() {
        getSupportFragmentManager().popBackStack();
    }
}