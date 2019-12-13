package com.mygdx.game.space.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Window;
import com.mygdx.game.space.Map;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Window.playerX;
import static com.mygdx.game.Window.playerY;

/**
 * This class is a ship!
 *
 * @author 22balmerl
 */

public class Ship {

    class ShipPart {
        private int x;
        private int y;
        private String type;
        private Texture texture;

        public ShipPart(Texture texture, String type, int x, int y){
            this.texture = texture;
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setTexture(Texture t) {
            this.texture = t;
        }

        public Texture getTexture() {
            return texture;
        }
    }

    private static Texture[] sp = new Texture[24];

    private List<ShipPart> part = new ArrayList();

    public static void init() {
        for (int i = 1; i < 17; i++) {
            sp[i-1] = new Texture(Gdx.files.absolute("assets/ShipPart" + i + ".png"));
        }
        sp[16] = new Texture(Gdx.files.absolute("assets/drill1.png"));
        sp[17] = new Texture(Gdx.files.absolute("assets/drill2.png"));
        sp[18] = new Texture(Gdx.files.absolute("assets/drill3.png"));
        sp[19] = new Texture(Gdx.files.absolute("assets/drill4.png"));

        sp[20] = new Texture(Gdx.files.absolute("assets/Cannon1.png"));
        sp[21] = new Texture(Gdx.files.absolute("assets/Cannon2.png"));
        sp[22] = new Texture(Gdx.files.absolute("assets/Cannon3.png"));
        sp[23] = new Texture(Gdx.files.absolute("assets/Cannon4.png"));

    }

    public void shoot(int mX, int mY, Player player){
        for(int i = 0;i < part.size(); i++){
            if (part.get(i).getType().equals("cannon"))
            player.bullets.add(new Bullet( -playerX + 638 + 28 * (part.get(i)).getX(), -playerY + 436 + 28 * (part.get(i)).getY(),(mX-652)/10,-((mY - 350)/10)));
        }
    }

    public void addShipPart(int x, int y, String type) {
        if (x < 10 && y < 10 && x > -10 && y > -10){
            if (type.equals("expand")) {
                updatePrimary(x, y);
            } else if (type.equals("drill")) {
                orient(x, y, type);
            } else if (type.equals("cannon")) {
                orient(x, y, type);
            }
        }
    }

    private void orient(int x, int y, String type){
        int change = 0;

        if (type.equals("drill")){
            change = 0;
        }else if(type.equals("cannon")){
            change = 4;
        }else{
            return;
        }

        for (ShipPart p: part){
            if (p.getX() == x && p.getY() == y){
                return;
            }
        }

        for (ShipPart p: part){
            if(p.getX() == x && p.getY() == y) {
                break;
            }else if (p.getType().equals("expand")) {
                if (p.getX() == x - 1 && p.getY() == y) {
                    part.add(new ShipPart(sp[17+ change], type, x, y));
                    break;
                } else if (p.getX() == x + 1 && p.getY() == y) {
                    part.add(new ShipPart(sp[19+ change], type, x, y));
                    break;
                } else if (p.getX() == x && p.getY() == y + 1) {
                    part.add(new ShipPart(sp[16+ change], type, x, y));
                    break;
                } else if (p.getX() == x && p.getY() == y - 1) {
                    part.add(new ShipPart(sp[18+ change], type, x, y));
                    break;
                }
            }
        }
    }

    public Ship() {
        part.add(new ShipPart(sp[0], "expand", 0,0));
    }

