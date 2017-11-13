/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Methods;



/**
 * Created by louisloh on 1/5/2017.
 */

public class UploadFile {
   public String FileName;
    public String url;

    public String getFileName() {
        return FileName;
    }

    public String getUrl() {
        return url;
    }

    public UploadFile(String fileName, String url) {
        FileName = fileName;
        this.url = url;

    }


}
