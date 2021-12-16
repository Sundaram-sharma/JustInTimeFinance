package com.appforall.justintimefinance.ListAdapter;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter {
    ListView listui;
    String listuiArray[];

    public ListAdapter(View v, int elementid)
    {
        listui = (ListView) v.findViewById(elementid);
        Log.i("course count:", String.valueOf(listui.getCount()));
//        bindList(v, listitems);
    }

    public void bindList(View v, List<String> listitems) {
//        //courses list
//        List<String> courses = new ArrayList<String>();
//        courses.add("PROG 8480");
//        courses.add("PROG 8470");
//        courses.add("PROG 8460");
//        courses.add("PROG 8450");

        listuiArray = new String[listitems.size()];
        listitems.toArray(listuiArray); //main
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, listuiArray);
        if(adapter != null && listui != null)
                listui.setAdapter(adapter);
        else
            Log.i("error:","one of the adapter or listui is null");

//        listui.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                                  @Override
//                                                  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                                                     String course = (String) listui.getItemAtPosition(position);
//                                                      model.course = course;
//                                                      Log.i("inside CourseList:","course=" + course);
//                                                      courselistview.requestFocus();
//                                                      for (int pos = 0; pos < courselistview.getCount(); pos++) {
//                                                          if (courselistview.getChildAt(pos) != null)
//                                                              courselistview.getChildAt(pos).setBackgroundColor(Color.WHITE);
//                                                      }
//                                                      if (courselistview.getChildAt(position) != null)
//                                                          courselistview.getChildAt(position).setBackgroundColor(Color.GRAY);
//                                                  }
//                                              }
//        );
    }
}


