package com.appforall.justintimefinance.MenuActions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appforall.justintimefinance.MainActivity;
import com.appforall.justintimefinance.MenuActions.FundTransfer;
import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.FundTransfers;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.User;
import com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter.CardRecycler;
import com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter.TransactionRecycler;
import com.appforall.justintimefinance.db.DatabaseHandler;

public class FundTransfer extends Fragment {
    DatabaseHandler db;
    String cardnumber, bank;
    TextView cardno;
    TextView bankname;
    TransactionRecycler transactionRecycler;
    Button submitbtn;
    EditText email;
    EditText amount;

    public FundTransfer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fund_transfer, container, false);
        if(getArguments() != null) {
            cardnumber = getArguments().getString("cardnumber");
            bank = getArguments().getString("bankname");

            Log.i("cardnumber Transactions fragment:", cardnumber);
        }


        cardno = (TextView) v.findViewById(R.id.cardnumber);
        bankname = (TextView) v.findViewById(R.id.bankname);

        email = (EditText)  v.findViewById(R.id.emailentry);
        amount = (EditText) v.findViewById(R.id.amountentry);
        submitbtn = (Button) v.findViewById(R.id.submitbtn);

        cardno.setText(cardnumber);
        bankname.setText(bank);
        db = new DatabaseHandler(getActivity());

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString();
                String samount = amount.getText().toString();

                FundTransfers funds = new FundTransfers(amount.getText().toString(), email.getText().toString());

                if(semail.equals("") || samount.equals("")){
                    Toast.makeText(getContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                }
                else {

                        long insrt = db.SaveTransaction(funds); //inserting data into database
                        if(insrt != 0) {
                            Toast.makeText(getActivity(), "Fund Transfered Successfully!", Toast.LENGTH_LONG).show();
                            setFundTransfer();
                        } else {
                            Toast.makeText(getActivity(), "Fund transfer failed ", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            public void setFundTransfer()
            {
                email.setText("");
                amount.setText("");
            }
        });

        return v;
    }
}