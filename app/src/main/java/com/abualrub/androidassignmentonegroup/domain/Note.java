package com.abualrub.androidassignmentonegroup.domain;

public class Note {

    private int noteId;
    private int studentId;
    private int courseId;
    private String note;

    public Note(){

    }

    public Note(int noteId, int studentId, int courseId, String note) {
        this.noteId = noteId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
