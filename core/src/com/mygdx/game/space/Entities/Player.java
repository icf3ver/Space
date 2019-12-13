package com.mygdx.game.space.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Window;
import com.mygdx.game.space.Entities.Area.Asteroid;
import com.mygdx.game.space.Entities.Area.Gate;
import com.mygdx.game.space.Entities.Area.Planet;
import com.mygdx.game.space.Entities.Area.Station;
import com.mygdx.game.space.Entities.Stuff.Stuff;
import com.mygdx.game.space.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import static com.mygdx.game.Window.playerX;
import static com.mygdx.game.Window.playerY;

public class Player extends Entity
{
    private String name = "Guest";
    private int money = 0;
    private int i = 0;
    private Ship ship = new Ship();
    private List <Stuff> stuff= new ArrayList ();
    private BitmapFont font = new BitmapFont();
    private int system;
    public List <Bullet> bullets = new ArrayList ();

    public void shoot (int mX, int mY){
        ship.shoot(mX, mY, this);
    }

    public String getName(){
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int m) {
        this.money = m;
    }

    private int getNumberValue(String type){
        HashMap<Integer, String> types = new HashMap<Integer, String>();
        types.put(0, "Player   ");
        types.put(1, "Planet   ");
        types.put(2, "Asteroid ");
        types.put(3, "Station  ");
        types.put(4, "Gate     ");
        types.put(5, "unknown  ");

        for (Integer e : types.keySet()) {
            String a = types.get(e).replace(" ", "");
            if (a.equals(type)){
                return e;
            }
        }
        return 5;
    }

    private String getStringValue(int type){
        HashMap<Integer, String> types = new HashMap<Integer, String>();
        types.put(0, "Player");
        types.put(1, "Planet");
        types.put(2, "Asteroid");
        types.put(3, "Station");
        types.put(4, "Gate");
        types.put(5, "unknown");

        return types.get(type);
    }

    private Color getColorValue(int type){
        HashMap<Integer, Color> types = new HashMap<Integer, Color>();
        types.put(0, Color.RED);
        types.put(1, Color.BLUE);
        types.put(2, Color.BLUE);
        types.put(3, Color.BLUE);
        types.put(4, Color.BLUE);
        types.put(5, Color.RED);

        return types.get(type);
    }

    private String distanceCleaner(int d){
        if(d >= 9.461 * Math.pow(10, 15)){
            return (d / (9.461 * Math.pow(10, 15))) + "ly";
        }else if (d >= 1000){
            return (d / 1000) + "km";
        }else{
            return d + "m";
        }
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public void renderShip(){
        ship.render();
    }

    public void addPart(int x,int y, String type){
        ship.addShipPart(x, y, type);
    }

    public void render (Map map, double velocityX, double velocityY, SpriteBatch batch){
        double[][] distancesToObjects = new double[map.solarSystem.get(system).entities.size()][2];

        super.setX(super.getX()+(int)(2*velocityX));
        super.setY(super.getY()+(int)(2*velocityY));

        for (int i = 0; map.solarSystem.get(system).entities.size() > i; i++){
            Entity obj = (map.solarSystem.get(system).entities.get(i));
            if(obj instanceof Group){
                Group gobj = (Group)obj;
                gobj.render(this);
                if (Math.sqrt(Math.pow(obj.getX() + (super.getX() + 2 * velocityX), 2) + Math.pow(obj.getY() + (super.getY() + 2 * velocityY), 2)) <= 1000) {
                    List<Entity> entities = gobj.getList();
                    boolean slow = true;
                    while(slow) {
                        slow = ship.collisionDetection(map, super.getX(), super.getY(), velocityX, velocityY, this);
                        if (slow){
                            velocityX = 0.5 * velocityX;
                            velocityY = 0.5 * velocityX;
                        }
                    }
                }
                distancesToObjects[i][1] = getNumberValue(obj.getClassType());
                distancesToObjects[i][0] = (int) Math.sqrt(Math.pow(obj.getX() + (super.getX() + 2 * velocityX), 2) + Math.pow(obj.getY() + (super.getY() + 2 * velocityY), 2));
            }else{
                if (Math.sqrt(Math.pow(obj.getX() + (super.getX() + 2 * velocityX), 2) + Math.pow(obj.getY() + (super.getY() + 2 * velocityY), 2)) <= 2000) {
                    obj.render();
                }
                if (obj instanceof Gate) {
                    if (Math.sqrt(Math.pow(obj.getX() + super.getX()-(650-15), 2) + Math.pow(obj.getY() + super.getY()-(450-15), 2)) <= 200) {
                        ((Gate) obj).inRange();
                    } else {
                        ((Gate) obj).notInRange();
                    }
                }
                if (obj instanceof Station) {
                    if (Math.sqrt(Math.pow(obj.getX() + super.getX()-(650-15), 2) + Math.pow(obj.getY() + super.getY()-(450-15), 2)) <= 200) {
                        ((Station) obj).inRange();
                    } else {
                        ((Station) obj).notInRange();
                    }
                }
                distancesToObjects[i][1] = getNumberValue(obj.getClassType());
                distancesToObjects[i][0] = (int) Math.sqrt(Math.pow(obj.getX() + (super.getX() + 2 * velocityX), 2) + Math.pow(obj.getY() + (super.getY() + 2 * velocityY), 2));
            }
        }

        for (int i = 0; map.solarSystem.get(system).entities.size() > i; i++){
            Entity obj = (map.solarSystem.get(system).entities.get(i));
            obj.renderArrow();
        }

        java.util.Arrays.sort(distancesToObjects, new java.util.Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        Window.font.setColor(Color.BLUE);
        Window.font.draw(batch, "Type", 1100, 780);
        Window.font.draw(batch, "Distance", 1200, 780);
        for (int i = 0; i < distancesToObjects.length; i++){
            Window.font.setColor(getColorValue((int)distancesToObjects[i][1]));
            Window.font.draw(batch, getStringValue((int)distancesToObjects[i][1]) + "", 1100, 760-(20*i));
            Window.font.draw(batch, distanceCleaner((int)distancesToObjects[i][0]) + "", 1200, 760-(20*i));
        }

        for (int i = 0; map.solarSystem.get(system).entities.size() > i; i++){
            Entity obj = (map.solarSystem.get(system).entities.get(i));
            if(obj instanceof Gate){
                if(((Gate)obj).isInRange()){
                    Window.font.setColor(Color.BLUE);
                    Window.font.draw(batch,  "Enter gate: X", (obj.getX() ) + playerX, (obj.getY() + 28*6) + playerY);
                }
            }
            if (obj instanceof Station){
                if(((Station)obj).isInRange()){
                    Window.font.setColor(Color.BLUE);
                    Window.font.draw(batch,  "Enter station: X", (obj.getX() ) + playerX, (obj.getY() + 28*6) + playerY);
                }
            }
        }

        renderShip();
        Window.setPlayerXY(super.getX(), super.getY(), velocityX, velocityY);

        for(int i = 0; i<bullets.size();i++){
            bullets.get(i).render();
            bullets.get(i).update();
            if (bullets.get(i).age()){
                bullets.remove(i);
            }
        }
    }
}
