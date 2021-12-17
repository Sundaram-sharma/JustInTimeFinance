package com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.CardDetail;
import com.appforall.justintimefinance.RecyclerAdaptor.ModelAdapter.*;
import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.db.DatabaseHandler;
import java.util.ArrayList;
import java.util.List;

public class CardRecycler {
private RecyclerView recycleList;
    private List<CardDetail> cards;
    DatabaseHandler dbHandler;

    public CardRecycler(View v,  Activity activity)
    {
        Log.i("in constructor", "start");
        cards =  new ArrayList<CardDetail>();
        Log.i("in constructor", "Initialize");
        Initialize(v);  // Initialize the recycleList
        Log.i("in constructor", "AddCards");
        AddCards(v); // to add products to the defined List
        Log.i("in constructor", "start");
        BindListData(activity); // store data to recycleList
        Log.i("in constructor", "BindListData");
    }

    private void Initialize(View v)
    {
        recycleList = v.findViewById(R.id.recyclelist); //add recycleList
        if(recycleList == null)
            Log.i("Intialize","recyclerlist is empty");
        dbHandler = new DatabaseHandler(v.getContext());
        if(dbHandler == null)
            Log.i("Intialize","dbHandler is empty");
    }

    private void AddCards(View v)
    {
         cards = dbHandler.GetCards(); //get data from database
        Log.i("cards size=","cardssize=" + cards.size());
        if(cards.size() == 0)
        {
            Toast.makeText(v.getContext(), "Record Not Found", Toast.LENGTH_LONG).show();
        }
    }

    void BindListData(Activity activity)
    {
        CardAdapter cardAdapter;
        if(cards.size() != 0) {
            RecyclerView.LayoutManager layoutmanager = new GridLayoutManager(activity, 1);//used Grid Layout
            if(layoutmanager == null)
                Log.i("BindListData","layoutmanager is empty");
                recycleList.setLayoutManager(layoutmanager); // bind the data to recycler view
                cardAdapter = new CardAdapter(cards);// add products to adapter
            if(cardAdapter == null)
                Log.i("BindListData","cardAdapter is empty");
                recycleList.setAdapter(cardAdapter);

                cardAdapter.notifyDataSetChanged();
        }
    }
}