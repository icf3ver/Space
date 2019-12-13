package com.mygdx.game.space.Entities.Area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.space.Entities.Entity;
import com.mygdx.game.space.GUI.Arrow;

public class Station extends Entity {
    private String name;
    private boolean inRange = false;

    public Station(){
        newTexture = new Texture(Gdx.files.absolute("assets/Station.png"));
        arrow = new Arrow();
    }

    public void inRange(){
        this.inRange = true;
    }

    public void notInRange(){
        this.inRange = false;
    }

    public boolean isInRange() {
        return inRange;
    }

    public String getName(){
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }
}
