package com.my.xxx.endan.bean;


import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by sjh on 2018/4/18.
 * 250135506@qq.com
 */

public class Star extends BmobObject {
    private String starName;
    private BmobFile file;

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public BmobFile getFile() {
        return file;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }
}
