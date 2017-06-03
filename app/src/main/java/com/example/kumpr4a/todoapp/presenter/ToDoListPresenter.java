package com.example.kumpr4a.todoapp.presenter;

import android.content.Context;

import com.example.kumpr4a.todoapp.net.request.AppRequester;
import com.example.kumpr4a.todoapp.net.response.ToDoResponse;
import com.example.kumpr4a.todoapp.ui.ToDoAdapter;

/**
 * Created by kumpr4a on 6/2/2017.
 */

public class ToDoListPresenter {

    private final String URL = "https://jsonplaceholder.typicode.com/todos?userId=1";

    public void getTaskList(Context context) {
        new AppRequester().requestData(context, URL);
    }

    public void addNewTask(String task, ToDoAdapter adapter) {
        ToDoResponse newTask = buildNewTask(task);
        adapter.resetData(newTask);
    }

    private ToDoResponse buildNewTask(String task) {
        ToDoResponse toDoResponse = new ToDoResponse();
        toDoResponse.setCompleted(false);
        toDoResponse.setTitle(task);
        return toDoResponse;
    }
}
