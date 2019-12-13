package com.mygdx.game.space.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Window;

public class StationWindow {
    private int x;
    private int y;
    private boolean visible;
    private Texture type;

    public void setType(Texture type) {
        this.type = type;
    }

    public Texture getType() {
        return type;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void render(){
        Window.batch.draw(type,x,y);
    }
}
