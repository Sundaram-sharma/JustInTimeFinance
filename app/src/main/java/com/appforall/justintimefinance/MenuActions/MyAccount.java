package com.appforall.justintimefinance.MenuActions;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter.CardRecycler;

public class MyAccount extends Fragment {

    public MyAccount() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CardRecycler cardRecycler;
        View v = inflater.inflate(R.layout.activity_my_account, container, false);
           Activity activity = getActivity();
          cardRecycler = new CardRecycler(v, activity); //create recycler view to display data//
        return v;
    }
}