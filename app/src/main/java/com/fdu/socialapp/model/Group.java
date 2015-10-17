package com.fdu.socialapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mh on 2015/10/14.
 */
@DatabaseTable
public class Group {

    @DatabaseField(id = true)
    public int id;

    @DatabaseField
    public String name;

    public Group(){}
}
