package com.fdu.socialapp.database;

import android.content.Context;
import android.util.Log;

import com.fdu.socialapp.model.Friend;
import com.fdu.socialapp.model.Group;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mh on 2015/10/14.
 */
public class DBManager {

    private static DBManager instance = null;

    private DBManager(){}

    public static DBManager getInstance(){
        if (instance == null){
            synchronized (DBManager.class){
                if (instance == null){
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    private DBHelper mDBHelper;

    public Dao<Friend,Integer> mFriendDao;
    public Dao<Group,Integer> mGroupDao;

    public void create(Context context){
        mDBHelper = DBHelper.getInstance(context);

        try{
            mFriendDao = mDBHelper.getmFriendDao();
            mGroupDao = mDBHelper.getmGroupDao();
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
        }
    }

    public void close(){
        mFriendDao = null;
        mGroupDao = null;
    }

    //===================================Save Function================================

    public void saveFriends(Friend friend){
        try{
            mFriendDao.createOrUpdate(friend);
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
        }
    }

    public void saveGroup(Group group){
        try{
            mGroupDao.createOrUpdate(group);
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
        }
    }

    //====================================Get Function=====================================

    public List<Friend> getFriendList(){
        try{
            List<Friend> friends = mFriendDao.queryForAll();
            return friends;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return null;
        }
    }

    public List<Group> getGroupList(){
        try{
            List<Group> groups = mGroupDao.queryForAll();
            return groups;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return null;
        }
    }

    public List<Friend> getFriendsInGroup(int groupId){
        try{
            List<Friend> friends = mFriendDao.queryForEq(Friend.FriendState.ARG_GROUP_ID, groupId);
            return friends;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return null;
        }
    }

    //====================================Search Function==================================

    public Friend searchFriendById(Integer id){
        try{
            Friend friend = mFriendDao.queryForId(id);
            return friend;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return null;
        }
    }

    public Group searchGroupById(Integer id){
        try{
            Group group = mGroupDao.queryForId(id);
            return group;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return null;
        }
    }

    //===================================Delete Function===================================

    public void clearData(){
        mDBHelper.clear();
    }

    public boolean deleteFriend(Integer id){
        try{
            int rtn = mFriendDao.deleteById(id);
            if (rtn == 1)
                return true;
            else
                return false;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return false;
        }
    }

    public boolean deleteGroup(Integer id){
        try{
            int rtn = mGroupDao.deleteById(id);
            if (rtn == 1)
                return true;
            else
                return false;
        }catch (SQLException e){
            Log.e("DB",e.getMessage());
            return false;
        }
    }


}
