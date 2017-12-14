package com.example.shivani.expandablenavigationdrawer;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DealsFragment extends Fragment {
    

    public DealsFragment() {
        // Required empty public constructor
    }

   
    public static DealsFragment newInstance() {
        DealsFragment fragment = new DealsFragment();
       
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deals, container, false);
       /* int i = getArguments().getInt(ARG_DEAL_NUMBER);
        String deals = getResources().getStringArray(R.array.deals_array)[i];

        int textId = getResources().getIdentifier();
        ((TextView) rootView.findViewById(R.id.textDeals)).setText(textId);
        getActivity().setTitle(deals);*/
        return rootView;
    }


   
}
