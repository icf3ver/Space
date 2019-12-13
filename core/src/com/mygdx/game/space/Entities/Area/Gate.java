package com.mygdx.game.space.Entities.Area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.space.Entities.Entity;
import com.mygdx.game.space.Map;
import com.mygdx.game.space.SolarSystem;
import com.mygdx.game.space.GUI.Arrow;

//system
public class Gate extends Entity {
    private SolarSystem system = null;
    private int LocationStorage = 0;
    private boolean inRange = false;

    public Gate(){
        newTexture = new Texture(Gdx.files.absolute("assets/Gate.png"));
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

    public String getLocationTo(){
        return "Gate to " + system.name;
    }

    public void setLocationTo(int s){
        LocationStorage = s;
    }

    public int getLocationToNumber(){
        return LocationStorage;
    }

    public void assemble(Map map){
        system = map.solarSystem.get(LocationStorage);
    }
}
