package com.jack.csci1660.project4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by brobst.30 on 4/12/18.
 */
/*
Additionally, modify the code from Project 2 to include a custom class
representing a collection of tasks.  This class should implement the
appropriate interface so that a for-each loop can be used to iterate through
all the tasks.  The order in which the tasks are returned for the for-each loop
is up to you.
 */
public class TaskList implements Iterable<Task>, Iterator<Task> {
    private ArrayList<Task> taskList;

    private int count = 0;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void add(Task ntask) {
        taskList.add(ntask);
    }

    public void remove(Task dtask) {
        taskList.remove(dtask);
    }
    public Task remove(int ind) {
        return taskList.remove(ind);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int ind) {
        return taskList.get(ind);
    }

    @Override
    public Task next() {
        if (this.count == 0) {
            Collections.sort(taskList);
        }
        return this.taskList.get(this.count++);
    }

    @Override
    public boolean hasNext() {
        return this.count < this.taskList.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return this;
    }
}
