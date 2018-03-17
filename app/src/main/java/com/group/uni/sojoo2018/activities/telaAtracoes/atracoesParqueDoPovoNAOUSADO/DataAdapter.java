package com.group.uni.sojoo2018.activities.telaAtracoes.atracoesParqueDoPovoNAOUSADO;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group.uni.sojoo2018.R;

import java.util.List;

/**
 * Created by fabii on 31/01/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<Data> data;
    public TextView  frase;

    private ItemClickListener itemClickListener;
    @Override
    public void onClick(View view) {

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView dia, diasemana, frase;
      //  public ImageView thumbnail;
       // public ImageView overflow;
      private ItemClickListener itemClickListener;
        public MyViewHolder(View view) {
            super(view);
            dia = (TextView) view.findViewById(R.id.dia);
            diasemana = (TextView) view.findViewById(R.id.dias);
            frase = (TextView) view.findViewById(R.id.textView4);

            view.setOnClickListener(this);
            //thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
           // overflow = (ImageView) view.findViewById(R.id.overflow);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition());
        }
    }


    public DataAdapter(Context mContext, List<Data> albumList) {
        this.mContext = mContext;
        this.data = albumList;
    }

    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data, parent, false);

        return new DataAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final DataAdapter.MyViewHolder holder, int position) {
        Data album = data.get(position);
        holder.dia.setText(album.getDiaS());
        holder.diasemana.setText(album.getDia());
holder.setItemClickListener(new ItemClickListener() {
    @Override
    public void onClick(View view, int position) {
        //Toast.makeText(mContext, "Eu cliquei em "+data.get(position).getDiaS(), Toast.LENGTH_SHORT).show();


    }
});
        // loading album cover using Glide library
        //Glide.with(mContext).load(album.getImagem()).into(holder.thumbnail);

        holder.dia.setOnClickListener(view -> mostrarProgr(position));




    }


    private void mostrarProgr(int position) {
 mContext.setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
//        frase.setText("Cliquei na "+data.get(position).getDiaS());
    }


    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new DataAdapter.MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                  //  Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                 //   Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
