package com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter;
        import android.app.Activity;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import com.appforall.justintimefinance.RecyclerAdaptor.Model.Transaction;
        import com.appforall.justintimefinance.RecyclerAdaptor.ModelAdapter.*;
        import com.appforall.justintimefinance.R;
        import com.appforall.justintimefinance.db.DatabaseHandler;
        import java.util.ArrayList;
        import java.util.List;

public class TransactionRecycler {
    private RecyclerView trasactionrecycler;

    private List<Transaction> transactionslist;
    DatabaseHandler dbHandler;

    public TransactionRecycler(View v, Activity activity)
    {
        Log.i("in constructor", "start");
        transactionslist =  new ArrayList<Transaction>();
        Log.i("in constructor", "Initialize");
        Initialize(v);  // Initialize the recycleList
        Log.i("in constructor", "AddCards");
        GetTransactionDetails(v); // to add products to the defined List
        Log.i("in constructor", "start");
        BindListData(activity); // store data to recycleList
        Log.i("in constructor", "BindListData");
    }

    private void Initialize(View v)
    {
        trasactionrecycler = v.findViewById(R.id.trasactionrecycler); //add recycleList
        if(trasactionrecycler == null)
            Log.i("Intialize","trasactionrecycler is empty");
        dbHandler = new DatabaseHandler(v.getContext());
        if(dbHandler == null)
            Log.i("Intialize","dbHandler is empty");
    }

    private void GetTransactionDetails(View v)
    {
        transactionslist = dbHandler.GetTransactions(); //get data from database
        Log.i("transactionslist size=","transactionslistsize=" + transactionslist.size());
        if(transactionslist.size() == 0)
        {
            Toast.makeText(v.getContext(), "Record Not Found", Toast.LENGTH_LONG).show();
        }
    }

    void BindListData(Activity activity)
    {
        TransactionAdapter transactionAdapter;
        if(transactionslist.size() != 0) {
            RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);//used Grid Layout
            if(layoutmanager == null)
                Log.i("BindListData","layoutmanager is empty");
            trasactionrecycler.setLayoutManager(layoutmanager); // bind the data to recycler view
            transactionAdapter = new TransactionAdapter(transactionslist);// add products to adapter
            if(transactionAdapter == null)
                Log.i("BindListData","cardAdapter is empty");
            trasactionrecycler.setAdapter(transactionAdapter);

            transactionAdapter.notifyDataSetChanged();
        }
    }
}