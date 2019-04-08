package com.mygradebook.model;


//import utility.ConnectionManager;

import com.hoffman.model.ConnectionManager;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named("coursesBean")
@RequestScoped
public class CoursesBean implements Serializable {

    private int courseID;
    private int courseNum;
    private String courseName;
    private String courseGradeModel;
    private ConnectionManager connectionManager =
            new ConnectionManager("Gradebook_Application", "root", "Winter I_S Coming!");


    public CoursesBean(){

    }

    public ResultSet getCourses() throws SQLException {

        Connection connection = connectionManager.getConnectionFromPool();

        if (connection == null){
            throw new SQLException("unable to open up the connection");
        }

        try {

            PreparedStatement getCourses = connection.prepareStatement(

                    "SELECT COURSEID, COURSENUM, COURSENAME" +
                            " FROM COURSES ORDER BY COURSEID"
            );

            CachedRowSet results = RowSetProvider.newFactory().createCachedRowSet();
            results.populate(getCourses.executeQuery());
            return results;

        }

        finally {
            connection.close();
            connectionManager.returnConnectionToPool(connection);
        }

    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseGradeModel() {
        return courseGradeModel;
    }

    public void setCourseGradeModel(String courseGradeModel) {
        this.courseGradeModel = courseGradeModel;
    }
}
