package com.phjethva.rx.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phjethva.rx.R;
import com.phjethva.rx.data.model.WondersResponse;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WondersAdapter extends RecyclerView.Adapter<WondersAdapter.MyViewHolder> {

    private Context ctx;
    private List<WondersResponse.DataWonders> list;
    private WondersAdapterItemClick click;

    public interface WondersAdapterItemClick {
        void callbackWondersAdapterItemClick(int position);
    }

    public WondersAdapter(WondersAdapterItemClick click, List<WondersResponse.DataWonders> list) {
        this.click = click;
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView itemRxJava;
        ImageView ivBG;
        TextView tvName, tvCountry;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemRxJava = itemView.findViewById(R.id.item_wonder);
            ivBG = itemView.findViewById(R.id.iv_bg);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCountry = itemView.findViewById(R.id.tv_country);
        }

        public void bind(final int position, final WondersAdapterItemClick click) {
            itemRxJava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (click != null) {
                        click.callbackWondersAdapterItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        ctx = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_wonders, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        WondersResponse.DataWonders dataWonders = list.get(position);
        myViewHolder.tvName.setText(dataWonders.name);
        myViewHolder.tvCountry.setText(dataWonders.country);

        int width = ctx.getResources().getDisplayMetrics().widthPixels;
        int height = (int) (width / 1.78);
        Log.d("TAG_w&h", width + ", " + height);

        Picasso.get()
                .load(dataWonders.imageUrl)
                .resize(width, height)
                .centerCrop()
                .into(myViewHolder.ivBG);
        myViewHolder.bind(position, click);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void notifyData(List<WondersResponse.DataWonders> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
