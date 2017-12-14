package com.example.shivani.expandablenavigationdrawer;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MyDashBoardFragment extends Fragment {
    
    public MyDashBoardFragment() {
        // Required empty public constructor
    }
    
    public static MyDashBoardFragment newInstance(String param1, String param2) {
        MyDashBoardFragment fragment = new MyDashBoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_dash_board, container, false);
    }

}
