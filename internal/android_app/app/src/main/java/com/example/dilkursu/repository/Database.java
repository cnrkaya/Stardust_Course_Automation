package com.example.dilkursu.repository;

import com.example.dilkursu.GlobalConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Database {

    private Connection connection;
    private final String host = "swprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com";
    private final String database = "langcoursedb";
    private final int port = 5432;
    private final String user = "swprojectadmin";
    private final String pass = "swproject12345";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Database() {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(GlobalConfig.getConnectionString(), GlobalConfig.user, GlobalConfig.pass);
                } catch (Exception e) {
                    status = false;
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    private void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception e) {
                this.status = false;
            } finally {
                this.connection = null;
            }
        }
    }

    public ResultSet execute(String query) {
        this.connect();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.connection, query).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
        this.disconnect();
        return resultSet;
    }

    // Threads  already coming from AsyncTask.
    public ResultSet execute2(String query) {
        this.connect();
        ResultSet resultSet = null;
        try {
            //resultSet = new ExecuteDB(this.connection, query).execute().get();
            try {
                resultSet = connection.prepareStatement(query).executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
        this.disconnect();
        return resultSet;
    }


    public Connection getConnection() {
        this.connect();
        return connection;
    }

}
