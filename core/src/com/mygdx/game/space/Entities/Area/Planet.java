package com.mygdx.game.space.Entities.Area;

import com.mygdx.game.space.Entities.Chunk;
import com.mygdx.game.space.Entities.Entity;
import com.mygdx.game.space.Entities.Group;
import com.mygdx.game.space.OpenSimplexNoise;

import java.util.List;

public class Planet extends Group {
    private String name = "Planet";
    private String type;
    private boolean inhabitable = false;
    private int diameter = 100;
    private int seed = 10;
    private int size = 40;

    public void generate (int radius){
        OpenSimplexNoise osnoise = new OpenSimplexNoise(seed);
        if (type.equals("Earth")){
            for (int i = -1*(size); i < size; i++){
                for (int j = -1*(size); j < size; j++){
                    if ( ((j*j) + (i*i)) < (radius * radius)) {
                        if (osnoise.eval(i / 5f, j / 5f) > 0) {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(6);
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(false);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                            if (osnoise.eval(i / 5f, j / 5f) < 0.5) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(5);
                            } else if (osnoise.eval(i / 5f, j / 5f) < 0.7) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(2);
                            } else if (osnoise.eval(i / 5f, j / 5f) >= 0.8) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(6);
                            }
                        } else {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(4);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                        }
                    }
                }
            }
        }else if(type.equals("Water")) {
            for (int i = -1*(size); i < size; i++){
                for (int j = -1*(size); j < size; j++){
                    if ( ((j*j) + (i*i)) < (radius * radius)) {
                        if (osnoise.eval(i / 5f, j / 5f) > 0) {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(5);
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(false);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                            if (osnoise.eval(i / 5f, j / 5f) < 0.5) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(4);
                            } else if (osnoise.eval(i / 5f, j / 5f) < 0.7) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(4);
                            } else if (osnoise.eval(i / 5f, j / 5f) >= 0.8) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(5);
                            }
                        } else {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(4);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                        }
                    }
                }
            }
        }else if(type.equals("IronOxideSand")){
            for (int i = -1*(size); i < size; i++){
                for (int j = -1*(size); j < size; j++){
                    if ( ((j*j) + (i*i)) < (radius * radius)) {
                        if (osnoise.eval(i / 5f, j / 5f) > 0) {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(7);
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(false);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                            if (osnoise.eval(i / 5f, j / 5f) < 0.5) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(9);
                            } else if (osnoise.eval(i / 5f, j / 5f) < 0.7) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(8);
                            } else if (osnoise.eval(i / 5f, j / 5f) >= 0.8) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(7);
                            }
                        } else {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(10);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                        }
                    }
                }
            }
        }else if(type.equals("Anne")){
            for (int i = -1*(size); i < size; i++){
                for (int j = -1*(size); j < size; j++){
                    if ( ((j*j) + (i*i)) < (radius * radius)) {
                        if (osnoise.eval(i / 5f, j / 5f) > -0.4) {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(15);
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(false);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                            if (osnoise.eval(i / 5f, j / 5f) < 0.1) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(16);
                            } else if (osnoise.eval(i / 5f, j / 5f) < 0.7) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(17);
                            } else if (osnoise.eval(i / 1f, j / 1f) >= 0.8) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(6);
                            }
                        } else {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(4);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                        }
                    }
                }
            }
        }else if(type.equals("Sun1")){
            for (int i = -1*(size); i < size; i++){
                for (int j = -1*(size); j < size; j++){
                    if ( ((j*j) + (i*i)) < (radius * radius)) {
                        if (osnoise.eval(i / 1f, j / 1f) > -0.4) {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(6);
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setCollidable(false);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                            if (osnoise.eval(i / 1f, j / 1f) < 0.1) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(12);
                            } else if (osnoise.eval(i / 1f, j / 1f) < 0.7) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(14);
                            } else if (osnoise.eval(i / 1f, j / 1f) >= 0.8) {
                                ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(6);
                            }
                        } else {
                            super.subEntities.add(new Chunk());
                            ((Chunk) super.subEntities.get(super.subEntities.size() - 1)).setType(13);
                            super.subEntities.get(super.subEntities.size() - 1).setX((i * 28) + getX() + (radius+4) * 28);
                            super.subEntities.get(super.subEntities.size() - 1).setY((j * 28) + getY() + (radius-4) * 28);
                        }
                    }
                }
            }
        }
    }

    public void setSeed(int s){
        seed = s;
    }

    public List<Entity> getList (){
        return super.subEntities;
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

    public String getName(){
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

}
