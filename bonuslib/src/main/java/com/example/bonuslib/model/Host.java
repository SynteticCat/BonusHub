package com.example.bonuslib.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Timur on 13-Apr-17.
 */
@DatabaseTable(tableName = "host")
public class Host{

    public final static String HOST_NAME_FIELD_NAME = "title";

    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = "title")
    private String title;

    @DatabaseField(columnName = "description")
    private String description;

    public Host() {

    }

    public Host(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}