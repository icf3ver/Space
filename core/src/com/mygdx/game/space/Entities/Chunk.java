package com.mygdx.game.space.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.space.Entities.Area.Asteroid;
import com.mygdx.game.space.Entities.Entity;

public class Chunk extends Entity {
    private Asteroid parent;
    private Texture texture;
    private boolean collidable;
    public static Texture [] textures = {
            new Texture(Gdx.files.absolute("assets/common.png")),
            new Texture(Gdx.files.absolute("assets/dense.png")),
            new Texture(Gdx.files.absolute("assets/ORE.png")),
            new Texture(Gdx.files.absolute("assets/watter.png")),
            new Texture(Gdx.files.absolute("assets/forest.png")),
            new Texture(Gdx.files.absolute("assets/snow.png")),
            new Texture(Gdx.files.absolute("assets/redSand1.png")),
            new Texture(Gdx.files.absolute("assets/redSand2.png")),
            new Texture(Gdx.files.absolute("assets/redSand3.png")),
            new Texture(Gdx.files.absolute("assets/redSand4.png")),
            new Texture(Gdx.files.absolute("assets/AsteroidBack.png")),
            new Texture(Gdx.files.absolute("assets/SunRed.png")),
            new Texture(Gdx.files.absolute("assets/SunRed2.png")),
            new Texture(Gdx.files.absolute("assets/SunYellow.png")),
            new Texture(Gdx.files.absolute("assets/AnneColor1.png")),
            new Texture(Gdx.files.absolute("assets/AnneColor2.png")),
            new Texture(Gdx.files.absolute("assets/AnneColor3.png")),
    };
    //private Texture Type;

    public void setType (int i){
        switch(i){
            case 0 :
                texture = textures[0];
                break;
            case 1 :
                texture = textures[0];
                break;
            case 2 :
                texture = textures[1];
                break;
            case 3 :
                texture = textures[2];
                break;
            case 4 :
                texture = textures[3];
                break;
            case 5 :
                texture = textures[4];
                break;
            case 6 :
                texture = textures[5];
                break;
            case 7 :
                texture = textures[6];
                break;
            case 8 :
                texture = textures[7];
                break;
            case 9 :
                texture = textures[8];
                break;
            case 10 :
                texture = textures[9];
                break;
            case 11 :
                texture = textures[10];
                break;
            case 12:
                texture = textures[11];
                break;
            case 13:
                texture = textures[12];
                break;
            case 14:
                texture = textures[13];
                break;
            case 15:
                texture = textures[14];
                break;
            case 16:
                texture = textures[15];
                break;
            case 17:
                texture = textures[16];
                break;
            default :
                texture = textures[0];
                break;
        }
    }
    public void setCollidable (boolean c){
        collidable = c;
    }

    public boolean getCollidable () {
        return collidable;
    }

    public Texture getTexture (){
        return texture;
    }

    public void setParent(Asteroid parent) {
        this.parent = parent;
    }

    public Asteroid getParent() {
        return parent;
    }

    public void remove() {
        parent.remove(this);
    }
}