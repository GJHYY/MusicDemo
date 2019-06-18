package com.example.ft.musicdemo.helps;

import com.example.ft.musicdemo.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class Realmhelp {
    private Realm mRealm;

    public Realmhelp() {
        mRealm = Realm.getDefaultInstance();
    }

    /*
     * 关闭数据库
     * */
    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /*
     * 保存用户信息
     * */
    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        //mRealm.insertOrUpdate(userModel);更新主键的存储信息
        mRealm.commitTransaction();
    }

    /*
    * 返回所有用户
    * */
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query=mRealm.where(UserModel.class);
        RealmResults<UserModel>results= query.findAll();
        return results;
    }

    /*
    * 验证用户信息
    * */
    public boolean validateUser(String phone,String password){
        boolean result=false;
        RealmQuery<UserModel> query=mRealm.where(UserModel.class);
        query.equalTo("phone",phone).equalTo("passWord",password);
        UserModel userModel=query.findFirst();
        if (userModel!=null){
            return true;
        }
        return result;
    }

    /*
    * 获取当前用户
    * */
    public UserModel getUser(){
        RealmQuery<UserModel> query =mRealm.where(UserModel.class);
        UserModel userModel=query.equalTo("phone",UserHelp.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /*
    * 修改密码
    * */

    public void changePassword(String password){
        UserModel userModel=getUser();
        mRealm.beginTransaction();
        userModel.setPassWord(password);
        mRealm.commitTransaction();
    }
}
