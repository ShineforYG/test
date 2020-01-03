package com.shine.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcTest {
    public static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
    public static final String USER = "root";
    public static final String PASSWORD = "1234";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT *  FROM address");
        ResultSetMetaData resultSetMD = rs.getMetaData();
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= resultSetMD.getColumnCount(); i++) {
            map.put(resultSetMD.getColumnName(i), resultSetMD.getColumnTypeName(i));
        }
        for (String name : map.keySet()) {
            System.out.print(name + "\t\t\t");
        }
        System.out.println();
        //如果有数据，rs.next()返回true
        while (rs.next()) {
            for (String name : map.keySet()){
                System.out.print(rs.getString(name) + "\t\t\t");
            }
            System.out.println();
        }
    }
}
