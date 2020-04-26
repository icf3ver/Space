package com.mygdx.game.space;

import com.mygdx.game.space.Entities.*;
import com.mygdx.game.space.Entities.Area.Gate;
import com.mygdx.game.space.Entities.Area.Planet;
import com.mygdx.game.space.Entities.Area.Station;
import com.mygdx.game.space.Entities.Area.Asteroid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends Thread{
    /*stores SolarSystems on the map*/
    public List<SolarSystem> solarSystem = new ArrayList();

    public void update() {
        //reset system
        solarSystem = new ArrayList();

        //file
        File file = new File("System");

        try {
            //create scanner
            Scanner sc = new Scanner(file);

            //reset variables
            SolarSystem system = null;

            //loop threw file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("-")) {
                    String[] entityInfo = line.split(" ");
                    if (entityInfo[0].equals("-Player")) {
                        system.entities.add(new Player());
                        ((Player) system.entities.get(system.entities.size() - 1)).setName(entityInfo[1]);
                        ((Player) system.entities.get(system.entities.size() - 1)).setMoney(Integer.parseInt(entityInfo[4]));
                        ((Player) system.entities.get(system.entities.size() - 1)).setSystem(solarSystem.size()-1);

                        //could be made into a function
                        system.entities.get(system.entities.size() - 1).setX(Integer.parseInt(entityInfo[2]));
                        system.entities.get(system.entities.size() - 1).setY(Integer.parseInt(entityInfo[3]));
                        system.entities.get(system.entities.size() - 1).setClassType(entityInfo[0].replace("-",""));
                    } else if (entityInfo[0].equals("-Gate")) {
                        system.entities.add(new Gate());
                        ((Gate) system.entities.get(system.entities.size() - 1)).setLocationTo(Integer.parseInt(entityInfo[1]));

                        //could be made into a function
                        system.entities.get(system.entities.size() - 1).setX(Integer.parseInt(entityInfo[2]));
                        system.entities.get(system.entities.size() - 1).setY(Integer.parseInt(entityInfo[3]));
                        system.entities.get(system.entities.size() - 1).setClassType(entityInfo[0].replace("-",""));
                    } else if (entityInfo[0].equals("-Station")) {
                        system.entities.add(new Station());
                        ((Station) system.entities.get(system.entities.size() - 1)).setName(entityInfo[1]);

                        //could be made into a function
                        system.entities.get(system.entities.size() - 1).setX(Integer.parseInt(entityInfo[2]));
                        system.entities.get(system.entities.size() - 1).setY(Integer.parseInt(entityInfo[3]));
                        system.entities.get(system.entities.size() - 1).setClassType(entityInfo[0].replace("-",""));
                    } else if (entityInfo[0].equals("-Planet")) {
                        system.entities.add(new Planet());
                        ((Planet) system.entities.get(system.entities.size() - 1)).setName(entityInfo[1]);
                        ((Planet) system.entities.get(system.entities.size() - 1)).setType(entityInfo[4]);
                        ((Planet) system.entities.get(system.entities.size() - 1)).setSeed(Integer.parseInt(entityInfo[5]));

                        //could be made into a function
                        system.entities.get(system.entities.size() - 1).setX(Integer.parseInt(entityInfo[2]));
                        system.entities.get(system.entities.size() - 1).setY(Integer.parseInt(entityInfo[3]));
                        system.entities.get(system.entities.size() - 1).setClassType(entityInfo[0].replace("-",""));
                        ((Planet) system.entities.get(system.entities.size() - 1)).generate(Integer.parseInt(entityInfo[6]));
                    } else if (entityInfo[0].equals("-Asteroid")) {
                        system.entities.add(new Asteroid());
                        ((Asteroid) system.entities.get(system.entities.size() - 1)).setType(entityInfo[1]);
                        ((Asteroid) system.entities.get(system.entities.size() - 1)).setSeed(Integer.parseInt(entityInfo[4]));
                        //could be made into a function
                        system.entities.get(system.entities.size() - 1).setX(Integer.parseInt(entityInfo[2]));
                        system.entities.get(system.entities.size() - 1).setY(Integer.parseInt(entityInfo[3]));
                        system.entities.get(system.entities.size() - 1).setClassType(entityInfo[0].replace("-",""));
                        ((Asteroid) system.entities.get(system.entities.size() - 1)).generate(Integer.parseInt(entityInfo[5]));
                    }

                } else {
                    solarSystem.add(new SolarSystem());
                    system = Map.this.solarSystem.get(solarSystem.size() - 1);
                    String[] systemInfo = line.split(" ");
                    system.name = systemInfo[1];
                }
            }
            //close scanner
            sc.close();

            //gateAssembly saves the gates destination systems to the gate.
            gateAssembly();
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void gateAssembly(){
        File file = new File("System");

        try {
            Scanner sc = new Scanner(file);
            SolarSystem system = null;
            int i = -1;
            int s = -1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains("-")){
                    i += 1;
                    String[] entityInfo = line.split(" ");
                    if(entityInfo[0].equals("-Gate")){
                        ((Gate)system.entities.get(i)).assemble(this);
                    }
                }else{
                    s += 1;
                    system = Map.this.solarSystem.get(s);
                    i = -1;
                }
            }
            sc.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        }
    }

        public Player getPlayer(String name){

        File file = new File("System");
        int system = -1;
        int positionInSystem = 0;
        try{
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(" ");
                if (data[0].equals("-Player")){
                    if (data[1].equals(name)){
                        return ((Player)((solarSystem.get(system)).entities).get(positionInSystem));
                    }
                }
                if(data[0].contains("-")){
                    positionInSystem += 1;
                }else{
                    positionInSystem = 0;
                    system += 1;
                }
            }
        }catch (java.io.IOException e){
            return null;
        }
        return null;
    }

    public void jumpTo(Player p, Gate g){
        p.setSystem(g.getLocationToNumber());
        SolarSystem oldSystem = solarSystem.get(p.getSystem());
        SolarSystem newSystem = solarSystem.get(g.getLocationToNumber());
        System.out.println(p.getSystem());
        System.out.println(g.getLocationToNumber());
        oldSystem.entities.remove(p);
        newSystem.entities.add(p);
    }
}