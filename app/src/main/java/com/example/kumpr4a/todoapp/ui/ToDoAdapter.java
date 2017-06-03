package com.example.kumpr4a.todoapp.ui;

import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumpr4a.todoapp.R;
import com.example.kumpr4a.todoapp.callback.OnCheckChangedCallback;
import com.example.kumpr4a.todoapp.net.response.ToDoResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by kumpr4a on 6/2/2017.
 */

public class ToDoAdapter extends RecyclerView.Adapter implements OnCheckChangedCallback {
    private ArrayList<ToDoResponse> toDoItems;

    public ToDoAdapter(ArrayList toDoItems) {
        this.toDoItems = toDoItems;
    }

    public void resetData(ToDoResponse item) {
        toDoItems.add(0, item);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task, parent,
                false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        ToDoResponse item = toDoItems.get(position);
        vh.name.setText(item.getTitle());
        vh.checkBox.setChecked(item.getCompleted());
        changeColor(vh.container, item.getCompleted());
    }

    private static void changeColor(View view, boolean isChecked) {
        if (isChecked) {
            view.setBackgroundColor(Color.parseColor("#D3F4D3"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }


    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    @Override
    public void onCheckChanged(boolean isChecked, int position) {
        toDoItems.get(position).setCompleted(isChecked);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.taskName)
        public TextView name;
        @BindView(R.id.checkbox)
        public AppCompatCheckBox checkBox;
        @BindView(R.id.container)
        public LinearLayout container;

        private OnCheckChangedCallback callBack;

        public ViewHolder(View itemView, OnCheckChangedCallback listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.callBack = listener;
        }

        @OnCheckedChanged(R.id.checkbox)
        public void onCheckChanged(boolean isChecked) {
            changeColor(container, isChecked);
            callBack.onCheckChanged(isChecked, getAdapterPosition());
        }
    }
}

