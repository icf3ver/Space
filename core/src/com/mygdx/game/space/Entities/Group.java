package com.mygdx.game.space.Entities;
import com.mygdx.game.Window;
import com.mygdx.game.space.GUI.Arrow;

import java.util.List;
import java.util.ArrayList;
import static com.mygdx.game.Window.playerX;
import static com.mygdx.game.Window.playerY;

public class Group extends Entity {
    public List<Entity> subEntities = new ArrayList();
    public Arrow arrow = new Arrow();

    public void render(Player p){
        if (Math.sqrt(Math.pow(this.getX() + (playerX), 2) + Math.pow(this.getY() + (playerY), 2)) <= 1500) {
            for (Entity e : subEntities) {
                if (e.getX() + playerX > -30 && e.getX() + playerX < 1300 && e.getY() + playerY > -30 && e.getY() + playerY < 1000) {
                    Window.batch.draw(((Chunk) e).getTexture(), e.getX() + playerX, e.getY() + playerY);
                }
            }
        }
    }

    @Override
    public void renderArrow() {
        arrow.align(0 ,0 ,playerX, playerY, -getX(), -getY());
    }

    public List<Entity> getList (){
        return subEntities;
    }
}
