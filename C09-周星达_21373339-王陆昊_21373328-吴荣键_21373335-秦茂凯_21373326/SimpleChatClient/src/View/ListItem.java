package View;

import Controller.Controller;
import Model.ChatManager;
import Model.Data.MsgData;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListItem{
    public Button head;
    public Label information;
    public Button friend_add;
    public Pane pane;
    public String friendHead;
    ListItem(String ihead,String iaccount){
        head = new Button();
        friend_add = new Button();
        information = new Label();
        pane = new Pane();
        pane.getChildren().add(head);
        pane.getChildren().add(information);
        pane.setPrefSize(535,60);
        head.setPrefSize(56,56);
        friend_add.setPrefSize(32,32);
        friend_add.getStyleClass().add("add");
        friend_add.setLayoutX(503);
        friend_add.setLayoutY(14);
        friend_add.setTooltip(new Tooltip("添加好友"));
        pane.getChildren().add(friend_add);
        head.setLayoutX(2);
        head.setLayoutY(2);
        information.setPrefSize(200,32);
        information.setLayoutX(65);
        information.setLayoutY(5);
        head.getStyleClass().add("head");
        information.getStyleClass().add("information");
        pane.getStyleClass().add("ListItem");
        setText(iaccount);
        setHead(ihead);
    }
    public void setText(String text){
        information.setText(text);
    }
    public void setHead(String head)
    {
        setHeadPortrait(this.head,head);
        friendHead = head;
    }
    public void setHeadPortrait(Button button, String head){
        button.setStyle(String.format("-fx-background-image: url('/View/Fxml/CSS/Image/head/%s.jpg')",head));
    }


    public void setActionForAdd(MainWindow mainWindow){
        friend_add.setOnAction(event -> {
                String friendName = information.getText();
                int index =  SearchFriend.friendVector.indexOf(friendName);
                if(index!=-1)
                {
                    Controller.searchFriend.friendList.getItems().remove(index);
                    SearchFriend.friendVector.remove(index);
                }
                //加消息
                MsgData.msg.add(new Vector<>());
                MsgData.accountList.add(friendName);
                MsgData.msgTip.put(friendName,0);
            try {
                try {
                    ChatManager.getInstance().send("###@ " + Controller.userdata.getAccount() + " " + friendName);
                } catch (Exception e) {
                    Controller.alert.setInformation("!添加好友失败");
                    Controller.alert.exec();
                    e.printStackTrace();
                }
                Controller.database.exec("INSERT INTO companion VALUES(?,?,?)", Controller.userdata.getAccount(), friendName, friendName);
                Controller.database.exec("INSERT INTO companion VALUES(?,?,?)", friendName, Controller.userdata.getAccount(), Controller.userdata.getAccount());
                mainWindow.addFriend(friendHead, friendName,friendName, Controller.database, Controller.friendPage);
                //是否在线
                ResultSet resultSet = Controller.database.execResult("SELECT * FROM dialog WHERE account=?", friendName);
                if (resultSet.next())
                    mainWindow.friendVector.get(MsgData.accountList.size()-1).setOnline();
                else
                    mainWindow.friendVector.get(MsgData.accountList.size()-1).setOutline();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


    }

}
