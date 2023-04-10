package Model.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理用户数据的辅助类
 * 这个代码纯纯nt，捏妈从来没见过这样用Map的
 */
public class Userdata
{
    private String account;
    private String password;
    private String label;
    private String sex;
    private String head;
    private int age;
    private String address;
    private String phone;
    private String name;
    private String background;

    public Userdata(String Username, String Password, String account, String label, String sex, String address, String phone, String head, int age, String background)
    {
        this.account = account;
        this.password = Password;
        this.label = label;
        this.sex = sex;
        this.head = head;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.name = Username;
        this.background = background;
    }

    public Userdata(ResultSet resultSet) throws SQLException
    {
        this.account = resultSet.getString("account");
        this.password = resultSet.getString("password");
        this.label = resultSet.getString("label");
        this.sex = resultSet.getString("sex");
        this.head = resultSet.getString("head");
        this.age = resultSet.getInt("age");
        this.address = resultSet.getString("address");
        this.phone = resultSet.getString("phone");
        this.name = resultSet.getString("name");
        this.background = resultSet.getString("background");
    }

    public void setUserdata(ResultSet resultSet) throws SQLException
    {
        this.account = resultSet.getString("account");
        this.password = resultSet.getString("password");
        this.label = resultSet.getString("label");
        this.sex = resultSet.getString("sex");
        this.head = resultSet.getString("head");
        this.age = resultSet.getInt("age");
        this.address = resultSet.getString("address");
        this.phone = resultSet.getString("phone");
        this.name = resultSet.getString("name");
        this.background = resultSet.getString("background");
    }


    public Userdata()
    {

    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setHead(String head)
    {
        this.head = head;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setData(ResultSet resultSet) throws SQLException
    {
        setName(resultSet.getString("name"));
        setPassword(resultSet.getString("password"));
        setAccount(resultSet.getString("account"));
        setAddress(resultSet.getString("address"));
        setAge(Integer.parseInt(resultSet.getString("age")));
        setHead(resultSet.getString("head"));
        setSex(resultSet.getString("sex"));
        setLabel(resultSet.getString("label"));
        setPhone(resultSet.getString("phone"));
    }

    public Map<String, String> getUserdata()
    {
        Map<String, String> user = new HashMap<>();
        user.put("name", name);
        user.put("password", password);
        user.put("account", account);
        user.put("address", address);
        user.put("age", String.valueOf(age));
        user.put("head", head);
        user.put("label", label);
        user.put("sex", sex);
        user.put("phone", phone);
        user.put("background", background);
        return user;

    }

    public String getAge()
    {
        return String.valueOf(age);
    }

    public String getAccount()
    {
        return account;
    }

    public String getAddress()
    {
        return address;
    }

    public String getHead()
    {
        if (head == null) head = "head1";
        return head;
    }

    public String getBackground()
    {
        return background;
    }

    public String getLabel()
    {
        return label;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getSex()
    {
        return sex;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }


}
