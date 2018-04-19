package com.my.xxx.endan.utils;

import android.util.Log;

import com.my.xxx.endan.bean.Person;
import com.my.xxx.endan.bean.Star;


import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;

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
    public static void SaveImage(final String imagename, String imagepath) {
        File imagefile = new File(imagepath);

        /*BmobFile.uploadBatch(new String[]{imagepath}, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> list, List<String> list1) {
                Log.i("保存成功=====", imagename);
            }

            @Override
            public void onProgress(int i, int i1, int i2, int i3) {
                Log.i("保存进度", i + "---" + i1 + "---------" + i2 + "------------" + i3);
            }

            @Override
            public void onError(int i, String s) {
                Log.i("保存失败", i + "---" + s);
            }
        });*/
        final BmobFile bmobFile1 = new BmobFile(imagefile);
        bmobFile1.uploadblock(new UploadFileListener() {


            @Override
            public void done(BmobException e) {
                if (e == null) {
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.i("保存成功1:", bmobFile1.getFileUrl());
                    Star star = new Star();
                    star.setStarName(imagename);
                    //star.setFile(bmobFile1.getFileUrl());
                    star.setImagepath(bmobFile1.getFileUrl());
                    star.save(new SaveListener<String>() {

                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Log.i("保存成功2", imagename);
                            } else {
                                Log.i("保存失败3", e.getMessage() + "---" + e.getErrorCode());
                            }
                        }
                    });
                } else {
                    Log.i("保存失败1：", e.getMessage());
                }
            }
        });

       /* Star star = new Star();
        star.setStarName(imagename);
        //star.setFile(bmobFile1.getFileUrl());
        star.setImagepath(fileUrl);
        star.save(new SaveListener<String>() {

            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.i("保存成功2", imagename);
                } else {
                    Log.i("保存失败3", e.getMessage() + "---" + e.getErrorCode());
                }
            }
        });*/


    }
}
