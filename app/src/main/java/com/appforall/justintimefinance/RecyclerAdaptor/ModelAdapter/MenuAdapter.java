//package com.appforall.justintimefinance.RecyclerAdaptor.ModelAdapter;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.appforall.justintimefinance.RecyclerAdaptor.Model.User;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuAdapter { //extends RecyclerView.Adapter<RecyclerView.ViewHolder>
//
//    List<User> calculations = new ArrayList<User>();
//    public MenuAdapter(List<User> calculations)
//    {
//        super();
//        this.calculations = calculations;
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder
//    {
//        public TextView name;
//	    public TextView marks;
//	    public TextView course;
//	    public TextView credit;
//        public TextView percent;
//        public TextView id;
//
//        public ViewHolder(@NonNull View marksView) {
//            super(marksView);
//            id = (TextView) marksView.findViewById(R.id.idval);
//            name = (TextView) marksView.findViewById(R.id.name);
//            course = (TextView) marksView.findViewById(R.id.course);
//            marks = (TextView) marksView.findViewById(R.id.marks);
//            credit = (TextView) marksView.findViewById(R.id.credit);
//            percent = (TextView) marksView.findViewById(R.id.percent);
//        }
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View marksView = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_marks_recycle, parent, false);
//        ViewHolder viewholder = new ViewHolder(marksView);
//        return viewholder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
//        User mark = calculations.get(position);
//        if(mark != null) {
//            ((ViewHolder) viewholder).id.setText(mark.getId());
//            ((ViewHolder) viewholder).name.setText(mark.getName());
//            ((ViewHolder) viewholder).course.setText(mark.getCourse());
//            ((ViewHolder) viewholder).marks.setText(mark.getMarks());
//            ((ViewHolder) viewholder).credit.setText(mark.getCredit());
//            ((ViewHolder) viewholder).percent.setText(mark.getPercentage());
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return calculations.size();
//    }
//}
