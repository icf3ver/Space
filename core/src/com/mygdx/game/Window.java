package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.game.space.Entities.Area.Gate;
import com.mygdx.game.space.Entities.Area.Station;
import com.mygdx.game.space.Entities.Entity;
import com.mygdx.game.space.Entities.Player;
import com.mygdx.game.space.Entities.Ship;
import com.mygdx.game.space.GUI.Crosshair;
import com.mygdx.game.space.GUI.StationWindow;
import com.mygdx.game.space.Map;


public class Window extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static BitmapFont font;
	public static Map map;
	private static Player player;
	private static StationWindow mainWindow;
	private static StationWindow marketWindow;
	private static StationWindow repairWindow;
	private static StationWindow inventoryWindow;
	private static StationWindow skillsWindow;

	private static Texture goldTech;
	private static Texture expansion;

	private static double velocityX;
	private static double velocityY;
	public static int playerX;
	public static int playerY;
	private static int sand;
	public static boolean stationView;
	public static boolean partDrag;
	public static String dragging;
	public static Crosshair crosshair;

	@Override
	public void create () {
		Ship.init();

		batch = new SpriteBatch();
		font = new BitmapFont();
		map = new Map();
		mainWindow = new StationWindow();
		marketWindow = new StationWindow();
		inventoryWindow = new StationWindow();

		goldTech = new Texture(Gdx.files.absolute("assets/GoldTech.png"));
		expansion = new Texture(Gdx.files.absolute("assets/ShipPart1.png"));

		marketWindow.setType(new Texture(Gdx.files.absolute("assets/Shop.png")));
		marketWindow.setX(100);
		marketWindow.setY(300);

		inventoryWindow.setType(new Texture(Gdx.files.absolute("assets/Inventory.png")));
		marketWindow.setX(100);
		marketWindow.setY(300);

		mainWindow.setType(new Texture(Gdx.files.absolute("assets/StationWindow.png")));
		mainWindow.setX(950);
		mainWindow.setY(300);

		playerX = 0;
		playerY = 0;
		velocityX = 0;
		velocityY = 0;
		mainWindow.setVisible(true);
		marketWindow.setVisible(false);
		stationView = false;
		partDrag = false;

		map.start();
		map.update();

		player = map.getPlayer("Jerry");
		crosshair = new Crosshair();

		Gdx.input.setInputProcessor(new InputAdapter() {
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if (button == Input.Buttons.LEFT) {
					// do something
					return true;
				}else{
					return false;
				}
			}
		});
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Render game
		if (stationView == false){
			batch.begin();
			crosshair.render();

			player.render(map, velocityX, velocityY, batch);
			batch.end();

			//Key and mouse input
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				velocityX -= 0.1;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				velocityX += 0.1;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.UP)){
				velocityY -= 0.1;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				velocityY += 0.1;
			}
			if(Gdx.input.isTouched()){
				shoot();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.X)){
				for (int i = 0; map.solarSystem.get(player.getSystem()).entities.size() > i; i++) {
					Entity obj = (map.solarSystem.get(player.getSystem()).entities.get(i));
					if (obj instanceof Gate) {
						if (((Gate)obj).isInRange()) {
							map.jumpTo(player,((Gate)obj));
							break;
						}
					}else if (obj instanceof Station) {
						if (((Station) obj).isInRange()) {
							stationView = true;
						}
					}
				}
			}
		}else{
			//station windows
			batch.begin();
			renderStationInside(player);
			batch.end();
			if(Gdx.input.isTouched()){
				checkClick();
			}
		}

	}

	public static void checkClick(){
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		System.out.println(mouseX + " " + mouseY);
		if(mouseX >= mainWindow.getX() + 139 && mouseY >= mainWindow.getY()-217 && mouseX <= 197+mainWindow.getX() && mouseY <= mainWindow.getY()-181){
			//undock
			stationView = false;
		}else if(mouseX >= mainWindow.getX()+34 && mouseY >= 162 && mouseX <= mainWindow.getX()+136 && mouseY <= 198){
			//repair
		}else if(mouseX >= 1143 && mouseY >= 162 && mouseX <= 1246 && mouseY <= 198){
			//shop
			marketWindow.setVisible(true);
		}else if(mouseX >= 989 && mouseY >= 242 && mouseX <= 1244 && mouseY <= 293){
			//Inventory
			inventoryWindow.setVisible(true);
		}else if(mouseX >= 988 && mouseY >= 339 && mouseX <= 1244 && mouseY <= 392){
			//skills
		}

		if (marketWindow.isVisible() == true){
			if (mouseX >= 209 && mouseY >= 95 && mouseX <= 235 && mouseY <= 118){
				dragging = "expand";
				partDrag = true;
			}else if (mouseX >= 205 && mouseY >= 130 && mouseX <= 237 && mouseY <= 159){
				dragging = "drill";
				partDrag = true;
			}else if (mouseX >= 211 && mouseY >= 177 && mouseX <= 234 && mouseY <= 209){
				dragging = "cannon";
				partDrag = true;
			}
		}
	}

	public static void setPlayerXY(int x, int y,double vX ,double vY){
		playerX = x;
		playerY = y;
		velocityX = vX;
		velocityY = vY;
	}

	public void renderStationInside(Player player){
		for (int x = 0; x <= 1300; x += 28){
			for (int y = 0; y <= 800; y += 28){
				batch.draw(goldTech, x, y);
			}
		}
		player.renderShip();
		if (mainWindow.isVisible() == true){
			mainWindow.render();
		}
		if (marketWindow.isVisible() == true){
			marketWindow.render();
		}
		if (inventoryWindow.isVisible() == true){
			inventoryWindow.render();
		}
		if (partDrag == true){
			int realPosX = ((Gdx.input.getX()+ 648)/28)*28  - 650;
			int realPosY = ((Gdx.input.getY()+ 364)/28)*28 - 336;
			int relativePosX = ((realPosX - (648/28)*28) + 6)/28;
			int relativePosY = (realPosY - (364/28)*28)/-28;
			if (dragging.equals("expand") || true) {
				batch.draw(expansion, realPosX, (-1 * (realPosY)) + 800);
			}
			if(Gdx.input.isTouched()) {
				player.addPart(relativePosX, relativePosY, dragging);
				partDrag = false;
			}
		}
	}

	public static void shoot(){
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		if (sand > 0) {
			sand--;
		}else{
			player.shoot(mouseX, mouseY);
			sand = 10;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
