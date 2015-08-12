package gamestate;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;


public class GameMap {
	
	private Tile[][] tiles;
	private int mapSizeX = 256;
	private int mapSizeY = 144;
	private GamePanel panel;
	private Random rnd;
	private Physics physics;
	
	public GameMap(GamePanel panel){
		this.panel = panel;
		rnd = new Random();
		createMap();
		physics = new Physics(tiles, this);
	}

	private void createMap() {
		tiles = new Tile[mapSizeX][mapSizeY];
		for(int x = 0; x < mapSizeX; x++){
			for(int y = 0; y < mapSizeY; y++){
				tiles[x][y] = new Tile(x,y);
			}
		}
	}
	
	public void update(){
		physics.update();
	}
	
	public void createDirt(){
		for(int i = 0; i < 3; i++)
			setTile(Tile.DIRT);
	}
	
	public void createWater(){
		for(int i = 0; i < 3; i++)
			setTile(Tile.WATER);
	}
	
	private void setTile(int type) {
		Point mousePos = panel.getMousePosition();
		
		if(mousePos == null)
			return;

		double xRevPos = mousePos.getX()/panel.getWidth();
		double yRevPos = mousePos.getY()/panel.getHeight();
		int x = Math.round((float)(xRevPos*getRowSize()));
		int y = Math.round((float)(yRevPos*getColumnSize()));
		
		x = x+rnd.nextInt(8)-4;
		y = y+rnd.nextInt(8)-4;
		
		if(!(isInXBounds(x) && isInYBounds(y)))
			return;

		tiles[x][y].setTypeId(type);
	}
	
	public void switchTiles(Tile tile1, Tile tile2){
		int tiletype = tile1.getTypeId();
		int tileDirection = tile1.getDirection();
		tile1.setTypeId(tile2.getTypeId());
		tile1.setDirection(tile2.getDirection());
		tile2.setTypeId(tiletype);
		tile2.setDirection(tileDirection);
	}

	public boolean isInXBounds(int x){
		if(x < 0 || x >= getRowSize())
			return false;
		return true;
	}
	
	public boolean isInYBounds(int y){
		if(y< 0 || y >= getColumnSize())
			return false;
		return true;
	}
	
	public boolean isFlying(Tile tile){
		if(tile.yPos >= getColumnSize()-1)
			return false;
		if(tiles[tile.xPos][tile.yPos+1].getTypeId() == Tile.AIR)
			return true;
		return false;
	}
	
	public int getRowSize(){
		return mapSizeX;
	}
	
	public int getColumnSize(){
		return mapSizeY;
	}
	
	public Color getTileColor(int x, int y){
		return tiles[x][y].getColor();
	}

	public void decend(Tile tile) {
		switchTiles(tile, tiles[tile.xPos][tile.yPos+1]);
	}

	public boolean canSlide(Tile tile) {
		if(tile.xPos >= getRowSize()-1 || tile.xPos <= 0)
			return false;
		if(tiles[tile.xPos+tile.getDirection()][tile.yPos].getTypeId() < tile.getTypeId())
			return true;
		return false;
	}

	public boolean canDecend(Tile tile) {
		if(tile.yPos >= getColumnSize()-1)
			return false;
		if(tiles[tile.xPos][tile.yPos+1].getTypeId() < tile.getTypeId())
			return true;
		return false;
	}

	public void slide(Tile tile) {
		switchTiles(tile, tiles[tile.xPos+tile.getDirection()][tile.yPos]);
	}

	public int dirtAbove(Tile tile) {
		int count = 0;
		for(int i = 0; i < 10; i++){
			if(tile.yPos-i <= 0)
				return count;
			if(tiles[tile.xPos][tile.yPos-i].isDirt())
				count++;
		}
		return count;
	}

}
