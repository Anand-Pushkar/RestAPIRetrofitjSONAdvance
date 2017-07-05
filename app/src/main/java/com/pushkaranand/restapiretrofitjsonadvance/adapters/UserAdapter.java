package com.pushkaranand.restapiretrofitjsonadvance.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnItemCLickListener;
import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.models.User;

import java.util.ArrayList;

/**
 * Created by Pushkar on 01-Jul-17.
 */

public class UserAdapter
        extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public static final String TAG = "UA";

    private Context context;
    private ArrayList<User> users;


    private OnItemCLickListener onItemClickListener;

    public void setOnItemClickListener(OnItemCLickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }


    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    public void updateUsers(ArrayList<User> newUserList) {
        this.users = newUserList;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_users, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User thisUser = users.get(position);

        holder.tvUserUsername.setText(thisUser.getUsername());
        holder.tvUserName.setText(thisUser.getName());
        holder.tvUserPhone.setText(thisUser.getPhone());
        holder.tvUserEmail.setText(thisUser.getEmail());

        holder.btnShowPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClickPosts(thisUser.getId());
                    onItemClickListener.onItemClick(thisUser.getId(), view);

                }
            }
        });

        holder.btnShowTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClickTodos(thisUser.getId());
                    onItemClickListener.onItemClick(thisUser.getId(), view);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserUsername, tvUserName, tvUserPhone, tvUserEmail;
        Button btnShowPost, btnShowTodos;
        View thisView;

        public UserViewHolder(View itemView) {
            super(itemView);
            thisView = itemView;

            tvUserEmail = (TextView) itemView.findViewById(R.id.tvUserEmail);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvUserPhone = (TextView) itemView.findViewById(R.id.tvUserPhone);
            tvUserUsername = (TextView) itemView.findViewById(R.id.tvUserUsername);

            btnShowPost = (Button) itemView.findViewById(R.id.btn_showPosts);
            btnShowTodos = (Button) itemView.findViewById(R.id.btn_showTodos);

        }
    }
}
