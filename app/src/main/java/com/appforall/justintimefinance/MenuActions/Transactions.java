package com.appforall.justintimefinance.MenuActions;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter.CardRecycler;
import com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter.TransactionRecycler;

import org.w3c.dom.Text;

public class Transactions extends Fragment {
    String cardnumber, bank;
    TextView cardno;
    TextView bankname;
    TransactionRecycler transactionRecycler;

    public Transactions() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_transactions, container, false);
        if(getArguments() != null) {
            cardnumber = getArguments().getString("cardnumber");
            bank = getArguments().getString("bankname");

            Log.i("cardnumber Transactions fragment:", cardnumber);
        }

        cardno = (TextView) v.findViewById(R.id.cardnumber);
        bankname = (TextView) v.findViewById(R.id.bankname);

        cardno.setText(cardnumber);
        bankname.setText(bank);

        Activity activity = getActivity();
        transactionRecycler = new TransactionRecycler(v, activity); //create recycler view to display data//

        return v;
    }
}