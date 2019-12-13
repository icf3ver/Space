package com.mygdx.game.space.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Window;

public class Crosshair {
    private static Texture crosshair = new Texture(Gdx.files.absolute("assets/crosshair.png"));

    public static void render(){
        Window.batch.draw(crosshair, Gdx.input.getX()-15, (Gdx.input.getY()-800+15)*-1);
    }
}
