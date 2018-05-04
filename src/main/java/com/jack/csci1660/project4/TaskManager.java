package com.jack.csci1660.project4;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by brobst.30 on 4/12/18.
 */

public class TaskManager {
    private Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();

    private String filename;

    private interface ActionInterface {
        String getName();
        void runAction();
    }

    private void actionAddTask() {
        System.out.print("Enter a title for this task:\n > ");
        String title = scanner.nextLine();

        System.out.print("Enter a description for this task:\n > ");
        String description = scanner.nextLine();

        int priority = 0;
        do {
            System.out.print("Enter a priority for this task (1-5):\n > ");
            try {
                priority = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Input cannot be converted to a number, task not created");
                return;
            }
        }
        while (priority < 1 || priority > 5);

        Task t = new Task(title, description, priority);
        taskList.add(t);

        System.out.printf("New task has been created at index %d\n", (taskList.size()-1));
    }

    private void actionRemoveTask() {
        System.out.print("Enter the index of the task to delete:\n > ");
        String indexString = scanner.nextLine();
        int index;

        try {
            index = Integer.parseInt(indexString);
        }
        catch (NumberFormatException e) {
            System.out.println("Input cannot be converted to a number, nothing deleted.");
            return;
        }

        Task deleted = null;
        try {
            deleted = taskList.remove(index);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("No task at specified index, nothing deleted.");
        }
        if (deleted != null) {
            System.out.printf("Task at index %d entitled \"%s\" deleted\n", index, deleted.title);
        }
        else {
            System.out.println("Problem deleting task.");
        }

    }

    private void actionUpdateTask() {
        System.out.print("Enter the index of the task to update:\n > ");
        String indexString = scanner.nextLine();
        int index;

        try {
            index = Integer.parseInt(indexString);
        }
        catch (NumberFormatException e) {
            System.out.println("Input cannot be converted to a number, nothing updated.");
            return;
        }

        Task toUpdate = null;
        try {
            toUpdate = taskList.get(index);
            System.out.print("Enter the new description:\n > ");
            String nDesc = scanner.nextLine();
            toUpdate.description = nDesc;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No task at specified index, nothing deleted.");
        }
        if (toUpdate != null) {
            System.out.printf("Task at index %d updated:\n", index);
            System.out.println(toUpdate.toString());
        }
        else {
            System.out.println("Problem updating task.");
        }
    }

    private void actionListTasksByIndex() {
        System.out.println("Your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            Task current = taskList.get(i);
            System.out.println(String.format("  (%d):\tTitle: %s\n\t\tDescription: %s\n\t\tPriority: %d",
                    i, current.title, current.description, current.getPriority()));
        }
    }

    private void actionListOnlyTasksOfPriority() {
        int priority = 0;
        do {
            System.out.print("Enter a priority to filter by (1-5):\n > ");
            try {
                priority = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Input cannot be converted to ta number, try again");
            }
        }
        while (priority < 1 || priority > 5);

        System.out.printf("Priority %d tasks:\n", priority);
        for (int i = 0; i < taskList.size(); i++) {
            Task current = taskList.get(i);
            if (current.getPriority() == priority) {
                System.out.println(String.format("  (%d):\tTitle: %s\n\t\tDescription: %s\n\t\tPriority: %d",
                        i, current.title, current.description, current.getPriority()));
            }
        }

    }

    private void actionExit() {
        System.out.println("Exiting");
        System.exit(0);
    }

    private void actionSave() throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(filename);
        try {
            gson.toJson(taskList, writer);
        }
        finally {
            writer.close();
        }
    }

    private ActionInterface[] actions = {
            new ActionInterface() {
                public String getName() { return "List your tasks by index"; }
                public void runAction() { actionListTasksByIndex(); }
            },
            new ActionInterface() {
                public String getName() { return "Add a new task"; }
                public void runAction() { actionAddTask(); }
            },
            new ActionInterface() {
                public String getName() { return "Remove a task"; }
                public void runAction() { actionRemoveTask(); }
            },
            new ActionInterface() {
                public String getName() { return "Update a task"; }
                public void runAction() { actionUpdateTask(); }
            },
            new ActionInterface() {
                public String getName() { return "Filter tasks by priority"; }
                public void runAction() { actionListOnlyTasksOfPriority(); }
            },
            new ActionInterface() {
                public String getName() { return "Save"; }
                public void runAction() {
                    try {
                        actionSave();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
            new ActionInterface() {
                public String getName() { return "Exit"; }
                public void runAction() { actionExit(); }
            }
    };

    public TaskList load() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        try {
            return gson.fromJson(reader, TaskList.class);
        }
        finally {
            reader.close();
        }
    }

    public void main(String filename) {
        this.filename = filename;
        try {
            taskList = load();
        } catch (IOException e) {
            System.out.println("Problems reading data file.");
        }

        while (true) {
            // Present all the actions in the actionTexts array
            System.out.println("Please choose an option:");
            for (int i = 0; i < actions.length; i++) {
                System.out.printf("(%d) %s\n", i, actions[i].getName());
            }
            System.out.print(" > ");
            // Get the input from the user
            String in = scanner.nextLine();
            int index;
            // Similar code to the action functions above
            try {
                index = Integer.parseInt(in);
            }
            catch (Exception e) {
                System.out.println("Error parsing input");
                continue;
            }
            // Spacing for readability
            System.out.print("\n");
            // Run the appropriate action -- it's important that those two arrays match up
            actions[index].runAction();
            // Spacing for readability
            System.out.print("\n");
        }
    }

}
