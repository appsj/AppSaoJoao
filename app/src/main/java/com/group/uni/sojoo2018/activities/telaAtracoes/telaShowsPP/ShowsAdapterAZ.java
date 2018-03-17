package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import com.group.uni.sojoo2018.R;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ShowsAdapterAZ extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {


    private List<Shows> modelList;

    private OnItemClickListener mItemClickListener;


    private Context context;

    public ShowsAdapterAZ(Context context, List<Shows> modelList) {
        this.modelList = modelList;
        this.context = context;

    }

    public void updateList(ArrayList<Shows> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public int getSectionCount() {
        return modelList.size();
    }

    @Override
    public int getItemCount(int section) {

        return modelList.get(section).getSingleItemArrayList().size();

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {

        String sectionName = modelList.get(section).getTitle();
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.txtHeader.setText(sectionName);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

        ArrayList<Shows> itemsInSection = modelList.get(section).getSingleItemArrayList();

        String itemTitle = itemsInSection.get(relativePosition).getTitle();
        String itemMessage = itemsInSection.get(relativePosition).getMessage();

        ViewHolder itemViewHolder = (ViewHolder) holder;

        itemViewHolder.itemTxtTitle.setText(itemTitle);
        itemViewHolder.itemTxtMessage.setText(itemMessage);
        itemViewHolder.imgUser.setImageResource(itemsInSection.get(relativePosition).getFoto());


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        if (header)

        {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_recycler_section, parent, false);
            return new SectionViewHolder(view);
        } else {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_show_az, parent, false);
            return new ViewHolder(view);
        }

    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Shows model);
    }

    // SectionViewHolder Class for Sections
    public static class SectionViewHolder extends RecyclerView.ViewHolder {


        final TextView txtHeader;

        public SectionViewHolder(View itemView) {
            super(itemView);

            txtHeader = (TextView) itemView.findViewById(R.id.txt_header);


        }
    }

    // ItemViewHolder Class for Items in each Section
    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView itemTxtTitle;

        final CircleImageView imgUser;

        final TextView itemTxtMessage;


        public ViewHolder(View itemView) {
            super(itemView);
            itemTxtTitle = (TextView) itemView.findViewById(R.id.item_txt_title);
            itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);

            imgUser = (CircleImageView) itemView.findViewById(R.id.img_user);


        }
    }


}


