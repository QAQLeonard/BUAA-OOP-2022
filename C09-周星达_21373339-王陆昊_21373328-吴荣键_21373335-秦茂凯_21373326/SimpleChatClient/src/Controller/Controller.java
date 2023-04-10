package Controller;

import Model.ChatManager;
import Model.Data.MsgData;
import Model.Data.Userdata;
import Model.DatabaseModel;
import View.*;
import View.Alert;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

public class Controller
{
    public static Userdata userdata;//用户数据
    public static DatabaseModel database;//数据库
    public static FriendPage friendPage;
    public static SearchFriend searchFriend;
    public static Alert alert;
    private Login login;//登录对话框
    private Register register;//注册页面
    private Forget forget;//忘记密码页面
    private MainWindow mainWindow;//主页面
    private Homepage homepage;//主页
    private ChangeInfo changeInfo;//修改个人信息页面
    private HeadPortrait headPortrait;

    public Controller() throws Exception
    {
        login = new Login();
        register = new Register();
        userdata = new Userdata();
        database = new DatabaseModel();
        forget = new Forget();
        mainWindow = new MainWindow();
        homepage = new Homepage();
        changeInfo = new ChangeInfo();
        alert = new Alert();
        friendPage = new FriendPage();
        searchFriend = new SearchFriend();
        headPortrait = new HeadPortrait();
        MsgData.msg = new Vector<>();
        MsgData.MsgMap = new HashMap<>();
        MsgData.accountList = new Vector<>();
        database.connect();
        login.show();
    }

    public static void setHeadPortrait(Button button, String head)
    {
        button.setStyle(String.format("-fx-background-image: url('/View/Fxml/CSS/Image/head/%s.jpg')", head));//修改头像
    }

    public static void setHeadPortrait(Button button, String file, String background)
    {
        button.setStyle(String.format("-fx-background-image: url('/View/Fxml/CSS/Image/%s/%s.jpg')", file, background));//修改个人主页图像
    }

    //exec()方法用于实现各个页面的各种交互
    public void exec()
    {
        headPortrait.setModality(register);
        headPortrait.setModality(changeInfo);
        alert.setModality(mainWindow);
        alert.setModality(searchFriend);
        ChatManager.getInstance().setMainWindow(mainWindow);
        initEvent();
        dialogExec();
        forgetExec();
        alterPersonExec();
        registerExec();
        sendMsgExec();
        OptionHead();
        SearchFriends();
        find();
        FriendInfo();
        saveRemark();//
        login.show();
    }

    /**
     * 该方法通过页面对象 以及给定的id 选择页面的元素
     * 用法:TextField t = (TextField)$(dialog,"UserName");
     * 这样选出登入框对象的id为UserName的输入框 之后就可以为 t 绑定事件了
     */
    private Object $(Window window, String id)
    {
        return window.getRoot().lookup("#" + id);
    }

    /**
     * oven
     * 初始化事件
     */
    public void initEvent()
    {
        ((Button) $(login, "register")).setOnAction(event ->
        {
            //隐藏及预处理登录控件
            login.hide();
            login.clear();
            login.resetErrorTip();
            //显示注册控件
            register.show();
        });
        ((Button) $(register, "back")).setOnAction(event ->
        {
            //隐藏及预处理注册控件
            register.hide();
            register.clear();
            register.resetErrorTip();
            //显示登录控件
            login.show();
        });
        ((Button) $(login, "getBack")).setOnAction(event ->
        {
            login.hide();
            login.clear("Password");
            forget.show();

        });
        ((Button) $(forget, "cancel")).setOnAction(event ->
        {
            //隐藏及预处理忘记密码控件
            forget.hide();
            forget.clear();
            forget.resetErrorTip();
            //显示登录控件
            login.show();
        });
        ((Button) $(mainWindow, "more")).setOnAction(event ->
        {
            //显示主页
            homepage.show();
        });
        ((Button) $(homepage, "alter")).setOnAction(event ->
        {
            changeInfo.setUserData(userdata.getUserdata());//将用户数据传入修改个人信息页面
            changeInfo.show();//显示修改个人信息页面
        });
        ((Button) $(register, "ChooseHead")).setOnAction(event ->
        {
            headPortrait.show();//显示选择头像页面
        });
        ((Button) $(mainWindow, "maximization")).setOnAction(event ->
        {
            searchFriend.clear();//清空搜索框
            searchFriend.show();//显示搜索好友页面
        });
    }

