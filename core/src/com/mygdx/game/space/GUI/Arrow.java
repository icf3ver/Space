package com.mygdx.game.space.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Window;

public class Arrow {
    private double rotation;
    private int xPosition;
    private int yPosition;
    private static Texture texture = new Texture(Gdx.files.absolute("assets/arrow.png"));

    public void align(int screenH, int screenW, int xP, int yP, int xO, int yO){
        int i = screenH - 100;
        int j = screenW - 100;
        double xPlayer = (double) xP;
        double yPlayer = (double) yP;
        double xObject = (double) xO;
        double yObject = (double) yO;
        if (xPlayer - xObject == 0){
            xPlayer+=1;
        }

        if(yPlayer - yObject == 0){
            yPlayer +=1;
        }
        if (xPlayer - xObject >= 0){
            if (yPlayer - yObject >= 0){
                rotation = (Math.toDegrees(Math.atan((xPlayer-xObject)/(yPlayer-yObject)))-90)*-1;
            }else{
                rotation = Math.toDegrees(Math.atan((yPlayer-yObject)/(xPlayer-xObject)));
            }

        }else{
            if (yPlayer - yObject >= 0){
                rotation = (Math.toDegrees(Math.atan((xPlayer-xObject)/(yPlayer-yObject)))-90)*-1;
            }else{
                rotation = (Math.toDegrees(Math.atan((xPlayer-xObject)/(yPlayer-yObject))) + 90)*-1;
            }
        }
        Window.batch.draw(new TextureRegion(texture), 650-250, 450-250,250,250,500,500,1,1,(float)rotation);
    }
}
