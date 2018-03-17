package com.group.uni.sojoo2018.activities.telaInicial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.group.uni.sojoo2018.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class NotificacoesAdapter extends RecyclerView.Adapter<NotificacoesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Notificacao> notificacaoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;
        public ImageView overflow;
        TextView titleTextView;
        ImageView undoButton;
        @SuppressLint("ResourceAsColor")
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.dias);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.imagem);
            overflow = (ImageView) view.findViewById(R.id.action);
            //overflow.setColorFilter(R.color.hoje);
            titleTextView = (TextView) itemView.findViewById(R.id.dias);
           // titleTextView.setTextColor(R.color.laranja);
            undoButton = (ImageView) itemView.findViewById(R.id.action);
            //undoButton.setColorFilter(R.color.hoje);


        }
    }



    public NotificacoesAdapter(Context mContext, List<Notificacao> notificacaoList) {
        this.mContext = mContext;
        this.notificacaoList = notificacaoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notifi_menu_inicial, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Notificacao notificacao = notificacaoList.get(position);
        holder.title.setText(notificacao.getTitulo());
        holder.count.setText(notificacao.getDesc());



        switch (notificacao.getAcao()){
            case 1:
                holder.overflow.setImageResource(R.drawable.icons_ir);
                break;
            case 2:
                holder.overflow.setImageResource(R.drawable.icon_insta);
                break;
            case 3:
                holder.overflow.setImageResource(R.drawable.icon_amei);
                break;

        }


        // loading notificacao cover using Glide library
        Glide.with(mContext).load(notificacao.getImagem()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }



    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec

    //List<Notificacao> notificacaoList;
    List<Notificacao> itemsPendingRemoval;
    int lastInsertedIndex; // so we can add some more notificacaoList for testing purposes
    boolean undoOn; // is undo on, you can turn it on from the toolbar menu

    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Notificacao, Runnable> pendingRunnables = new HashMap<>(); // map of notificacaoList to pending runnables, so we can cancel a removal if need be

    /**
     *  Utility method to add some rows for testing purposes. You can add rows from the toolbar menu.
     */
    public void addItems(int howMany){
        if (howMany > 0) {
            for (int i = lastInsertedIndex + 1; i <= lastInsertedIndex + howMany; i++) {
                //notificacaoList.add("Item " + i);
                notifyItemInserted(notificacaoList.size() - 1);
            }
            lastInsertedIndex = lastInsertedIndex + howMany;
        }
    }

    public void setUndoOn(boolean undoOn) {
        this.undoOn = undoOn;
    }

    public boolean isUndoOn() {
        return undoOn;
    }

    public void pendingRemoval(int position) {
        final Notificacao item = notificacaoList.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(notificacaoList.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        Notificacao item = notificacaoList.get(position);
        //if (itemsPendingRemoval.contains(item)) {
//            itemsPendingRemoval.remove(item);
       // }
        if (notificacaoList.contains(item)) {
            notificacaoList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public boolean isPendingRemoval(int position) {
        Notificacao item = notificacaoList.get(position);
        return itemsPendingRemoval.contains(item);
    }

    /**
     * Click listener for popup menu notificacaoList
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                   // Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                   // Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return notificacaoList.size();
    }


}