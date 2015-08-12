package gamestate;

import java.awt.Color;

public class Tile {
	
	public final static int AIR = 0;
	public final static int WATER = 1;
	public final static int DIRT = 2;
	
	private int typeId;
	public int xPos;
	public int yPos;
	private int direction;

	public Tile(int x, int y){
		typeId = AIR;
		xPos = x;
		yPos = y;
		direction = 0;
	}
	
	public int getTypeId(){
		return typeId;
	}
	
	public void setTypeId(int id){
		typeId = id;
	}
	
	public Color getColor(){
		if(typeId == AIR)
			return new Color(135,206,250);
		if(typeId == WATER)
			return new Color(0, 0, 128);
		if(typeId == DIRT)
			return new Color(139, 69, 19);
		return Color.BLACK;
	}

	public boolean isAir() {
		if(typeId == AIR)
			return true;
		return false;
	}
	
	public boolean isWater() {
		if(typeId == WATER)
			return true;
		return false;
	}
	
	public boolean isDirt() {
		if(typeId == DIRT)
			return true;
		return false;
	}

	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int i){
		direction = i;
	}
}
