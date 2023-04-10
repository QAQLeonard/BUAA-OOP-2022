package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class Login extends Window
{
    public Login() throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Fxml/Login.fxml")));
        Scene scene = new Scene(root, 450, 480);
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
        ((Button) $("quit1")).setTooltip(new Tooltip("退出"));
        ((Button) $("quit1")).setOnAction(event ->
        {
            close();
            System.exit(0);
        });
    }

    @Override
    public void minimiser()
    {
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
        ((Button) $("minimiser1")).setOnAction(event -> setIconified(true));
    }


    public void setErrorTip(String id, String Text)
    {
        ((Label) $(id)).setText(Text);
    }


    public void resetErrorTip()
    {
        setErrorTip("accountError", "");
        setErrorTip("passwordError", "");
    }

    public void clear()
    {

        ((TextField) $("UserName")).clear();
        ((PasswordField) $("Password")).clear();
    }

    public void clear(String id)
    {

        ((TextField) $(id)).clear();

    }

}
