package com.example.ft.musicdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.RegexValidator;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.activitys.LoginActivity;
import com.example.ft.musicdemo.helps.Realmhelp;
import com.example.ft.musicdemo.helps.UserHelp;
import com.example.ft.musicdemo.models.UserModel;

import java.util.List;

/*
* 任务和返回栈（Task栈）
* 任务是指在执行特定作业时与用户交互的一系列Activity。
* 这些Activity按照各自的打开顺序排列在堆栈（即返回栈）中 。Task栈
*
* Intent标志符
* 启动新的Activity时，可以通过在传递给startActivity()的Intent中加入相应的标志，
* 修改Activity与其任务的默认关联方式
*  Intent.FLAG_ACTIVITY_CLEAR_TASK 清除当前Task栈的Activity
*  Intent.FLAG_ACTIVITY_NEW_TASK 创建新的Task栈
*
* */

public class UserUtiles {
    /*
    * 验证登陆用户输入的合法性
    * */
    public static boolean validateLogin(Context context,String phone,String password){
        //简单
        //RegexUtils.isMobileSimple(phone);
        //精确
        //RegexUtils.isMobileExact(phone);
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"输入无效手机号",Toast.LENGTH_SHORT ).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        /*
        * 1.用户当前手机号是否已经被注册了
        * 2.用户输入的手机号和密码是否匹配
        * */
        if (!UserUtiles.userExistFromPhone(phone)){
            Toast.makeText(context,"该手机号未注册",Toast.LENGTH_SHORT ).show();
            return false;
        }
        Realmhelp realmhelp=new Realmhelp();
        boolean result=realmhelp.validateUser(phone,EncryptUtils.encryptMD5ToString(password));
        realmhelp.close();
        if (!result){
            Toast.makeText(context,"手机号或者密码不正确",Toast.LENGTH_SHORT ).show();
            return false;
        }

        //保存用户登录标记
        boolean isSave= SPUtils.saveUser(context,phone);
        if (!isSave){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT ).show();
            return false;
        }
        //保存用户标记，在全局单例类之中
        UserHelp.getInstance().setPhone(phone);
        return true;
    }

    /*
    * 退出登陆
    * */
    public static void logout(Context context){
        //删除sp保存的用户标记信息
        boolean isRemove=SPUtils.removeUser(context);
        if (!isRemove){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT ).show();
            return;
        }


        Intent intent=new Intent(context,LoginActivity.class);
        //添加intent标志符，清理task栈，并且新生成一个Task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit );
    }


    /**
     * 注册用户
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context,String phone,String password,String passwordConfirm){

        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"输入无效手机号",Toast.LENGTH_SHORT ).show();
            return false;
        }
        if (StringUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT ).show();
            return false;
        }

        //用户当前输入的手机是否已经被注册
        /*
        * 1.通过Realm获取到当前已注册的所有用户
        * 2.根据用户输入的手机号匹配查询的所有用户，如果可以匹配则证明该手机号已经被注册了，否则就表示手机号还未注册
        * */
        if (UserUtiles.userExistFromPhone(phone)){
            Toast.makeText(context,"该手机号已注册",Toast.LENGTH_SHORT ).show();
            return false;
        }


        UserModel userModel=new UserModel();
        userModel.setPhone(phone);
        userModel.setPassWord(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户到数据库
     * @param userModel
     */
    public static void saveUser(UserModel userModel){
        Realmhelp realmhelp=new Realmhelp();
        realmhelp.saveUser(userModel);
        realmhelp.close();
    }

    /*
    * 根据手机号判断用户是否存在
    * */
    public static boolean userExistFromPhone(String phone){
        boolean result=false;
        Realmhelp realmhelp=new Realmhelp();
        List<UserModel> allUser=realmhelp.getAllUser();
        for (UserModel userModel:allUser){
            if (userModel.getPhone().equals(phone)){
                //当前手机号已经存在于数据库中了
                result=true;
                break;
            }
        }
        realmhelp.close();
        return result;
    }

    /*
    * 验证是否存在已登陆用户
    * */
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }

    /*
    * 修改密码
    * 1.数据验证
    *       1.原密码是否输入
    *       2.新密码是否输入并且新密码与确定密码是否相同
    *       3.原密码是否正确
    *           1.Realm 获取到当前登录的用户模型
    *           2.根据用户模型中保存的密码匹配用户原密码
    * 2.利用Realm模型自动更新的特性完成密码的修改
    * */
    public static boolean changePassword(Context context,String oldPassword,String password,String passwordConfirm){
        if (TextUtils.isEmpty(oldPassword)){
            Toast.makeText(context,"请输入原密码",Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (TextUtils.isEmpty(password) || !password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认新密码",Toast.LENGTH_SHORT ).show();
            return false;
        }

        //验证原密码是否正确
        Realmhelp realmhelp=new Realmhelp();
        UserModel userModel=realmhelp.getUser();
        if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassWord())){
            Toast.makeText(context,"原密码不正确",Toast.LENGTH_SHORT ).show();
            return false;
        }
        realmhelp.changePassword(EncryptUtils.encryptMD5ToString(password));
        realmhelp.close();
        return true;
    }


}
