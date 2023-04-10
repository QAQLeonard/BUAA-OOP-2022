package View;

import javafx.scene.*;
import javafx.stage.*;


public abstract class Window extends Stage {
    Parent root;
    private double xOffset;
    private double yOffset;

    public void move() {
        root.setOnMousePressed(event -> {


            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            getRoot().setCursor(Cursor.CLOSED_HAND);

        });
        root.setOnMouseDragged(event -> {

            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);


        });
        root.setOnMouseReleased(event -> root.setCursor(Cursor.DEFAULT));
    }


    abstract public void quit();


    abstract public void minimiser();


    public Parent getRoot() {
        return root;
    }


    public Object $(String id) {
        return root.lookup("#" + id);
    }

}