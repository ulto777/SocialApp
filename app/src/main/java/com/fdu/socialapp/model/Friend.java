package com.fdu.socialapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mh on 2015/10/14.
 */
@DatabaseTable
public class Friend {

    @DatabaseField(id = true)
    public int id;

    @DatabaseField
    public String name;

    @DatabaseField(columnName = FriendState.ARG_GROUP_ID)
    public int foreignGroupId;

    public Friend(){}

    public static class FriendState{
        public static final String ARG_GROUP_ID = "groupId";
    }
}
