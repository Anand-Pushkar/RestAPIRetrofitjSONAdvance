package com.pushkaranand.restapiretrofitjsonadvance.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pushkaranand.restapiretrofitjsonadvance.Activities.PhotoActivity;
import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnItemCLickListener;
import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnPhotoClickListener;
import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pushkar on 03-Jul-17.
 */

public class PhotoAdapter
        extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private  static final String TAG = "PhotoAdapter";


    private Context context;
    private ArrayList<Photo> photos;

    private OnPhotoClickListener onPhotoClickListener;

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {

        this.onPhotoClickListener = onPhotoClickListener;
    }

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        Log.d(TAG, "PhotoAdapter: Constructor of photoAdapter");
        this.context = context;
        this.photos = photos;
    }

    public void updatePhoto(ArrayList<Photo> photos) {
        this.photos=photos;
        notifyDataSetChanged();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: OnCreateView");

        LayoutInflater l = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = l.inflate(R.layout.list_item_photos, parent, false);

        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: OnBindView");

        final Photo thisPhoto = photos.get(position);

        holder.tvPhotoTitle.setText(thisPhoto.getTitle());
        Picasso.with(context).load(thisPhoto.getThumbnailUrl()).into(holder.ivThumbnail);

        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: OnClicklistener");

                onPhotoClickListener.onPhotoClick(thisPhoto.getId(), thisPhoto.getUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ii");
        return photos.size();
    }

    public  class PhotoViewHolder extends RecyclerView.ViewHolder {

        TextView tvPhotoTitle;
        ImageView ivThumbnail;
        View thisView;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            Log.d(TAG, "PhotoViewHolder: constructor of PhotoViewHolder");

            thisView = itemView;
            tvPhotoTitle = (TextView)itemView.findViewById(R.id.tvPhotoTitle);
            ivThumbnail = (ImageView)itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
