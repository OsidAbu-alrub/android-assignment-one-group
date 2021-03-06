package com.abualrub.androidassignmentonegroup.domain;

import java.util.ArrayList;

public class Course {
    private int courseId;
    private String url;
    private String category;
    private String title;
    private String shortDesc;
    private String longDesc;
    private ArrayList<String> notes;

    public Course(){

    }

    public Course(int courseId, String url,String category, String title, String shortDesc, String longDesc, ArrayList<String> notes) {
        this.courseId = courseId;
        this.url = url;
        this.category = category;
        this.title = title;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.notes = notes;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
