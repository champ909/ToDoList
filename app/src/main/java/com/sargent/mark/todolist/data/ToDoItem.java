package com.sargent.mark.todolist.data;

/**
 * Created by mark on 7/4/17.
 *
 * Added two new column name like category and status and also created getter and setter method for them
 */

public class ToDoItem {
    private String category;
    private boolean status;
    private String description;
    private String dueDate;

    public ToDoItem(String description, String category,boolean  status, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.category=category;
        this.status=status;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
