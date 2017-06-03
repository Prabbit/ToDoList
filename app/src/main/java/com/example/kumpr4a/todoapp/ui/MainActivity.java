package com.example.kumpr4a.todoapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.kumpr4a.todoapp.R;
import com.example.kumpr4a.todoapp.net.response.ToDoResponse;
import com.example.kumpr4a.todoapp.presenter.ToDoListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.taskField)
    AppCompatEditText taskField;
    @BindView(R.id.taskList)
    RecyclerView recyclerView;
    private EventBus eventBus;
    private ToDoAdapter adapter;
    private ToDoListPresenter toDoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        eventBus = EventBus.getDefault();
        fetchData();
    }


    public void fetchData() {
        toDoListPresenter = new ToDoListPresenter();
        toDoListPresenter.getTaskList(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!eventBus.isRegistered(this))
            eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (eventBus.isRegistered(this))
            eventBus.unregister(this);
    }

    @Subscribe(sticky = true)
    public void onTaskListReceived(List<ToDoResponse> taskList) {
        eventBus.removeStickyEvent(taskList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpaceDividerItemDecorator());
        adapter = new ToDoAdapter((ArrayList) taskList);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.addButton)
    public void onAddClicked() {
        if (!TextUtils.isEmpty(taskField.getText())) {
            toDoListPresenter.addNewTask(taskField.getText().toString(), adapter);
            taskField.setText("");
        }
    }
}
