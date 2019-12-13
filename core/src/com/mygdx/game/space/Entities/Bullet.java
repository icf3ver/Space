package com.mygdx.game.space.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Window;

import static com.mygdx.game.Window.playerX;
import static com.mygdx.game.Window.playerY;

public class Bullet extends Entity{
    float xPosition;
    float yPosition;
    float changeX;
    float changeY;
    int age;
    static Texture texture = new Texture("assets/cannonBall.png");

    public Bullet(float x, float y,float cX, float cY){
        changeX = cX;
        changeY = cY;
        xPosition = x;
        yPosition = y;
    }

    public void update (){
        xPosition += changeX;
        yPosition += changeY;
        age +=1;
    }

    public boolean age(){
        if (age > 100){
            return true;
        }
        return false;
    }

    @Override
    public void render() {
        Window.batch.draw(texture, xPosition + playerX, yPosition + playerY);
    }
}