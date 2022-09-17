/**
 * Homework 3
 * Group9_HW3
 * Phi Ha
 * Srinath Dittakavi
 */

package com.example.group9_hw3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group9_hw3.databinding.FragmentBacCalculatorBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BacCalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BacCalculatorFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_TITLE = "title";

    private String title;
    private Profile profile = new Profile();
    private ArrayList<Drink> drinks = new ArrayList<>();

    public BacCalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title The title of the Fragment.
     * @return A new instance of fragment BacCalculatorFragment.
     */
    public static BacCalculatorFragment newInstance(String title) {
        BacCalculatorFragment fragment = new BacCalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM_TITLE);
        }
    }

    FragmentBacCalculatorBinding binding;
    TextView weightDisplay;
    TextView numDrinkDisplay;
    TextView status;
    TextView bacLevel;
    public static double bac = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBacCalculatorBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    // Function codes go here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(title);

        weightDisplay = binding.weightDisplay;
        numDrinkDisplay = binding.numDrinkDisplay;
        bacLevel = binding.bacLevel;
        String weightGender;

        if (profile.weight == 0 && profile.gender.equals("")){
            weightDisplay.setText(getResources().getString(R.string.weight_display));
        }
        else {
            weightGender = profile.weight + " lbs (" + profile.gender + ")";
            weightDisplay.setText(weightGender);
        }

        weightDisplay.setText("N/A");

        numDrinkDisplay.setText("0");

        if (bac == 0.0){
            bacLevel.setText(getResources().getString(R.string.BAC_num));
        } else {
            bacLevel.setText(String.valueOf(bac));
        }

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragmentListener("Set Profile");
            }
        });

        //
        binding.viewDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragmentListener("View Drinks");
            }
        });

        binding.addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragmentListener("Add Drink");
            }
        });

        binding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IListener){
            mListener = (IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    IListener mListener;

    /**
     * This method updates the profile object in this fragment from the Main Activity
     * @param profile The updated profile of the user
     */
    public void updateProfile(Profile profile) {
        this.profile = profile;
    }

    public interface IListener {
        void changeFragmentListener(String fragment);
    }

    /**
     * This method updates the drinks list from the Main Activity
     * @param drinks The updated drinks list
     */
    public void updateDrinksList(ArrayList<Drink> drinks){
        this.drinks = drinks;
    }

    /**
     * This method resets all stored values on the BAC Calculator
     */
    public void reset() {

        // Clear the User Profile
        profile = new Profile();

        // Set components' values back to default
        weightDisplay.setText(getResources().getText(R.string.weight_display));
        numDrinkDisplay.setText(getResources().getText(R.string.num_drinks));

        // Disable buttons
        binding.viewDrinksButton.setEnabled(false);
        binding.addDrinkButton.setEnabled(false);

        clearUI();
        Log.d("TEST", "onClick: clearUI successful");
        Log.d("TEST", "onClick: Drinks: " + drinks.size() + ", Profile weight: " +
                profile.weight + ", Profile gender: " + profile.gender);
    }

    /**
     * This method clears the UI when setting the profile
     */
    public void clearUI(){
        // Clear Drinks List
        drinks.clear();

        // Set status message to default via strings.xml
        status = binding.status;
        status.setText(getResources().getText(R.string.status));
        status.setBackgroundColor(getResources().getColor(R.color.green));

        // Set BAC Level to 0.000 via strings.xml
        bacLevel = binding.bacLevel;
        bacLevel.setText(getResources().getText(R.string.BAC_num));
        bac = 0;
    }

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

    /**
     * This method checks the BAC Level to determine the status message and updates the UI
     * @param bac double value used to determine the status
     */
    public void updateBacUI(double bac){

        TextView numDrinks = binding.numDrinkDisplay;
        TextView status = binding.status;

        // NUMBER OF DRINKS AND BAC LEVEL
        numDrinks.setText(String.valueOf(drinks.size()));
        bacLevel.setText(String.valueOf(String.format("%.3f", bac)));

        // STATUS MESSAGE

        // If the bac drops to 0.08 or lower, status is set to green and text: "You're safe."
        if (0 <= bac && bac <= 0.08) {
            status.setText(getResources().getText(R.string.status));
            status.setBackgroundColor(getResources().getColor(R.color.green));
            binding.addDrinkButton.setEnabled(true);
            Log.d("TEST", "updateBacUI: Green successful");
        }
        // Sets the status to "Be careful." and changes the color to orange
        else if (0.08 < bac && bac <= 0.2){
            status.setText(getResources().getText(R.string.status2));
            status.setBackgroundColor(getResources().getColor(R.color.orange));
            binding.addDrinkButton.setEnabled(true);
            Log.d("TEST", "updateBacUI: Orange successful");
        }

        // Sets the status to "Over the limit!" and changes the color to red
        else {
            status.setText(getResources().getText(R.string.status3));
            status.setBackgroundColor(getResources().getColor(R.color.red));

            // Once the BAC reaches over 0.25, display Toast Message "No more drinks for you
            // This will disable the "Add Drink" button
            if (bac >= 0.25) {
                Toast.makeText(getActivity(), "No more drinks for you.", Toast.LENGTH_LONG).show();
                binding.addDrinkButton.setEnabled(false);
                Log.d("TEST", "updateBacUI: Red successful");
            }
            else {
                binding.addDrinkButton.setEnabled(true);
            }
        }
    }

}