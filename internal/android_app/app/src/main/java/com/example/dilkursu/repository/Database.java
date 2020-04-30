package com.example.dilkursu.repository;

import com.example.dilkursu.GlobalConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Database implements Runnable {

    private static Database instance;
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
        this.connect();
        this.disconnect();
    }

    @Override
    public void run() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(GlobalConfig.getConnectionString(), GlobalConfig.user, GlobalConfig.pass);
        } catch (Exception e) {
            this.status = false;
        }
    }

    private void connect() {
        Thread thread = new Thread(this);
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

        return resultSet;
    }

    public Connection getConnection() {
        if (connection == null) {
            this.connect();
        }
        return connection;
    }

}
