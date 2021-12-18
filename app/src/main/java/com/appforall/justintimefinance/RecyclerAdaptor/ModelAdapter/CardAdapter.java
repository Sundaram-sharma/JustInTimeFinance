package com.appforall.justintimefinance.RecyclerAdaptor.ModelAdapter;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appforall.justintimefinance.RecyclerAdaptor.Model.CardDetail;
import com.appforall.justintimefinance.R;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    List<CardDetail> cards = new ArrayList<CardDetail>();
    TextView hiddencard;
    public CardAdapter(List<CardDetail> cards, TextView hiddencard)
    {
        super();
        this.hiddencard = hiddencard;
        this.cards = cards;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView bankname;
	    public TextView banktype;
	    public ImageView chip;
	    public TextView cardnumber;
	    public CardView bankcard;

        public ViewHolder(@NonNull View v) {
            super(v);
            bankname = (TextView) v.findViewById(R.id.bankname);
            banktype = (TextView) v.findViewById(R.id.banktype);
            chip = (ImageView) v.findViewById(R.id.chip);
            cardnumber  = (TextView) v.findViewById(R.id.cardnumber);
            bankcard = (CardView) v.findViewById(R.id.bankcard);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card, parent, false);
        ViewHolder viewholder = new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
        CardDetail card = cards.get(position);
        if(card != null) {
            ((ViewHolder) viewholder).bankname.setText(card.getBankname());//bankname
            ((ViewHolder) viewholder).cardnumber.setText(card.getCardnumber());//cardnumber
            ((ViewHolder) viewholder).chip.setImageResource(R.drawable.chip);
            ((ViewHolder) viewholder).banktype.setText("Checking");
            ((ViewHolder) viewholder).bankcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView cardnum = ((ViewHolder) viewholder).bankcard.findViewById(R.id.cardnumber);
                    hiddencard.setText(cardnum.getText());
                    ((ViewHolder) viewholder).bankcard.setCardBackgroundColor(Color.GRAY);
                }
        });
    }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}

