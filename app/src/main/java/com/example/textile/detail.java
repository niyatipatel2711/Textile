package com.example.textile;

public class detail {
    private String Name;
    private String imgURL;
    private String url;

    public detail(){}
    public detail(String Name,String imgURL,String url){
        this.Name = Name;
        this.imgURL = imgURL;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
