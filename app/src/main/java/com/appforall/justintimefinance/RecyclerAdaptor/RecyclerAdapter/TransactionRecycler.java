package com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.appforall.justintimefinance.RecyclerAdaptor.Model.Menu;
//import com.appforall.justintimefinance.RecyclerAdaptor.ModelAdapter.*;
import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;
public class TransactionRecycler {
//private RecyclerView recycleList;
//    private List<MarksCalculator> calculations;
//    DatabaseHandler dbHandler;
//
//    public MarksRecycling(View v,  Activity activity, String where)
//    {
//        calculations =  new ArrayList<MarksCalculator>();
//        Initialize(v);  // Initialize the recycleList
//        AddListMarks(where, v); // to add products to the defined List
//        BindListData(activity); // store data to recycleList
//    }
//
//    private void Initialize(View v)
//    {
//        recycleList = v.findViewById(R.id.recyclelist); //add recycleList
//        dbHandler = new DatabaseHandler(v.getContext());
//    }
//
//    private void AddListMarks(String where, View v)
//    {
//        calculations = dbHandler.databaseToList(where); //get data from database
//        if(calculations.size() == 0)
//        {
//            Toast.makeText(v.getContext(), "Record Not Found", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    void BindListData(Activity activity)
//    {
//        RecyclerView.LayoutManager layoutmanager = new GridLayoutManager(activity, 1);//used Grid Layout
//        recycleList.setLayoutManager(layoutmanager); // bind the data to recycler view
//        MarksCalculatorAdapter marksAdapter = new MarksCalculatorAdapter(calculations);// add products to adapter
//        recycleList.setAdapter(marksAdapter);
//        marksAdapter.notifyDataSetChanged();
//    }
}