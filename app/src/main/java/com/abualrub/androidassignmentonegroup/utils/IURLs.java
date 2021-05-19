package com.abualrub.androidassignmentonegroup.utils;

public interface IURLs {
    // GET
    // TAKES: NOTHING
    // RETURNS: ARRAY OF COURSES
    public static final String URL_COURSES = "http://10.0.2.2/android-rest/group-assignment-one/courses.php";
    // =================================================================================
    // GET
    // TAKES: studentId
    // RETURNS: Student Object
    public static final String URL_GET_STUDENT = "http://10.0.2.2/android-rest/group-assignment-one/get-student.php";
    // =================================================================================
    // POST
    // TAKES:
    // 	userName
    // 	password
    // RETURNS: Student Object
    public static final String URL_LOGIN = "http://10.0.2.2/android-rest/group-assignment-one/login.php";
    // =================================================================================
    // GET
    // TAKES: studentId
    // RETURNS: ARRAY OF NOTES
    public static final String URL_NOTES = "http://10.0.2.2/android-rest/group-assignment-one/notes.php";
    // =================================================================================
    // POST
    // TAKES:
    //	firstName
    // 	lastName
    // 	userName
    // 	password
    // 	email
    // 	phoneNumber
    // RETURNS: Student Object
    public static final String URL_register = "http://10.0.2.2/android-rest/group-assignment-one/register.php";
    // =================================================================================
    // POST
    // TAKES: Student Object
    // RETURNS: Updated Student Object
    public static final String URL_UPDATE_STUDENT = "http://10.0.2.2/android-rest/group-assignment-one/update-student.php";
    // =================================================================================
    // GET
    // TAKES: USERNAME
    // RETURNS: USERNAME
    public static final String URL_VALIDATE_USERNAME = "http://10.0.2.2/android-rest/group-assignment-one/validate-username.php";
}
