package Model;

import java.sql.*;

public class DatabaseModel
{
    private String url = "jdbc:mysql://localhost:3306/weChat?useUnicode=true&characterEncoding=utf-8";
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "root";
    private String password = "114514";
    private Connection connection;
    public Statement statement;
    public PreparedStatement preparedStatement;

    public DatabaseModel()
    {

    }

    public void connect()
    {
        try
        {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, userName, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet execResult(String Sql, String... data) throws SQLException
    {
        preparedStatement = connection.prepareStatement(Sql);
        for (int i = 1; i <= data.length; i++)
        {
            preparedStatement.setString(i, data[i - 1]);
        }
        return preparedStatement.executeQuery();
    }

    public void exec(String Sql, String... data) throws SQLException
    {

        preparedStatement = connection.prepareStatement(Sql);
        for (int i = 1; i <= data.length; i++)
        {
            preparedStatement.setString(i, data[i - 1]);
        }
        preparedStatement.executeUpdate();
    }


    public void exec(String Sql)
    {
        try
        {
            preparedStatement = connection.prepareStatement(Sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception ignored)
        {
        }
    }

    public void insert(String tableName, String... data) throws SQLException
    {

        StringBuilder pre = new StringBuilder();
        for (int i = 0; i < data.length; i++)
        {
            pre.append("?").append(i == data.length - 1 ? "" : ",");
        }

        String Sql = "INSERT INTO " + tableName + " VALUES(" + pre + ")";
        preparedStatement = connection.prepareStatement(Sql);
        for (int i = 1; i <= data.length; i++)
        {

            preparedStatement.setString(i, data[i - 1]);

        }
        preparedStatement.executeUpdate();

    }

    public void delete(String tableName, String condition, String... data) throws SQLException
    {
        String Sql = "DELETE FROM " + tableName + " WHERE " + condition;
        preparedStatement = connection.prepareStatement(Sql);
        for (int i = 1; i <= data.length; i++)
        {
            preparedStatement.setString(i, data[i - 1]);
        }
        preparedStatement.executeUpdate();
    }

    public void update(String tableName, String target, String condition, String[] data) throws SQLException
    {
        String Sql = "UPDATE " + tableName + " SET " + target + " WHERE " + condition;
        preparedStatement = connection.prepareStatement(Sql);
        for (int i = 1; i <= data.length; i++)
        {
            preparedStatement.setString(i, data[i - 1]);
        }
        preparedStatement.executeUpdate();
    }


    public static boolean[] get_flag(String condition, int n)
    {
        int x1 = condition.indexOf("age");
        int x2 = condition.indexOf("degree");
        boolean[] flag = new boolean[n];
        if (x1 == -1 && x2 == -1)
        {
            return flag;
        }
        int sum = 0;
        boolean my_flag = false;
        for (int i = 0; i < condition.length(); i++)
        {
            if (i == x1 || i == x2)
            {
                my_flag = true;
            }
            if (condition.charAt(i) == '?')
            {
                if (my_flag)
                {
                    flag[sum] = true;
                    my_flag = false;
                }
                sum++;
            }
        }
        return flag;
    }

    public ResultSet select(String option, String tableName, String condition, String... data) throws SQLException
    {
        String Sql;
        Sql = "select" + option + "from" + tableName + "where" + condition;
        boolean[] flag = new boolean[data.length];
        flag = get_flag(condition, data.length);
        preparedStatement = connection.prepareStatement(Sql);
        for (int i = 1; i <= data.length; i++)
        {
            if (flag[i - 1]) preparedStatement.setInt(i, Integer.parseInt(data[i - 1]));
            preparedStatement.setString(i, data[i - 1]);
        }
        return preparedStatement.executeQuery();
    }

    public void insert(String tableName, String condition, String... data) throws SQLException
    {
        StringBuilder pre = new StringBuilder();
        for (int i = 0; i < data.length; i++)
        {
            if (i != data.length - 1) pre.append("?,");
            else pre.append("?");
        }
        String Sql = "INSERT INTO " + tableName + " VALUES(" + pre + ")";
        preparedStatement = connection.prepareStatement(Sql);
        boolean[] flag = new boolean[data.length];
        flag = get_flag(condition, data.length);
        for (int i = 1; i <= data.length; i++)
        {
            if (flag[i - 1]) preparedStatement.setInt(i, Integer.parseInt(data[i - 1]));
            preparedStatement.setString(i, data[i - 1]);
        }
        preparedStatement.executeUpdate();
    }


    public void reConnection(String Url, String UserName, String Password) throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);
        connection = DriverManager.getConnection(Url, UserName, Password);
    }


}
