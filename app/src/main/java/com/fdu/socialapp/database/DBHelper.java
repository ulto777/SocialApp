package com.fdu.socialapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fdu.socialapp.model.Friend;
import com.fdu.socialapp.model.Group;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by mh on 2015/10/14.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper{

    private Dao<Friend,Integer> mFriendDao;
    private Dao<Group,Integer> mGroupDao;

    private static final String DB_NAME = "orm";
    private static final int DB_VERSION = 1;
    private static DBHelper instance = null;

    private DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if (instance == null){
            synchronized (DBHelper.class){
                if (instance == null){
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTableIfNotExists(connectionSource,Friend.class);
            TableUtils.createTableIfNotExists(connectionSource,Group.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public void clear(){
        try{
            TableUtils.clearTable(connectionSource,Friend.class);
            TableUtils.clearTable(connectionSource,Group.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<Friend,Integer> getmFriendDao() throws SQLException{
        if (mFriendDao == null){
            mFriendDao = getDao(Friend.class);
        }
        return mFriendDao;
    }

    public Dao<Group,Integer> getmGroupDao() throws SQLException{
        if (mGroupDao == null){
            mGroupDao = getDao(Group.class);
        }
        return mGroupDao;
    }

    @Override
    public void close() {
        super.close();
        mFriendDao = null;
        mGroupDao = null;
    }
}
