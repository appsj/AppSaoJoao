package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.detalhes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.RecyclerViewAdapterShow;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.Shows;

public class CardsDetalhesAdapter extends ArrayAdapter<Shows> {

    public CardsDetalhesAdapter(Context context) {
        super(context, 0);
    }
    private RecyclerViewAdapterShow.OnItemClickListener mItemClickListener;
    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.item_tourist_spot_card, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        Shows spot = getItem(position);

        holder.name.setText(spot.getTitle());
        holder.city.setText(spot.getMessage());
        holder.image.setImageResource(spot.getFoto());
        holder.ok.setColorFilter(getContext().getResources().getColor(R.color.white));
        holder.oktext.setTextColor(getContext().getResources().getColor(R.color.white));
        holder.oktext.setText("Eu vou!");

        return contentView;
    }



    private static class ViewHolder {

        public TextView name;
        public TextView city, oktext;
        public ImageView image;
        public ImageView ok;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.item_tourist_spot_card_name);
            this.city = (TextView) view.findViewById(R.id.item_tourist_spot_card_city);
            this.image = (ImageView) view.findViewById(R.id.item_tourist_spot_card_image);
            this.ok = (ImageView) view.findViewById(R.id.okbutton);
            this.oktext = (TextView) view.findViewById(R.id.oktext);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ok.setColorFilter(view.getContext().getResources().getColor(R.color.laranja));
                    oktext.setTextColor(view.getContext().getResources().getColor(R.color.laranja));
                    oktext.setText("Eu fui!");
                }
            });
            oktext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ok.setColorFilter(view.getContext().getResources().getColor(R.color.laranja));
                    oktext.setTextColor(view.getContext().getResources().getColor(R.color.laranja));
                    oktext.setText("Eu fui!");
                }
            });


        }


    }

}

