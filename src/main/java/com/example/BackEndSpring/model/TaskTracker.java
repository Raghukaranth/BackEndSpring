package com.example.BackEndSpring.model;

import jakarta.persistence.*;

@Entity
public class TaskTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String task;

    public TaskTracker() {
    }

    public TaskTracker(String task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "TaskTracker{" +
                "id=" + id +
                ", task='" + task + '\'' +
                '}';
    }
}
