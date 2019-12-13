package com.mygdx.game.space.Entities.Area;

import com.mygdx.game.space.Entities.Group;
import com.mygdx.game.space.Entities.Entity;
import com.mygdx.game.space.OpenSimplexNoise;
import com.mygdx.game.space.Entities.Chunk;
import java.util.List;

public class Asteroid extends Group{
    private String type;
    private int seed = 10394;
    private int size = 40;

    public void generate ( int radius){
        OpenSimplexNoise osnoise = new OpenSimplexNoise(seed);
        for (int i = -1*(size); i < size; i++){
            for (int j = -1*(size); j < size; j++){
                if (((j*j) + (i*i)) < (radius * radius)){
                    super.subEntities.add(new Chunk());
                    ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(11);
                    ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(false);
                    ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setParent(this);
                    super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                    super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                    if (osnoise.eval(i/5f,j/5f) > 0) {
                        super.subEntities.add(new Chunk());
                        ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(3);
                        ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(true);
                        ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setParent(this);
                        super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                        super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                        if (osnoise.eval(i / 5f, j / 5f) < 0.5) {
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(0);
                        } else if (osnoise.eval(i / 5f, j / 5f) < 0.7) {
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(2);
                        }
                    }
                }
            }
        }
    }

    public void setSeed(int s){
        seed = s;
    }

    public String getType (){
        return type;
    }

    public void setType (String t){
        type = t;
    }

    public void remove (Chunk chunk){
        super.subEntities.remove(chunk);
    }
}
