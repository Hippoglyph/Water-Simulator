package gamestate;

public class InputsHandler {
	
	GameMap map;
	private boolean LMD,RMD = false;
	
	public InputsHandler(GameMap map){
		this.map = map;
	}
	
	public void update(){
		if(LMD && RMD)
			return;
		if(LMD)
			map.createDirt();
		if(RMD)
			map.createWater();
	}

	public void LMD(){
		this.LMD = true;
	}
	
	public void RMD(){
		this.RMD = true;
	}
	
	public void LMU(){
		this.LMD = false;
	}
	
	public void RMU(){
		this.RMD = false;
	}
}
