package com.example.dtuadminkapil.User.ebook;

public class ebookdata {
    private String name,pdfurl;

    public ebookdata() {
    }

    public ebookdata(String name, String pdfurl) {
        this.name = name;
        this.pdfurl = pdfurl;
    }

    public String getName() {
        return name;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }
}
