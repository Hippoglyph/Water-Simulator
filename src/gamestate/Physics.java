package gamestate;

import java.util.Random;

public class Physics {
	
	private Tile[][] tiles;
	private GameMap map;
	private Random rnd;

	public Physics(Tile[][] tiles, GameMap map){
		this.tiles = tiles;
		this.map = map;
		rnd = new Random();
	}
	
	public void update(){
		scan();
	}
	
	private void scan(){
		if(rnd.nextInt(2) == 1){
			for(int x = map.getRowSize()-1; x >= 0; x--){
				for(int y = map.getColumnSize()-1; y >= 0; y--){
					move(tiles[x][y]);
				}
			}
		}
		else
			for(int x = 0; x <= map.getRowSize()-1; x++){
				for(int y = map.getColumnSize()-1; y >= 0; y--){
						move(tiles[x][y]);
				}
			}
	}

	private void move(Tile tile) {
		if(tile.isAir())
			return;
		if(rnd.nextInt(10) < 1)
			return;
		edge(tile);
		fall(tile);
		sidestep(tile);
	}
	
	private void edge(Tile tile){
		if(tile.xPos >= map.getRowSize()-1 || tile.xPos <= 0)
			tile.setTypeId(Tile.AIR);
	}

	private void sidestep(Tile tile) {
		if(tile.isWater())
			slide(tile);
		else if(map.dirtAbove(tile) > rnd.nextInt(3)+2){
			slide(tile);
		}
	}

	private void slide(Tile tile) {
		if(tile.getDirection() == 0)
			tile.setDirection(rnd.nextInt(3)-1);
		if(map.canSlide(tile))
			map.slide(tile);
		else
			tile.setDirection(tile.getDirection()*-1);
	}

	private void fall(Tile tile) {
		if(map.canDecend(tile))
			map.decend(tile);
	}
}
