package com.pushkaranand.restapiretrofitjsonadvance.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.models.Todos;

import java.util.ArrayList;

/**
 * Created by Pushkar on 02-Jul-17.
 */

public class TodoAdapter
        extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {


    public static final String TAG = "PADAPTR";

    private Context context;
    private ArrayList<Todos> todos;

    public TodoAdapter(Context context, ArrayList<Todos> todos) {
        this.context = context;
        this.todos = todos;
    }

    public void updateTodos (ArrayList<Todos> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_todos, parent, false);

        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {

        Todos thisTodo = todos.get(position);

        holder.tvTask.setText(thisTodo.getTitle());

        if(thisTodo.getCompleted() == false){
            holder.cbTask.toggle();
        }

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{

        TextView tvTask;
        CheckBox cbTask;
        View thisView;


        public TodoViewHolder(View itemView) {
            super(itemView);
            thisView = itemView;

            tvTask = (TextView)itemView.findViewById(R.id.tvTask);
            cbTask = (CheckBox)itemView.findViewById(R.id.cbTask);

        }
    }
}
