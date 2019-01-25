package com.mygradebook.model;

import com.hoffman.model.ConnectionManager;
import com.hoffman.model.Query;
import com.hoffman.model.QueryType;
//
public class Tester {

    public static void main(String[] args){

        ConnectionManager connectionManager = new ConnectionManager("Gradebook_Application", "root", "Winter I_S Coming!");
        Query query = new Query(QueryType.SELECT," * FROM Courses", connectionManager.getConnectionFromPool());
        query.executeQuery();



    }

}
