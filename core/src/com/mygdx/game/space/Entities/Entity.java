package com.mygdx.game.space.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Window;
import com.mygdx.game.space.GUI.Arrow;

import static com.mygdx.game.Window.playerX;
import static com.mygdx.game.Window.playerY;

public class Entity {
    private int x;
    private int y;
    private String typeClass;

    protected Texture newTexture = null;
    protected Arrow arrow = new Arrow();

    public String getClassType() {
        return typeClass;
    }

    public void setClassType(String typeClass) {
        this.typeClass = typeClass;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public void render(){
        if (newTexture != null){
            Window.batch.draw(newTexture, (x - 28*8) + playerX, (y - 28*8) + playerY);
        }
    }
    public void renderArrow(){
        arrow.align(0, 0, playerX, playerY, -x + (650 - 15), -y + (450 - 15));
    }
}
