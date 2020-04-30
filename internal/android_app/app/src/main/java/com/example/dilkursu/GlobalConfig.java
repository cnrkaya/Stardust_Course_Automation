package com.example.dilkursu;

import com.example.dilkursu.repository.IDataConnection;
import com.example.dilkursu.repository.SqlConnector;

public class GlobalConfig {

    private static String host = "swprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com";
    private static String database = "langcoursedb";
    private static int port = 5432;
    public static String user = "swprojectadmin";
    public static String pass = "swproject12345";

    public static IDataConnection connection;

    public static String getConnectionString() {
        String url = "jdbc:postgresql://%s:%d/%s";
        return String.format(url, host, port, database);
    }

    public static void InitializeConnections() {
        SqlConnector sql = new SqlConnector();
        connection = sql;
    }

}
