package com.example.dilkursu.repository;

import android.util.Log;
import android.widget.Toast;

import com.example.dilkursu.models.Credential;
import com.example.dilkursu.models.Person;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class SqlConnector implements IDataConnection {

    private Database database;
    private ResultSet resultSet;


    public SqlConnector() {
        database = new Database();
        resultSet = null;

    }


    @Override
    public Credential checkUserCredentials(String email, String password) {
        Credential credential = null;
        try {
            String storedProcedureCall = "{CALL checkUserCredentials(?,?,?,?)}";
            CallableStatement callableStatement = database.getConnection().prepareCall(storedProcedureCall);

            //input
            callableStatement.setString(1, email);
            callableStatement.setString(2, password);

            //output
            callableStatement.registerOutParameter(3, Types.CHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            callableStatement.executeUpdate();

            //declare variables
            String person_id = callableStatement.getString(3);
            int authorization_level = callableStatement.getInt(4);

            if (person_id != null) {
                credential = new Credential(person_id, authorization_level);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return credential;
    }
}
