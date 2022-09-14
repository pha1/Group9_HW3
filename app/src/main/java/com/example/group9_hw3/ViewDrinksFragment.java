package com.example.group9_hw3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private static final String ARG_PARAM2 = "param2";

    private String drinks_list;
    private String mParam2;

    public ViewDrinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param drinks_list Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrinksFragment newInstance(ArrayList<Drink> drinks_list, String param2) {
        ViewDrinksFragment fragment = new ViewDrinksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM_DRINKS, drinks_list);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinks_list = getArguments().getString(ARG_PARAM_DRINKS);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
    }
}