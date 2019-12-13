package com.mygdx.game.space.Entities.Area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.space.Entities.Entity;
import com.mygdx.game.space.GUI.Arrow;

public class Beacon extends Entity {
    private String name = "Beacon";

    public Beacon(){
        newTexture = new Texture(Gdx.files.absolute("assets/Beacon.png"));
        arrow = new Arrow();
    }

    public String getName(){
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }
}