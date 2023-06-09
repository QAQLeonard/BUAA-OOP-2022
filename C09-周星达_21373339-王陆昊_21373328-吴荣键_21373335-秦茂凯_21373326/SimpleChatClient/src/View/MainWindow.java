package View;

import Controller.Controller;
import Model.ChatManager;
import Model.DatabaseModel;
import Model.Tool;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

import javafx.scene.control.Button;


public class MainWindow extends Window
{
    public ListView friendList;
    public ListView chatList;
    public Vector<FriendList> friendVector;
    public Vector<ChatListItem> chatVector;
    public ContextMenu contextMenu;
    public Vector<MenuItem> rightItem;
    public double xOffset;
    public double yOffset;

    public MainWindow() throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Fxml/MainWindow.fxml")));
        Scene scene = new Scene(root, 1400, 700);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        setTitle("Simple Chat");
        friendList = ((ListView) $("FriendList"));
        chatList = ((ListView) $("ChatList"));
        friendVector = new Vector<>();
        contextMenu = new ContextMenu();
        rightItem = new Vector<>();
        rightItem.add(new MenuItem("  添加好友  "));
        rightItem.add(new MenuItem("  设置      "));
        rightItem.add(new MenuItem("  最小化    "));
        rightItem.add(new MenuItem("  退出      "));
        move();
        quit();
        minimiser();
        initTooltip();
    }

    public void initTooltip()
    {
        ((Button) $("maximization")).setTooltip(new Tooltip("添加好友"));
        ((Button) $("setting")).setTooltip(new Tooltip("设置"));
        ((Button) $("individual")).setTooltip(new Tooltip("个人资料"));
        ((Button) $("moref")).setTooltip(new Tooltip("好友资料"));
        ((TextField) $("search")).setTooltip(new Tooltip("查找好友"));
        ((Button) $("send")).setTooltip(new Tooltip("发送"));
    }


    @Override
    public void move()
    {
        root.setOnMousePressed(event ->
        {
            contextMenu.getItems().clear();
            if (event.getButton() == MouseButton.SECONDARY)
            {
                rightItem.get(0).setOnAction(event1 -> Controller.searchFriend.show());
                rightItem.get(1).setOnAction(event1 ->
                {
                });
                rightItem.get(2).setOnAction(event1 -> setIconified(true));
                rightItem.get(3).setOnAction(event1 ->
                {
                    close();
                    try
                    {
                        Controller.database.exec("DELETE FROM dialog WHERE account  = ?", Controller.userdata.getAccount());
                        try
                        {
                            ChatManager.getInstance().send("#### " + Controller.userdata.getAccount() + " ####");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    System.exit(0);
                });
                contextMenu.getItems().addAll(rightItem);
                contextMenu.show(this, event.getScreenX(), event.getScreenY());
            }
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            getRoot().setCursor(Cursor.CLOSED_HAND);

        });
        root.setOnMouseDragged(event ->
        {

            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);


        });
        root.setOnMouseReleased(event -> root.setCursor(Cursor.DEFAULT));

    }

    //别人的消息
    public void addLeft(String head, String Msg) throws IllegalStateException
    {
        try
        {
            chatList.getItems().add(new ChatListItem().Left(head, Msg, Tool.getWidth(Msg), Tool.getHight(Msg)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //自己的消息
    public void addRight(String head, String Msg)
    {
        try
        {
            chatList.getItems().add(new ChatListItem().Right(head, Msg, Tool.getWidth(Msg), Tool.getHight(Msg)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setHead(String Head)
    {
        setHeadPortrait(((Button) $("individual")), Head);
    }

    @Override
    public void quit()
    {//退出
        ((Button) $("quit1")).setTooltip(new Tooltip("退出"));
        ((Button) $("quit1")).setOnAction(event ->
        {
            close();
            try
            {
                Controller.database.exec("DELETE FROM dialog WHERE account  = ?", Controller.userdata.getAccount());
                try
                {
                    ChatManager.getInstance().send("#### " + Controller.userdata.getAccount() + " ####");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.exit(0);
        });
    }

    @Override
    public void minimiser()
    {
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
        ((Button) $("minimiser1")).setOnAction(event -> setIconified(true));
    }

    public static void setHeadPortrait(Button button, String head)
    {
        button.setStyle(String.format("-fx-background-image: url('/View/Fxml/CSS/Image/head/%s.jpg')", head));
    }

    public static void setHeadPortrait(Button button, String background, String file)
    {
        button.setStyle(String.format("-fx-background-image: url('/View/Fxml/CSS/Image/%s/%s.jpg')", file, background));
    }


    public void addFriend(String head, String account, String remark, DatabaseModel database, FriendPage friendPage)
    {
        friendVector.add(new FriendList(head, account, remark));
        int index = friendVector.size() - 1;
        friendVector.get(index).setActionForMsgTip();
        friendVector.get(index).setActionForInfo(database, friendPage, account);
        friendVector.get(index).setActionForSendMsg(this, account, Controller.userdata.getHead());
        friendVector.get(index).setActionForClear(this);
        friendVector.get(index).setActionForDelete(database, this, Controller.userdata.getAccount());
        friendList.getItems().add(friendVector.get(friendVector.size() - 1).pane);
    }

    public void addFriend(String head, String account, String remark)
    {
        int index = friendVector.size();
        friendVector.add(new FriendList(head, account, remark));
        friendVector.get(index).setActionForSendMsg(this, account, Controller.userdata.getHead());
        friendVector.get(index).setActionForMsgTip();
        friendList.getItems().add(friendVector.get(friendVector.size() - 1).pane);
    }

    public void setPersonalInfo(String account, String name, String address, String phone)
    {
        ((Label) $("myAccount")).setText(account);
        ((Label) $("myName")).setText(name);
        ((Label) $("myAddress")).setText(address);
        ((Label) $("myPhone")).setText(phone);
    }
}

class ChatListItem
{
    public Pane pane;
    public Button head;
    public TextArea text;
    public Pane left;
    public Pane right;
    public Button arrow;

    public ChatListItem()
    {
        pane = new Pane();
        head = new Button();
        text = new TextArea();
        pane.setPrefSize(730, 150);
        left = new Pane();
        right = new Pane();
        arrow = new Button();
        arrow.setDisable(false);
        arrow.setPrefSize(32, 32);
        left.setPrefSize(580, 70);
        right.setPrefSize(580, 70);
        head.getStyleClass().add("head");
        pane.getStyleClass().add("pane");
        left.getStyleClass().add("pane");
        right.getStyleClass().add("pane");
        head.setPrefSize(50, 50);
        text.setPrefSize(480, 50);
        text.setWrapText(true);
        text.setEditable(false);
    }

    public Pane Left(String ihead, String itext, double width, double height)
    {//别人的消息
        text.getStyleClass().add("lefttext");
        arrow.getStyleClass().add("leftarrow");
        pane.setPrefHeight(110 + height);
        left.setPrefHeight(30 + height);
        head.setLayoutY(10);
        head.setLayoutX(10);
        text.setPrefSize(width, height);
        text.setLayoutX(100);
        text.setLayoutY(30);
        arrow.setLayoutY(40);
        arrow.setLayoutX(85);
        text.setText(itext);
        MainWindow.setHeadPortrait(head, ihead);
        left.getChildren().add(head);
        left.getChildren().add(text);
        left.getChildren().add(arrow);
        pane.getChildren().add(left);

        return pane;


    }

    public Pane Right(String ihead, String itext, double width, double height)
    {//自己的消息
        text.getStyleClass().add("righttext");
        arrow.getStyleClass().add("rightarrow");
        head.setLayoutY(10);
        head.setLayoutX(510);
        pane.setPrefHeight(110 + height);
        right.setPrefHeight(30 + height);
        text.setPrefSize(width, height);
        text.setLayoutY(30);
        text.setLayoutX(480 - width);
        arrow.setLayoutY(40);
        arrow.setLayoutX(475);
        text.setText(itext);
        MainWindow.setHeadPortrait(head, ihead);
        right.getChildren().add(head);
        right.getChildren().add(text);
        right.getChildren().add(left);
        right.getChildren().add(arrow);
        right.setLayoutX(150);
        pane.getChildren().add(right);
        return pane;

    }

}


