package com.my.xxx.endan.utils;

import android.util.Log;

import com.my.xxx.endan.bean.Person;
import com.my.xxx.endan.bean.Star;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by sjh on 2018/4/12.
 * 250135506@qq.com
 */

public class OperationalDataBase {


    //增
    public static void InsertPerson2Bmob() {
        Person p2 = new Person();
        p2.setName("史江汉");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Log.i("添加成功", objectId);
                } else {
                    Log.i("添加失败", e.getMessage());
                }
            }
        });
    }

    //查
    public static void SelectPersonFromBmob() {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("77a4a546fb", new QueryListener<Person>() {
            @Override
            public void done(Person object, BmobException e) {
                if (e == null) {
                    Log.i("获取成功", object.getName() + object.getAddress());
                } else {
                    Log.i("获取失败", e.getMessage());
                }
            }
        });
    }

    //改
    public static void Update() {
        final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("6b6c11c537", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("更新成功", p2.getUpdatedAt());
                } else {
                    Log.i("更新失败", e.getMessage());
                }
            }
        });
    }

    //删
    public static void Delete() {
        final Person p2 = new Person();
        p2.setObjectId("6b6c11c537");
        p2.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("删除成功", p2.getUpdatedAt());
                } else {
                    Log.i("删除失败", e.getMessage());
                }
            }
        });
    }

    //保存图片
    public static void SaveImage() {
        Star star = new Star();


    }
}
