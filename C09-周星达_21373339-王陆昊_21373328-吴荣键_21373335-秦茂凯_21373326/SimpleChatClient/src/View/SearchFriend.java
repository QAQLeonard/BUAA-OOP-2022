package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

public class SearchFriend extends Window
{
    public Vector<ListItem> items;
    public ListView friendList;
    public static Vector<String> friendVector;

    public SearchFriend() throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Fxml/SearchFriend.fxml")));
        Scene scene = new Scene(root, 600, 350);
        friendList = ((ListView) $("friendList"));
        ((TextField) $("textInput")).setTooltip(new Tooltip("输入账号，回车查询"));
        items = new Vector<>();
        friendVector = new Vector<>();
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        setTitle("Simple Chat");
        move();
        quit();
        minimiser();
    }

    @Override
    public void quit()
    {
        ((Button) $("quit1")).setTooltip(new Tooltip("关闭"));
        ((Button) $("quit1")).setOnAction(event -> close());
    }

    public void add(String head, String account, MainWindow mainWindow)
    {
        items.add(new ListItem(head, account));
        int index = items.size() - 1;
        items.get(index).setActionForAdd(mainWindow);
        friendVector.add(items.get(index).information.getText());
        friendList.getItems().add(items.get(index).pane);
    }

    @Override
    public void minimiser()
    {
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
        ((Button) $("minimiser1")).setOnAction(event -> setIconified(true));
    }

    public void clear()
    {
        friendList.getItems().clear();
        friendVector.clear();
    }



}