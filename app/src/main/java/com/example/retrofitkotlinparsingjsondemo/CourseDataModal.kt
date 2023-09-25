package com.example.retrofitkotlinparsingjsondemo

data class CourseDataModal(
    // on below line creating variables for our modal class
    // make sure that variable name should be same to
    // that of key which is used in json response.
    var courseName: String,
    var courseimg: String,
    var courseDesc: String,
    var Prerequisites: String,
    var courseLink: String
)