package com.appforall.justintimefinance.MenuActions;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.CardDetail;
import com.appforall.justintimefinance.db.DatabaseHandler;

public class RegisterCard extends Fragment {
    DatabaseHandler db;
    EditText bankid, cardnumber, expirydate, cvv;
    Button submitbutton;

    public RegisterCard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_register_card, container, false);
        bankid = v.findViewById(R.id.bankid);
        cardnumber = v.findViewById(R.id.cardnumber);
        expirydate = v.findViewById(R.id.expirydate);
        cvv = v.findViewById(R.id.cvv);
        submitbutton = (Button) v.findViewById(R.id.button);

        db = new DatabaseHandler(getContext());
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sbankid = bankid.getText().toString();
                String scardnumber = cardnumber.getText().toString();
                String sexpirydate = expirydate.getText().toString();
                String scvv = cvv.getText().toString();
                Reset();
                if (sbankid.equals("") || scardnumber.equals("") || sexpirydate.equals("") || scvv.equals("")) {
                    Toast.makeText(getContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                } else {
                    CardDetail cardDetail = new CardDetail(sbankid, scardnumber, sexpirydate, scvv);
                    long insrt = db.SaveCardDetails(cardDetail); //inserting data into database
                    if (insrt != 0) {
                        Toast.makeText(getContext(), "Card Registered Successfully!", Toast.LENGTH_LONG).show();
                        Reset();
                    } else {
                        Toast.makeText(getContext(), "Card Registration Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return v;
    }

    public void Reset()
    {
        bankid.setText("");
        cardnumber.setText("");
        expirydate.setText("");
        cvv.setText("");
    }
}