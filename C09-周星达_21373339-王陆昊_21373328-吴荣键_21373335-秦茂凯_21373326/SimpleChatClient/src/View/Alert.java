package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Alert extends Window
{

    public Alert() throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Fxml/Alert.fxml")));
        Scene scene = new Scene(root, 500, 250);
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

    @Override
    public void minimiser()
    {
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
        ((Button) $("minimiser1")).setOnAction(event -> setIconified(true));
    }

    public void exec()
    {
        show();
        ((Button) $("submit")).setOnAction(event -> close());
        ((Button) $("cancel")).setOnAction(event -> close());
    }

    public void setInformation(String Text)
    {
        ((Label) $("information")).setText(Text);
    }

    public void setModality(Window Own)
    {
        initModality(Modality.APPLICATION_MODAL);
        initOwner(Own);
    }
}