    /**
     *  oven
     * 登入功能
     */
    private void dialogExec()
    {
        ((Button) $(login, "enter")).setOnAction(event ->
        {
            login.resetErrorTip();
            String UserName = ((TextField) $(login, "UserName")).getText();
            String Password = ((PasswordField) $(login, "Password")).getText();
            if (UserName.equals("") || Password.equals(""))
            {
                if (UserName.equals(""))
                {
                    login.setErrorTip("accountError", "！未输入账号");
                }
                if (Password.equals(""))
                {
                    login.setErrorTip("passwordError", "！未输入密码");
                }
            }
            else
            {
                ResultSet resultSet = null;
                try
                {
                    resultSet = database.execResult("SELECT * FROM user WHERE account=?", UserName);//查询数据库
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                try
                {
                    //判断账号密码是否正确
                    assert resultSet != null;
                    if (resultSet.next())
                    {
                        if (resultSet.getString(3).equals(Password))
                        {
                            ResultSet set = database.execResult("SELECT * FROM dialog WHERE account = ?", UserName);
                            if (set.next())
                            {
                                login.setErrorTip("accountError", "该账号已经登入，不能重复登入!");
                            }
                            else
                            {
                                database.exec("INSERT INTO dialog VALUES(?)", UserName);//登入记录
                                //设置用户数据
                                userdata.setUserdata(resultSet);
                                userdata.setData(resultSet);
                                //个人主页数据
                                homepage.setUserData(userdata.getUserdata());
                                login.close();
                                //主窗口
                                mainWindow.setHead(userdata.getHead());
                                mainWindow.setPersonalInfo(userdata.getAccount(), userdata.getName(), userdata.getAddress(), userdata.getPhone());

                                ResultSet resultSet1 = database.execResult("SELECT head,account,remark FROM user,companion WHERE account = Y_account AND I_account=?", UserName);

                                //聊天助手
                                mainWindow.addFriend("system", "SimpleChat聊天助手", "聊天助手");
                                ((Label) $(mainWindow, "Y_account")).setText("SimpleChat聊天助手");
                                MsgData.msg.add(new Vector<>());
                                MsgData.accountList.add("SimpleChat聊天助手");
                                MsgData.msgTip.put("SimpleChat聊天助手", 0);
                                //所有好友
                                while (resultSet1.next())
                                {
                                    MsgData.msg.add(new Vector<>());
                                    String temp = resultSet1.getString("account");
                                    MsgData.accountList.add(temp);
                                    MsgData.msgTip.put(temp, 0);
                                    mainWindow.addFriend(resultSet1.getString("head"), resultSet1.getString("account"), resultSet1.getString("remark"), database, friendPage);
                                }

                                mainWindow.addLeft("system", "欢迎使用SimpleChat,赶快找好友聊天吧!");
                                MsgData.msg.get(0).add("SimpleChat聊天助手 欢迎使用SimpleChat,赶快找好友聊天吧!");
                                //输入框禁用
                                ((TextField) $(mainWindow, "input")).setDisable(true);
                                ((Button) $(mainWindow, "send")).setDisable(true);
                                //开始选择聊天助手
                                mainWindow.friendList.getSelectionModel().select(0);
                                //获取已登入的好友
                                ResultSet resultSet2 = database.execResult("SELECT Y_account FROM companion WHERE I_account=? AND Y_account in (SELECT account FROM dialog)", UserName);
                                while (resultSet2.next())
                                {
                                    int i = MsgData.accountList.indexOf(resultSet2.getString("Y_account"));
                                    if (i != -1)
                                    {
                                        mainWindow.friendVector.get(i).setOnline();//已登入就设置为登入状态
                                    }
                                }
                                mainWindow.friendVector.get(0).setOnline();//否则未登入状态
                                ChatManager.getInstance().connect("127.0.0.1", UserName);//链接服务器
                                mainWindow.show();
                            }
                        }
                        else
                        {
                            login.setErrorTip("passwordError", "！您输入的密码有误");
                        }
                    }
                    else
                    {
                        login.setErrorTip("accountError", "！账号未注册");
                    }

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 发消息功能
     * oven
     */
    private void sendMsgExec()
    {
        ((Button) $(mainWindow, "send")).setOnAction(event ->
        {

            String youAccount = MsgData.accountList.get(mainWindow.friendList.getSelectionModel().getSelectedIndex());//获取当前聊天对象的账号

            try
            {
                //选择好友
                ResultSet resultSet = database.execResult("SELECT * FROM dialog WHERE account=?", youAccount);
                if (resultSet.next())
                {
                    String input = ((TextField) $(mainWindow, "input")).getText();
                    if (!input.equals(""))
                    {
                        String line = userdata.getAccount() + " " + youAccount + " " + input;
                        mainWindow.addRight(userdata.getHead(), input);//添加自己的消息
                        try
                        {
                            ChatManager.getInstance().send(line);//向服务器发消息
                            int i = MsgData.accountList.indexOf(youAccount);//添加到消息集
                            if (i != -1)
                            {
                                MsgData.msg.get(i).add(userdata.getAccount() + " " + input);
                            }
                            ((TextField) $(mainWindow, "input")).clear();//清空输入框
                        }
                        catch (Exception e)
                        {
                            alert.setInformation("你断开了链接!");
                            alert.exec();
                            e.printStackTrace();
                        }

                    }

                }
                else
                {
                    alert.setInformation("对方暂时不在线，你发的消息对方无法接收!");
                    alert.exec();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


        });

        ((TextField) $(mainWindow, "input")).setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                String youAccount = MsgData.accountList.get(mainWindow.friendList.getSelectionModel().getSelectedIndex());//获取当前聊天对象的账号

                try
                {
                    //选择好友
                    ResultSet resultSet = database.execResult("SELECT * FROM dialog WHERE account=?", youAccount);
                    if (resultSet.next())
                    {
                        String input = ((TextField) $(mainWindow, "input")).getText();
                        if (!input.equals(""))
                        {
                            String line = userdata.getAccount() + " " + youAccount + " " + input;
                            mainWindow.addRight(userdata.getHead(), input);//添加自己的消息
                            try
                            {
                                ChatManager.getInstance().send(line);//向服务器发消息
                                int i = MsgData.accountList.indexOf(youAccount);//添加到消息集
                                if (i != -1)
                                {
                                    MsgData.msg.get(i).add(userdata.getAccount() + " " + input);
                                }
                                ((TextField) $(mainWindow, "input")).clear();//清输入框
                            }
                            catch (Exception e)
                            {
                                alert.setInformation("你断开了链接!");
                                alert.exec();
                                e.printStackTrace();
                            }

                        }
                    }
                    else
                    {
                        alert.setInformation("对方暂时不在线，你发的消息对方无法接收!");
                        alert.exec();
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     *  oven
     * 忘记密码功能
     */
    private void forgetExec()
    {
        ///点击重置按钮
        ((Button) $(forget, "reset")).setOnAction(event ->
        {
            forget.resetErrorTip();
            String account = ((TextField) $(forget, "account")).getText();
            String name = ((TextField) $(forget, "name")).getText();
            String phone = ((TextField) $(forget, "phone")).getText();
            String password = ((PasswordField) $(forget, "password")).getText();
            String rePassword = ((PasswordField) $(forget, "rePassword")).getText();
            String accountRegExp = "^[0-9a-zA-Z]{1,15}$";
            String nameRegExp = "^[a-zA-Z]{1,100}$";
            String phoneRegExp = "^[0-9]{11}$";
            String passwordReExp = "^[a-zA-Z0-9]{6,20}$";
            String rePasswordRegExp = "^[a-zA-Z0-9]{6,20}$";

            if (account.equals("") || name.equals("") || phone.equals("") || password.equals("") || rePassword.equals(""))
            {
                if (account.equals(""))
                {
                    forget.setErrorTip("accountError", "！未输入账号");
                }
                if (name.equals(""))
                {
                    forget.setErrorTip("nameError", "！未输入姓名");
                }
                if (phone.equals(""))
                {
                    forget.setErrorTip("phoneError", "！未输入电话号");
                }
                if (password.equals(""))
                {
                    forget.setErrorTip("passwordError", "！未输入密码");
                }
                if (rePassword.equals(""))
                {
                    forget.setErrorTip("rePasswordError", "！未输入密码");

                }

            }
            else if (!account.matches(accountRegExp) || !name.matches(nameRegExp) || !phone.matches(phoneRegExp) || !password.matches(passwordReExp) || !rePassword.matches(rePasswordRegExp))
            {
                if (!account.matches(accountRegExp))
                {
                    forget.setErrorTip("accountError", "！错误,账号是长度不超过15位的中文和英文和数字");
                }
                if (!name.matches(nameRegExp))
                {
                    forget.setErrorTip("nameError", "！姓名格式错误");
                }
                if (!phone.matches(phoneRegExp))
                {
                    forget.setErrorTip("phoneError", "！电话号格式错误");
                }
                if (!password.matches(passwordReExp))
                {
                    forget.setErrorTip("passwordError", "！错误,密码是长度在6-20位的英文字母和数字");
                }
                if (!rePassword.matches(rePasswordRegExp))
                {
                    forget.setErrorTip("rePasswordError", "！错误,密码是长度在6-20位的英文字母和数字");
                }
            }
            else
            {
                try
                {
                    ResultSet resultSet = database.execResult("SELECT * FROM user WHERE account = ?", account);
                    if (resultSet.next())
                    {
                        if (name.equals(resultSet.getString(2)))
                        {
                            if (phone.equals(resultSet.getString(9)))
                            {
                                if (password.equals(resultSet.getString(3)))
                                {
                                    forget.setErrorTip("passwordError", "！新密码不能和旧密码一样");
                                }
                                else
                                {
                                    if (password.equals(rePassword))
                                    {
                                        database.exec("UPDATE user SET password = ? WHERE account=?", password, account);
                                        forget.close();
                                        forget.clear();
                                        forget.resetErrorTip();
                                        login.show();
                                    }
                                    else
                                    {
                                        forget.setErrorTip("rePasswordError", "！两次密码输入不一致");
                                    }
                                }
                            }
                            else
                            {
                                forget.setErrorTip("phoneError", "！电话号输入错误");
                            }
                        }
                        else
                        {
                            forget.setErrorTip("nameError", "！姓名输入错误");
                        }
                    }
                    else
                    {
                        forget.setErrorTip("accountError", "！该账号不存在");
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     *  oven
     * 注册功能
     */
    private void registerExec()
    {
        ///点击注册按钮
        ((Button) $(register, "register")).setOnAction(event ->
        {
            register.resetErrorTip();
            //获取输入的信息
            String account = ((TextField) $(register, "account")).getText();
            String name = ((TextField) $(register, "name")).getText();
            String password = ((PasswordField) $(register, "password")).getText();
            String rePassword = ((PasswordField) $(register, "rePassword")).getText();
            String age = ((TextField) $(register, "age")).getText();
            String sex;
            String phone = ((TextField) $(register, "phone")).getText();
            RadioButton man = ((RadioButton) $(register, "man"));

            String accountRegExp = "^[0-9a-zA-Z]{1,15}$";
            String nameRegExp = "^[a-zA-Z]{1,100}$";
            String phoneRegExp = "^[0-9]{11}$";
            String ageRegExp = "^\\d{1,3}$";
            String passwordReExp = "^[a-zA-Z0-9]{6,20}$";
            String rePasswordRegExp = "^[a-zA-Z0-9]{6,20}$";

            if (account.equals("") || name.equals("") || password.equals("") || rePassword.equals("") || age.equals("") || phone.equals(""))
            {
                if (account.equals(""))
                {
                    register.setErrorTip("accountError", "！未输入账号");
                }
                if (name.equals(""))
                {
                    register.setErrorTip("nameError", "！未输入姓名");
                }
                if (password.equals(""))
                {
                    register.setErrorTip("passwordError", "！未输入密码");
                }
                if (rePassword.equals(""))
                {
                    register.setErrorTip("rePasswordError", "！未输入密码");
                }
                if (age.equals(""))
                {
                    register.setErrorTip("ageError", "！未输入年龄");
                }
                if (phone.equals(""))
                {

                    register.setErrorTip("phoneError", "！未输入电话号");

                }
            }
            else if (!account.matches(accountRegExp) || !name.matches(nameRegExp) || !password.matches(passwordReExp) || !rePassword.matches(rePasswordRegExp) || !age.matches(ageRegExp) || !phone.matches(phoneRegExp))
            {

                if (!Pattern.matches(accountRegExp, account))
                {
                    register.setErrorTip("accountError", "！错误,账号名是长度不超过15位的中文和英文和数字");
                }
                if (!Pattern.matches(nameRegExp, name))
                {
                    register.setErrorTip("nameError", "！错误的姓名格式");
                }
                if (!Pattern.matches(passwordReExp, password))
                {
                    register.setErrorTip("passwordError", "！错误,密码应该是长度在6-20位的英文字母和数字");
                }
                if (!Pattern.matches(rePasswordRegExp, rePassword))
                {
                    register.setErrorTip("rePasswordError", "！错误,密码应该是长度在6-20位的英文字母和数字");
                }
                if (!Pattern.matches(ageRegExp, age))
                {
                    register.setErrorTip("ageError", "！错误，年龄只能是数字");
                }
                if (!Pattern.matches(phoneRegExp, phone))
                {
                    register.setErrorTip("phoneError", "！电话号格式错误");
                }

            }
            else
            {
                try
                {
                    ResultSet resultSet = database.execResult("SELECT * FROM user WHERE account=?", account);
                    if (!resultSet.next())
                    {
                        if (password.equals(rePassword))
                        {
                            if (man.isSelected())
                            {
                                sex = "man";
                            }
                            else
                            {
                                sex = "woman";
                            }
                            try
                            {
                                database.exec("INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)", account, name, password, age, sex, userdata.getHead(), "", "", phone, "background1");
                                register.close();
                                register.clear();
                                register.resetErrorTip();
                                login.show();
                            }
                            catch (SQLException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            register.setErrorTip("rePasswordError", "！两次密码不一致");
                        }

                    }
                    else
                    {
                        register.setErrorTip("accountError", "！错误,该账号已存在");
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * 选择头像
     * oven
     */
    public void OptionHead()
    {
        ((Button) $(headPortrait, "submit")).setOnAction((ActionEvent event) ->
        {
            RadioButton one = ((RadioButton) $(headPortrait, "one"));
            RadioButton two = ((RadioButton) $(headPortrait, "two"));
            RadioButton three = ((RadioButton) $(headPortrait, "three"));
            RadioButton four = ((RadioButton) $(headPortrait, "four"));
            RadioButton five = ((RadioButton) $(headPortrait, "five"));
            RadioButton six = ((RadioButton) $(headPortrait, "six"));
            RadioButton seven = ((RadioButton) $(headPortrait, "seven"));
            RadioButton eight = ((RadioButton) $(headPortrait, "eight"));
            RadioButton nine = ((RadioButton) $(headPortrait, "nine"));
            RadioButton ten = ((RadioButton) $(headPortrait, "ten"));
            if (one.isSelected())
            {
                userdata.setHead("head1");
            }
            else if (two.isSelected())
            {
                userdata.setHead("head2");
            }
            else if (three.isSelected())
            {
                userdata.setHead("head3");
            }
            else if (four.isSelected())
            {
                userdata.setHead("head4");
            }
            else if (five.isSelected())
            {
                userdata.setHead("head5");
            }
            else if (six.isSelected())
            {
                userdata.setHead("head6");
            }
            else if (seven.isSelected())
            {
                userdata.setHead("head7");
            }
            else if (eight.isSelected())
            {
                userdata.setHead("head8");
            }
            else if (nine.isSelected())
            {
                userdata.setHead("head9");
            }
            else if (ten.isSelected())
            {
                userdata.setHead("head10");
            }
            setHeadPortrait(((Button) $(register, "HeadPortrait")), userdata.getHead());
            setHeadPortrait(((Button) $(changeInfo, "head")), userdata.getHead());
            setHeadPortrait(((Button) $(changeInfo, "background")), "head1", userdata.getHead());
            headPortrait.close();
        });
    }

    /**
     *oven
     * 添加好友
     */
    public void SearchFriends()
    {
        ((TextField) $(searchFriend, "textInput")).setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                searchFriend.clear();
                String UserName = ((TextField) $(searchFriend, "textInput")).getText();
                ((TextField) $(searchFriend, "textInput")).clear();
                if (UserName.equals(""))
                {
                    alert.setInformation("未输入账号!");
                    alert.exec();
                }
                else if (UserName.equals(userdata.getAccount()))
                {
                    alert.setInformation("不能输入自己的账号!");
                    alert.exec();
                }
                else
                {
                    ResultSet resultSet;
                    try
                    {
                        resultSet = database.execResult("SELECT head,account FROM user WHERE account!=? AND account not in (SELECT Y_account FROM companion WHERE I_account = ?) ", userdata.getAccount(), userdata.getAccount());
                        boolean flag = false;
                        while (resultSet.next())
                        {
                            if (resultSet.getString("account").indexOf(UserName) != -1)
                            {
                                searchFriend.add(resultSet.getString("head"), resultSet.getString("account"), mainWindow);
                                flag = true;
                            }
                        }
                        if (!flag)
                        {
                            alert.setInformation("没有相关结果!");
                            alert.exec();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                return;
            }

        });
    }

    /**
     * oven
     * 修改头像
     */
    public void alterHead()
    {
        ((Button) $(changeInfo, "replace")).setOnAction(event -> headPortrait.show());
    }

    /**
     * oven
     * 修改个人信息
     * */
    public void alterPersonExec()
    {
        ((Button) $(changeInfo, "submit")).setOnAction(event ->
        {
            changeInfo.resetErrorTip();
            //获取读入信息
            String account = ((Label) $(changeInfo, "account")).getText();
            String label = ((TextArea) $(changeInfo, "label")).getText();
            String name = ((TextField) $(changeInfo, "name")).getText();
            String age = ((TextField) $(changeInfo, "age")).getText();
            String address = ((TextField) $(changeInfo, "address")).getText();
            String phone = ((TextField) $(changeInfo, "phone")).getText();
            String sex;

            String nameRegExp = "^[a-z,A-Z]{1,100}$";
            String phoneRegExp = "^[0-9]{11}$";
            String ageRegExp = "^[0-9]{1,3}$";
            RadioButton man = ((RadioButton) $(changeInfo, "man"));

            if (name.equals("") || phone.equals("") || age.equals(""))
            {
                if (name.equals(""))
                {
                    changeInfo.setErrorTip("nameError", "！未输入姓名");
                }
                if (phone.equals(""))
                {
                    changeInfo.setErrorTip("phoneError", "！未输入电话号");
                }
                if (age.equals(""))
                {
                    changeInfo.setErrorTip("ageError", "！未输入年龄");
                }
            }
            else if (!name.matches(nameRegExp) || !phone.matches(phoneRegExp) || !age.matches(ageRegExp))
            {
                if (!name.matches(nameRegExp))
                {
                    changeInfo.setErrorTip("nameError", "！姓名格式错误");
                }
                if (!phone.matches(phoneRegExp))
                {
                    changeInfo.setErrorTip("phoneError", "！电话号格式错误");
                }
                if (!age.matches(ageRegExp))
                {
                    changeInfo.setErrorTip("ageError", "！年龄输入有误,年龄只能是数字");
                }
            }
            else
            {
                if (man.isSelected())
                {
                    sex = "man";
                }
                else
                {
                    sex = "woman";
                }
                try
                {
                    userdata.setPhone(phone);//设置手机号码
                    userdata.setLabel(label);//设置个性签名
                    userdata.setSex(sex);//设置性别
                    userdata.setAddress(address);//设置地址
                    userdata.setName(name);//设置姓名
                    userdata.setAge(Integer.parseInt(age));//设置年龄
                    database.exec("UPDATE user SET label=?, name=?, age=?, address=?, phone=?, sex=?,head=? WHERE account=?", label, name, age, address, phone, sex, userdata.getHead(), account);//更新数据库
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                homepage.setUserData("label", label);
                homepage.setUserData("name", name);
                homepage.setUserData("age", age);
                homepage.setUserData("address", address);
                homepage.setUserData("phone", phone);
                homepage.setUserData("sex", sex);
                setHeadPortrait(((Button) $(homepage, "head")), userdata.getHead());
                setHeadPortrait(((Button) $(homepage, "background")), "head1", userdata.getHead());
                setHeadPortrait(((Button) $(mainWindow, "individual")), userdata.getHead());
                mainWindow.setPersonalInfo(account, name, address, phone);
                changeInfo.close();
            }

        });
        changeInfo.resetErrorTip();
        alterHead();
    }

    /**
     * oven
     * 查找自己的好友
     */
    public void find()
    {
        ((TextField) $(mainWindow, "search")).setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                String text = ((TextField) $(mainWindow, "search")).getText();//获取输入的内容
                int i = MsgData.accountList.indexOf(text);//获取输入的内容在好友账号列表中的位置
                if (i != -1)
                {
                    ((ListView) $(mainWindow, "FriendList")).getSelectionModel().select(i);
                }
                else
                {
                    alert.setInformation("!未查找到该好友");
                    alert.exec();
                }
            }

        });
    }

    /**
     * oven
     * 查看好友信息
     * */

    public void FriendInfo()
    {

        ((Button) $(mainWindow, "moref")).setOnAction(event ->
        {
            int index = mainWindow.friendList.getSelectionModel().getSelectedIndex();
            String account = MsgData.accountList.get(index);
            if (account.equals("SimpleChat聊天助手"))
            {
                //聊天助手没有个人信息捏
            }
            else
            {
                if (friendPage.isShowing())
                {
                    friendPage.close();
                }
                try
                {
                    ResultSet resultSet = database.execResult("SELECT * FROM user WHERE account=?", account);
                    resultSet.next();
                    friendPage.setFriendData(resultSet, ((Label) $(mainWindow, "Y_account")).getText());//设置好友信息
                    friendPage.show();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * oven
     * 保存备注
     */
    public void saveRemark()
    {
        ((Button) $(friendPage, "submit")).setOnAction(event ->
        {
            String remark = ((TextField) $(friendPage, "remark")).getText();
            if (remark.equals(""))
            {
                return;
            }
            int index = MsgData.accountList.indexOf(((Label) $(friendPage, "account")).getText());
            if (index == -1)
            {
                return;
            }
            int index1 = mainWindow.friendList.getSelectionModel().getSelectedIndex();
            if (index == index1)
            {
                mainWindow.friendVector.get(index).setText(remark);
                ((Label) $(mainWindow, "Y_account")).setText(remark);
            }
            else
            {
                mainWindow.friendVector.get(index).setText(remark);
            }
            try
            {
                database.exec("UPDATE companion SET remark=? WHERE I_account=? AND Y_account =?", remark, userdata.getAccount(), ((Label) $(friendPage, "account")).getText());
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        });


    }//

}
