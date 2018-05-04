/**
 * Created by brobst.30 on 5/4/18.
 */

package com.jack.csci1660.project4;

/*
Project 4 should make use of topics covered during weeks twelve though fourteen.

Modify your code from project 3 to support saving and loading task data to/from a file. The program should try to load
the task data when the program starts and save the task data when the user decides to exit the program. Task information
should be stored in the file as JSON data. For example, the file data representing two tasks might appear as follows:


        {
          "tasks": [
            {
              "description": "Walk the dog",
              "priority": 2,
              "title": "dog"
            },
            {
              "description": "Prepare dinner",
              "priority": 1,
              "title": "Dinner"
            }
          ]
        }


Note that it might be easier to have your program save a JSON file first before trying to load one - this will ensure
that the file exists, has data, and contains valid JSON.

There is an issue using IntelliJ, Gradle, and the Scanner. If you run the project as a Gradle project, the Scanner will
not work. Here are the steps to work around the issue:

Create a Gradle run configuration specifying the existing Gradle project and "run" as the task.
Run the project with the Gradle run configuration. This will ensure that any dependencies are downloaded.
Run your project like we had been running projects prior to using Gradle by right-clicking on your java class file in
the Project view, selecting “Run Main.main()” or whatever the appropriate name is depending on your class name. For
subsequent runs, repeat this step.
 */

public class main {
    public static void main(String[] args) {
        (new TaskManager()).main("data.json");
    }
}
