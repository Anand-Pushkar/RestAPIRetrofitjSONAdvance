package com.pushkaranand.restapiretrofitjsonadvance.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnItemCLickListener;
import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.models.Album;

import java.util.ArrayList;

/**
 * Created by Pushkar on 03-Jul-17.
 */

public class AlbumAdapter
        extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    public static final String TAG = "AlbumAdapter";

    private Context context;
    private ArrayList<Album> albums;

    private OnItemCLickListener onItemClickListener;

    public void setOnItemClickListener(OnItemCLickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        Log.d(TAG, "AlbumAdapter: constructor of album adapter");
        this.context = context;
        this.albums = albums;
    }

    public void updateAlbums(ArrayList<Album> albums){
        this.albums = albums;
        notifyDataSetChanged();
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater l = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = l.inflate(R.layout.list_item_album, parent, false);

        Log.d(TAG, "onCreateViewHolder: onCreateVIewHolder "+albums.size());

        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        final Album thisAlbum = albums.get(position);
        holder.tvAlbumTitle.setText(thisAlbum.getTitle());

        Log.d(TAG, "onBindViewHolder: onBindViewHolder111111111");

        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickListener.onItemClick(thisAlbum.getUserId(), view);
                Log.d(TAG, "onClick: View Clicked222222222222222");
            }
        });
    }

    @Override
    public int getItemCount() {

        return albums.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlbumTitle;
        View thisView;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            Log.d(TAG, "AlbumViewHolder: constructor of class AlbumViewHolder");

            this.thisView = itemView;
            tvAlbumTitle = (TextView)itemView.findViewById(R.id.tvAlbumTitle);
        }
    }
}
