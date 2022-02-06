package com.appforall.justintimefinance.MenuActions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ListAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.appforall.justintimefinance.R;
import com.appforall.justintimefinance.RecyclerAdaptor.RecyclerAdapter.CardRecycler;

import java.util.ArrayList;
import java.util.List;

public class MyAccount extends Fragment {
    TextView hiddencard;
    TextView hiddenbank;
    List<String> listitems = new ArrayList<String>();
    String[] listuiArray;
    ListView listviewui;

    public MyAccount() {
        listitems.add("Transactions");
        listitems.add("Fund Transfer");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CardRecycler cardRecycler;
        View v = inflater.inflate(R.layout.activity_my_account, container, false);
        Activity activity = getActivity();
        cardRecycler = new CardRecycler(v, activity); //create recycler view to display data//
        //////////populate the list//////////////
        listviewui = (ListView) v.findViewById(R.id.menuitems);
        hiddencard = (TextView) v.findViewById(R.id.hiddencard);
        hiddenbank = (TextView) v.findViewById(R.id.hiddenbank);
        Log.i("list menu items count:", String.valueOf(listviewui.getCount()));
        bindList(v, listitems);

        /////////////////////////////////////////


        listviewui.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                  String menuitem = (String) listviewui.getItemAtPosition(position);
                                                  Log.i("inside CourseList:", "menuitem=" + menuitem);
                                                  listviewui.requestFocus();
                                                  for (int pos = 0; pos < listviewui.getCount(); pos++) {
                                                      if (listviewui.getChildAt(pos) != null)
                                                          listviewui.getChildAt(pos).setBackgroundColor(Color.WHITE);
                                                  }
                                                  if (listviewui.getChildAt(position) != null)
                                                      listviewui.getChildAt(position).setBackgroundColor(Color.GRAY);

//                Intent intent=new Intent(getContext(), Transaction.class);
//                    intent.putExtra("cardnumber",hiddencard.getText().toString());
//                    v.getContext().startActivity(intent); //start the new activity
                                                  Fragment frag = null;
                                                  if(menuitem == "Transactions")
                                                  {
                                                       frag = new Transactions();
                                                  } else {
                                                       frag = new FundTransfer();
                                                  }
                                                  GoToFragment(frag, "hi");

                                              }

                                              private void GoToFragment(Fragment frag, String tag) {
                                                  Bundle bundle = new Bundle();
                                                  bundle.putString("cardnumber", hiddencard.getText().toString());
                                                  bundle.putString("bankname", hiddenbank.getText().toString());
                                                  if(frag != null && bundle != null) {
                                                      frag.setArguments(bundle);
                                                      FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                      if(ft != null) {
                                                          ft.replace(R.id.frame, frag, tag);
                                                          ft.addToBackStack(null);
                                                          ft.commit();
                                                      } else {
                                                          Log.i("ft GoTOfRAGMENT:", "FT IS NULL");
                                                      }
                                                  } else {
                                                      Log.i("GoToFragment", "either bundle to frag is null");
                                                  }
                                              }
                                          }
        );


        return v;
    }


    public void bindList(View v, List<String> listitems) {
        listuiArray = new String[listitems.size()];
        listitems.toArray(listuiArray); //main
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, listuiArray);
        if (adapter != null && listviewui != null)
            listviewui.setAdapter(adapter);
        else
            Log.i("error:", "one of the adapter or listviewui is null");
    }

}