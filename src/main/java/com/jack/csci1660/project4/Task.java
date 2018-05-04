package com.jack.csci1660.project4;

/**
 * Created by brobst.30 on 4/12/18.
 */
/*
Modify your code from Project 2 so that the class representing tasks implements
the appropriate interface allowing tasks to be sorted based first on
their priority then on their name.  If two tasks have different priorities,
the task with the greater priority appears before the other task.  If two
tasks have the same priority, then the task whose name would appear first
alphabetically appears before the other task.  If two tasks have the same
priority and their names are the same, then they are "equal" with regard to
the comparison.
 */
class Task implements Comparable<Task> {
    public String title;
    public String description;
    private int priority;

    Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    void setPriority(int priority) {
        this.priority = priority;
    }

    int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(Task other) {
        // If the priorities are different
        if (other.getPriority() < this.getPriority())
            return 1;   // this is ahead of other
        else if (other.getPriority() > this.getPriority())
            return -1;  // this is behind other

        // Otherwise, just return the title's compareTo
        return this.title.compareTo(other.title);
    }

}
