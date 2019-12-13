package com.mygdx.game.space;

import com.mygdx.game.space.Entities.*;
import com.mygdx.game.space.Entities.Area.*;
import com.mygdx.game.space.Entities.Area.Asteroid;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    public String name;
    public List<Entity> entities = new ArrayList ();

    public void printPlayersInSystem(){
        for (int i = 0; i < entities.size(); i++){
            if (entities.get(i) instanceof Player){
                System.out.println(((Player)entities.get(i)).getName());
            }
        }
    }

    public void printEntitiesInSystem(){
        for (int i = 0; i < entities.size(); i++){
            if (entities.get(i) instanceof Player){
                System.out.println(((Player)entities.get(i)).getName());
            }else if (entities.get(i) instanceof Gate){
                System.out.println(((Gate)entities.get(i)).getLocationTo());
            }else if (entities.get(i) instanceof Station){
                System.out.println(((Station)entities.get(i)).getName());
            }else if (entities.get(i) instanceof Planet){
                System.out.println(((Planet)entities.get(i)).getName());
            }
        }
    }

    public void printLongRangeEntitiesInSystem(){
        for (int i = 0; i < entities.size(); i++){
            if (entities.get(i) instanceof Gate){
                System.out.println(((Gate)entities.get(i)).getLocationTo());
            }else if (entities.get(i) instanceof Station){
                System.out.println(((Station)entities.get(i)).getName());
            }else if (entities.get(i) instanceof Planet){
                System.out.println(((Planet)entities.get(i)).getName());
            }else if (entities.get(i) instanceof Beacon){
                System.out.println(((Beacon)entities.get(i)).getName());
            }
        }
    }

    public void printCloseEntitiesInSystem(int x,int y){
        for (int i = 0; i < entities.size(); i++){
            if (Math.sqrt((double)(Math.pow(entities.get(i).getX(),2) + Math.pow(entities.get(i).getY(),2))) <= 100.0){
                if (entities.get(i) instanceof Player){
                    System.out.println(((Player)entities.get(i)).getName());
                }else if (entities.get(i) instanceof Gate){
                    System.out.println(((Gate)entities.get(i)).getLocationTo());
                }else if (entities.get(i) instanceof Station){
                    System.out.println(((Station)entities.get(i)).getName());
                }else if (entities.get(i) instanceof Planet){
                    System.out.println(((Planet)entities.get(i)).getName());
                }else if (entities.get(i) instanceof Asteroid){
                    System.out.println(((Asteroid)entities.get(i)).getType());
                }
            }else{
                if (entities.get(i) instanceof Gate){
                    System.out.println(((Gate)entities.get(i)).getLocationTo());
                }else if (entities.get(i) instanceof Station){
                    System.out.println(((Station)entities.get(i)).getName());
                }else if (entities.get(i) instanceof Planet){
                    System.out.println(((Planet)entities.get(i)).getName());
                }
            }
        }
    }
}