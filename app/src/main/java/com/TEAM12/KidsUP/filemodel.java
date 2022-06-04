package com.TEAM12.KidsUP;

public class filemodel
{
  String title,vurl,category;

    public filemodel() {
    }

    public filemodel(String title, String vurl,String category) {
        this.title = title;
        this.vurl = vurl;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
