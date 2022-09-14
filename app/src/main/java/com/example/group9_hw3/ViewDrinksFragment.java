/**
 * Homework 3
 * Group9_HW3
 * Phi Ha
 * Srinath Dittakavi
 */

package com.example.group9_hw3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.group9_hw3.databinding.FragmentViewDrinkBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrinksFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_DRINKS = "drinks_list";

    private String drinks_list;

    public static int numDrinks = 0;
    final public static String VIEW_DRINKS_KEY = "VIEW_DRINKS";
    public static ArrayList<Drink> drinks = new ArrayList<Drink>();
    public int current = 0;
    public Drink drink = new Drink();

    TextView currentDrink;
    TextView totalDrinks;
    TextView drinkSize;
    TextView alcoholPercentage;
    TextView date;

    public ViewDrinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param drinks_list Parameter 1.
     * @return A new instance of fragment ViewDrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrinksFragment newInstance(ArrayList<Drink> drinks_list, String param2) {
        ViewDrinksFragment fragment = new ViewDrinksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM_DRINKS, drinks_list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinks_list = getArguments().getString(ARG_PARAM_DRINKS);
        }
    }

    FragmentViewDrinkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentViewDrinkBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    // Function codes go here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        // Click next to get the next drink in the ArrayList
        // If the current drink is the last drink then show the first drink next
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check to see if current drink is the last drink in the list
                // if it is move to the first drink
                if (drinks.get(current).equals(drinks.get(drinks.size()-1))){
                    current = 0;
                    drink = drinks.get(0);
                }
                // if not, next drink
                else {
                    current++;
                    drink = drinks.get(current);
                }
                updateUI();
            }
        });

        // TODO Remove the current drink from this drink list
        // TODO Send the drink back to the Main Activity and delete it there too
        // TODO Do not change fragments while doing this
        // Click the trash icon to delete the current drink and then show the previous drink
        binding.trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the current drink
                drinks.remove(current);
                Log.d("TEST", "onClick: Number of drinks: " + drinks.size());

                // If there are drinks in the list
                if (drinks.size() > 0) {
                    if (drinks.size() ==  1) {
                        current = 0;
                    } else if (drinks.size() > 1) {
                        if (current == 0) {
                            current = drinks.size() - 1;
                        } else {
                            current--;
                        }
                    }
                    drink = drinks.get(current);
                    updateUI();
                }
                // If there are no drinks in the list
                else {
                    // TODO Send the data back to MainActivity
                }

            }
        });

        // Click the previous button to get the previous drink in the ArrayList
        // If the current drink is the first drink, show the last drink
        binding.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If this is the first drink
                // Display the last drink in the list
                if(drinks.get(current).equals(drinks.get(0))){
                    current = drinks.size() - 1;
                    drink = drinks.get(drinks.size()-1);
                }
                // Show the previous drink
                else{
                    current--;
                    drink = drinks.get(current);
                }
                updateUI();
            }
        });

        // The close button finishes the activity without returning any extras
        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Make the Title of each Action Bar a String XML Value!
                mListener.changeFragmentListener(getResources.getString.(R.string.);
                // TODO Send updated ArrayList<Drink> drinks back to MainActivity
                // TODO The Main Activity does this - Implement with interface
            }
        });

         */
    }

    /*
    public void updateUI(){

        currentDrink = binding.textviewCurrentDrink;
        currentDrink.setText(String.valueOf(current + 1));

        totalDrinks = binding.textViewTotalDrinks;
        totalDrinks.setText(String.valueOf(drinks.size()));

        drinkSize = binding.textviewDrinkSize;
        drinkSize.setText(String.valueOf(drink.size));

        alcoholPercentage = binding.textViewPercentage;
        alcoholPercentage.setText(String.valueOf(drink.alcohol_percentage));

        date = binding.textViewDateTime;
        date.setText(drink.date);
    }
     */

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BacCalculatorFragment.IListener){
            mListener = (ViewDrinksFragment.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    IListener mListener;

    public interface IListener{
        // For closing the View Drinks fragment
        void changeFragmentListener(String fragment);
    }
}