package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.group.uni.sojoo2018.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<ModeloCalendario> modelList;

    private OnItemClickListener mItemClickListener;


    public RecyclerViewAdapter(Context context, ArrayList<ModeloCalendario> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<ModeloCalendario> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calendario, viewGroup, false);

        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final ModeloCalendario model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTxtTitle.setText(model.getTitle());
            genericViewHolder.itemTxtMessage.setText(model.getMessage());

            switch (model.getTipo()){
                case 1:
                    genericViewHolder.imgUser.setImageResource(R.drawable.frame);
                    genericViewHolder.itemTxtTitle.setBackgroundColor(mContext.getColor(R.color.dia_normaldark));
                    genericViewHolder.itemTxtMessage.setBackgroundColor(mContext.getColor(R.color.dia_normal));
                    break;
                case 2:
                    genericViewHolder.imgUser.setImageResource(R.drawable.frame_fds);
                    genericViewHolder.itemTxtTitle.setBackgroundColor(mContext.getColor(R.color.fdsdark));
                    genericViewHolder.itemTxtMessage.setBackgroundColor(mContext.getColor(R.color.fds));
                    break;
                case 3:
                    genericViewHolder.imgUser.setImageResource(R.drawable.frame_hoje);
                    genericViewHolder.itemTxtTitle.setBackgroundColor(mContext.getColor(R.color.hojedark));
                    genericViewHolder.itemTxtMessage.setBackgroundColor(mContext.getColor(R.color.hoje));
                    break;
            }

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ModeloCalendario getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, ModeloCalendario model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SparseBooleanArray selectedItems = new SparseBooleanArray();
        private ImageView imgUser;
        private TextView itemTxtTitle;
        private TextView itemTxtMessage;



        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
            this.itemTxtTitle = (TextView) itemView.findViewById(R.id.item_txt_title);
            this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));



                 // TextView aaaa = view.findViewById(R.id.item_txt_message);
                   // aaaa.setText("sausjau!");



                }
            });

        }


        @Override
        public void onClick(View view) {
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                view.setSelected(false);
                CardView cardview = view.findViewById(R.id.cardview_calendar);
                cardview.setCardElevation(20);
                cardview.setElevation(20);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                view.setSelected(true);
            }
        }
    }

}