    private void updatePrimary(int x, int y){
        boolean r = false;
        boolean l = false;
        boolean u = false;
        boolean d = false;
        ShipPart rp = null;
        ShipPart lp = null;
        ShipPart up = null;
        ShipPart dp = null;
        for (ShipPart p : part){
            if(p.getX() == x && p.getY() == y) {
                r = false;
                l = false;
                u = false;
                d = false;
                rp = null;
                lp = null;
                up = null;
                dp = null;
                break;
            }else if (p.getType().equals("expand")){
                if (p.getX() == x-1 && p.getY() == y){
                    l = true;
                    lp = p;
                }else if (p.getX() == x+1 && p.getY() == y){
                    r = true;
                    rp = p;
                }else if (p.getX() == x && p.getY() == y+1){
                    u = true;
                    up = p;
                }else if (p.getX() == x && p.getY() == y-1){
                    d = true;
                    dp = p;
                }
            }
        }
        if(u){
            if(d){
                if(l){
                    if (r){
                        part.add(new ShipPart(sp[16-1], "expand", x, y));
                        updateSecondary(up);
                        updateSecondary(dp);
                        updateSecondary(lp);
                        updateSecondary(rp);
                    }else{
                        part.add(new ShipPart(sp[13-1], "expand", x, y));
                        updateSecondary(up);
                        updateSecondary(dp);
                        updateSecondary(lp);
                    }
                }else if (r){
                    part.add(new ShipPart(sp[15-1], "expand", x, y));
                    updateSecondary(up);
                    updateSecondary(dp);
                    updateSecondary(rp);
                }else{
                    part.add(new ShipPart(sp[11-1], "expand", x, y));
                    updateSecondary(up);
                    updateSecondary(dp);
                }
            }else if(l){
                if (r){
                    part.add(new ShipPart(sp[12-1], "expand", x, y));
                    updateSecondary(up);
                    updateSecondary(lp);
                    updateSecondary(rp);
                }else{
                    part.add(new ShipPart(sp[9-1], "expand", x, y));
                    updateSecondary(up);
                    updateSecondary(lp);
                }
            }else if(r){
                part.add(new ShipPart(sp[8-1], "expand", x, y));
                updateSecondary(up);
                updateSecondary(rp);
            }else{
                part.add(new ShipPart(sp[4-1],"expand",  x, y));
                updateSecondary(up);
            }
        }else if(d){
            if(l){
                if(r){
                    part.add(new ShipPart(sp[14-1], "expand", x, y));
                    updateSecondary(dp);
                    updateSecondary(lp);
                    updateSecondary(rp);
                }else{
                    part.add(new ShipPart(sp[6-1], "expand", x, y));
                    updateSecondary(dp);
                    updateSecondary(lp);
                }
            }else if(r){
                part.add(new ShipPart(sp[7-1], "expand", x, y));
                updateSecondary(dp);
                updateSecondary(rp);
            }else{
                part.add(new ShipPart(sp[2-1], "expand", x, y));
                updateSecondary(dp);
            }
        }else if(l){
            if(r){
                part.add(new ShipPart(sp[10-1], "expand", x, y));
                updateSecondary(lp);
                updateSecondary(rp);
            }else{
                part.add(new ShipPart(sp[5-1], "expand", x, y));
                updateSecondary(lp);
            }
        }else if(r){
            part.add(new ShipPart(sp[3-1], "expand", x, y));
            updateSecondary(rp);
        }
    }

    private void updateSecondary(ShipPart p){
        int x = p.getX();
        int y = p.getY();
        boolean r = false;
        boolean l = false;
        boolean u = false;
        boolean d = false;
        for (ShipPart pn : part){
            if (pn.getType().equals("expand")){
                if (pn.getX() == x-1 && pn.getY() == y){
                    l = true;
                }else if (pn.getX() == x+1 && pn.getY() == y){
                    r = true;
                }else if (pn.getX() == x && pn.getY() == y+1){
                    u = true;
                }else if (pn.getX() == x && pn.getY() == y-1){
                    d = true;
                }
            }
        }
        if(u){
            if(d){
                if(l){
                    if (r){
                        p.setTexture(sp[16-1]);
                    }else{
                        p.setTexture(sp[13-1]);
                    }
                }else if (r){
                    p.setTexture(sp[15-1]);
                }else{
                    p.setTexture(sp[11-1]);
                }
            }else if(l){
                if (r){
                    p.setTexture(sp[12-1]);
                }else{
                    p.setTexture(sp[9-1]);
                }
            }else if(r){
                p.setTexture(sp[8-1]);
            }else{
                p.setTexture(sp[4-1]);
            }
        }else if(d){
            if(l){
                if(r){
                    p.setTexture(sp[14-1]);
                }else{
                    p.setTexture(sp[6-1]);
                }
            }else if(r){
                p.setTexture(sp[7-1]);
            }else{
                p.setTexture(sp[2-1]);
            }
        }else if(l){
            if(r){
                p.setTexture(sp[10-1]);
            }else{
                p.setTexture(sp[5-1]);
            }
        }else if(r){
            p.setTexture(sp[3-1]);
        }
    }

