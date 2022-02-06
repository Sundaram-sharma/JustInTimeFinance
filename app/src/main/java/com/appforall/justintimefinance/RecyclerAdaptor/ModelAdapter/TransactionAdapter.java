package com.appforall.justintimefinance.RecyclerAdaptor.ModelAdapter;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.SurfaceControl;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.appforall.justintimefinance.RecyclerAdaptor.Model.Transaction;
        import com.appforall.justintimefinance.R;
        import java.util.ArrayList;
        import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    List<Transaction> transactions = new ArrayList<Transaction>();

    public TransactionAdapter(List<Transaction> transactions)
    {
        super();
        this.transactions = transactions;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView amount;
        public TextView description;

        public ViewHolder(@NonNull View v) {
            super(v);
            amount = (TextView) v.findViewById(R.id.amount);
            description = (TextView) v.findViewById(R.id.description);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_transaction, parent, false);
        ViewHolder viewholder = new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
        Transaction transaction = transactions.get(position);
        if(transaction != null) {
            ((ViewHolder) viewholder).description.setText(transaction.getDescription());//bankname
            ((ViewHolder) viewholder).amount.setText(transaction.getAmount());//cardnumber
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}


