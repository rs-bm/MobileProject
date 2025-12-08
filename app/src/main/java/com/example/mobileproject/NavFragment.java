package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class NavFragment extends Fragment {

    Button weaponsB;
    Button statsB;
    Button warB;

    public NavFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav, container, false);
        weaponsB = view.findViewById(R.id.weaponsB);
        weaponsB.setOnClickListener(weaponsListener);
        statsB = view.findViewById(R.id.statsB);
        statsB.setOnClickListener(statsListener);
        warB = view.findViewById(R.id.warB);
        warB.setOnClickListener(warListener);
        if (getActivity().getClass() == MainActivity.class) {
            weaponsB.setBackgroundColor(getResources().getColor(R.color.white));
            statsB.setBackgroundColor(getResources().getColor(R.color.lightgrey));
            warB.setBackgroundColor(getResources().getColor(R.color.lightgrey));
        } else if (getActivity().getClass() == SecondActivity.class) {
            weaponsB.setBackgroundColor(getResources().getColor(R.color.lightgrey));
            statsB.setBackgroundColor(getResources().getColor(R.color.white));
            warB.setBackgroundColor(getResources().getColor(R.color.lightgrey));
        } else {
            weaponsB.setBackgroundColor(getResources().getColor(R.color.lightgrey));
            statsB.setBackgroundColor(getResources().getColor(R.color.lightgrey));
            warB.setBackgroundColor(getResources().getColor(R.color.white));

        }
        return view;
    }
    View.OnClickListener weaponsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getActivity().getClass() != MainActivity.class) {
                Intent intent;
                intent = new Intent(getActivity(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        }
    };
    View.OnClickListener statsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getActivity().getClass() != SecondActivity.class) {
                Intent intent;
                intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
            }
        }
    };
    View.OnClickListener warListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getActivity().getClass() != ThirdActivity.class) {
                Intent intent;
                intent = new Intent(getActivity(), ThirdActivity.class);
                startActivity(intent);
            }
        }
    };
}