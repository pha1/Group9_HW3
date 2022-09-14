package com.example.group9_hw3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.group9_hw3.databinding.FragmentSetProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetProfileFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_PROFILE = "profile";

    private Profile profile = new Profile();
    private String gender;

    public SetProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param profile Parameter 1.
     * @return A new instance of fragment SetProfileFragment.
     */
    public static SetProfileFragment newInstance(Profile profile) {
        SetProfileFragment fragment = new SetProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_PROFILE, profile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profile = getArguments().getParcelable(ARG_PARAM_PROFILE);
        }
    }

    FragmentSetProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentSetProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    TextView editWeight;
    RadioGroup gender_group;

    // Function codes go here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // default gender selection
        gender = "Female";

        // TODO Re-type in the setOnCheckedChangedListener
        // Check to see which radio button is checked
        // and apply the gender to the display
        /*
        binding.gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == binding.radioFemale){
                    gender = "Female";
                }
                else if (checkedId == binding.radioMale){
                    gender = "Male";
                }
            }
        });
         */

        // On Click Listener for Set
        //binding.setWeight
        // TODO This goes inside the OnClick
        /*
        try {
                    // Check if the value can be parsed into an int
                    weight = Integer.parseInt(editText.getText().toString());

                    // If the number is less than 0, show the Toast message
                    if (weight < 0) {
                        throw new IllegalArgumentException();
                    }

                    Profile profile = new Profile(gender, weight);
                    Log.d("TEST", "onClick: Profile created");

                    // TODO Send profile to Main Activity

                } catch (Exception e)
                {
                    // Toast message when a positive number is not entered
                    Toast.makeText(SetProfileActivity.this, "Please enter a valid positive" +
                            " number", Toast.LENGTH_SHORT).show();
                }
         */

        // On Click Listener for Cancel
        //binding.cancelSetProfile
        // TODO interface that has a "Cancel Method"
        //  On Click pop stack method, create a String XML for SetProfile
        // mListener.changeFragmentListener(getResources.getString.());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BacCalculatorFragment.IListener){
            mListener = (SetProfileFragment.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    IListener mListener;

    public interface IListener{
        // For canceling the Set Profile fragment
        void changeFragmentListener(String fragment);
    }
}