    public boolean collisionDetection(Map map, int x, int y, double velocityX, double velocityY, Player player){
        boolean hit = false;
        for (ShipPart p : part){
            for (int i = 0; map.solarSystem.get(player.getSystem()).entities.size() > i; i++) {
                try {
                    Group obj = ((Group) map.solarSystem.get(player.getSystem()).entities.get(i));
                    if (Math.sqrt(Math.pow(obj.getX() + (x + 2 * velocityX), 2) + Math.pow(obj.getY() + (y + 2 * velocityY), 2)) <= 1000) {
                        List<Entity> entities = obj.getList();
                        for (Entity e : entities) {
                            // is it collidable
                            if (((Chunk) e).getCollidable()) {
                                if (e.getX() + (x-(p.getX()*28) + 2 * velocityX) <= 650 + 14 && e.getX() + (x-(p.getX()*28)+(p.getX()*28) + 2 * velocityX) >= 650) {
                                    if (e.getY() + (y-(p.getY()*28) + 2 * velocityY) <= 450 + 14 && e.getY() + (y-(p.getY()*28) + 2 * velocityY) >= 450 - 40) {
                                        if (velocityX < 0) {
                                            if (p.getType().equals("drill")){
                                                ((Chunk) e).remove();
                                            }
                                            hit = true;
                                        }
                                    }
                                }
                                if (e.getX() + (x-(p.getX()*28) + 2 * velocityX) <= 650 && e.getX() + (x-(p.getX()*28) + 2 * velocityX) >= 650 - 40) {
                                    if (e.getY() + (y-(p.getY()*28) + 2 * velocityY) <= 450 + 14 && e.getY() + (y-(p.getY()*28) + 2 * velocityY) >= 450 - 40) {
                                        if (velocityX > 0) {
                                            if (p.getType().equals("drill")){
                                                ((Chunk) e).remove();
                                            }
                                            hit = true;
                                        }
                                    }
                                }
                                if (e.getY() + (y-(p.getY()*28) + 2 * velocityY) <= 450 && e.getY() + (y-(p.getY()*28) + 2 * velocityY) >= 450 - 38) {
                                    if (e.getX() + (x-(p.getX()*28) + 2 * velocityX) <= 650 + 14 && e.getX() + (x-(p.getX()*28) + 2 * velocityX) >= 650 - 40) {
                                        if (velocityY > 0) {
                                            if (p.getType().equals("drill")){
                                                ((Chunk) e).remove();
                                            }
                                            hit = true;
                                        }
                                    }
                                }
                                if (e.getY() + (y-(p.getY()*28) + 2 * velocityY) <= 450 + 14 && e.getY() + (y-(p.getY()*28) + 2 * velocityY) >= 450) {
                                    if (e.getX() + (x-(p.getX()*28) + 2 * velocityX) <= 650 + 14 && e.getX() + (x-(p.getX()*28) + 2 * velocityX) >= 650 - 40) {
                                        if (velocityY < 0) {
                                            if (p.getType().equals("drill")){
                                                ((Chunk) e).remove();
                                            }
                                            hit = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    //null
                }
            }
        }
        return hit;
    }

    public void render(){
        for (int i = 0; i < part.size();i++){
            ShipPart p = part.get(i);
            Window.batch.draw(p.getTexture(),  (386+(p.getX()*28))+(28*9), (128+(p.getY()*28)) + (28*11));
        }
    }
}
