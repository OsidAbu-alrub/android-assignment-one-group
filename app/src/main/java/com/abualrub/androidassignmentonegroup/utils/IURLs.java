package com.abualrub.androidassignmentonegroup.utils;

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public interface IURLs {
    // GET
    // TAKES: NOTHING
    // RETURNS: ARRAY OF COURSES
    public static final String URL_COURSES = "http://98.90.90.110/android-rest/group-assignment-one/courses.php";
    // =================================================================================
    // GET
    // TAKES: studentId
    // RETURNS: Student Object
    public static final String URL_GET_STUDENT = "http://98.90.90.110/android-rest/group-assignment-one/get-student.php";
    // =================================================================================
    // POST
    // TAKES:
    // 	userName
    // 	password
    // RETURNS: Student Object
    public static final String URL_LOGIN = "http://98.90.90.110/android-rest/group-assignment-one/login.php";
    // =================================================================================
    // GET
    // TAKES:
    //	studentId
    //	courseId
    // RETURNS:
    //  notes array
    public static final String URL_NOTES = "http://98.90.90.110/android-rest/group-assignment-one/notes.php";
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
    public static final String URL_REGISTER = "http://98.90.90.110/android-rest/group-assignment-one/register.php";
    // =================================================================================
    // POST
    // TAKES: Student Object
    // RETURNS: Updated Student Object
    public static final String URL_UPDATE_STUDENT = "http://98.90.90.110/android-rest/group-assignment-one/update-student.php";
    // =================================================================================
}
