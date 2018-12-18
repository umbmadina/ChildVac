package com.example.madina.childvac.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.madina.childvac.R;

import java.util.ArrayList;
public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MenuRecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MenuRecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<Integer> imageUrls, OnItemClickListener onItemClickListener) {
        mNames = names;
        mImages = imageUrls;
        mContext = context;
        mItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_item, parent, false);

        return new ViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.onBind(position, mItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name;
        View mItemView;
        CardView menu_card;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            menu_card= itemView.findViewById(R.id.menu_item);
            mItemClickListener = onItemClickListener;
            mItemView = itemView;
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(getAdapterPosition());
        }

        public void onBind(final int position, final OnItemClickListener onItemClickListener) {
            Log.d(TAG, "onBind: called.");

            Glide.with(mContext)
                    .asBitmap()
                    .load(mImages.get(position))
                    .into(image);

            name.setText(mNames.get(position));
            mItemView.setOnClickListener(this);

//            menu_card.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
//                    //Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
