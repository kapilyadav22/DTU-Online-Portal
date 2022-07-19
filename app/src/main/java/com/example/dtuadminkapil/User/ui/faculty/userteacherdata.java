package com.example.dtuadminkapil.User.ui.faculty;

public class userteacherdata {
    private String name,email,post,image,key;

    public userteacherdata() {
    }

    public userteacherdata(String name, String email, String post, String image, String key) {
        this.name = name;
        this.email = email;
        this.post = post;
        this.image = image;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPost() {
        return post;
    }

    public String getImage() {
        return image;
    }

    public String getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
