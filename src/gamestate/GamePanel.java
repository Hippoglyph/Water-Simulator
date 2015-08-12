package gamestate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private GameMap gameMap;
	private InputsHandler input;
	private Timer timer;

	public GamePanel(){
		createDeafult();
		
		addMouseListener(this);
		timer = new Timer(1000/60, this);
		timer.start();
	}

	private void createDeafult() {
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setFocusable(true);
		
		gameMap = new GameMap(this);
		input = new InputsHandler(gameMap);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		int tileWidth = super.getWidth()/gameMap.getRowSize();
		int tileHeight = super.getHeight()/gameMap.getColumnSize();
		for(int x = 0; x < gameMap.getRowSize(); x++){
			for(int y = 0; y < gameMap.getColumnSize(); y++){
				drawTile(g, x, y, x*tileWidth, y*tileHeight, tileWidth, tileHeight);
			}
		}
	}
	
	public void drawTile(Graphics g, int x, int y, int xPos, int yPos, int width, int height){
		if(gameMap.isInXBounds(x) && gameMap.isInYBounds(y)){
			g.setColor(gameMap.getTileColor(x, y));
			g.fillRect(xPos, yPos, width, height);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		input.update();
		gameMap.update();
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("Pressed " + e.getButton());
		if(e.getButton() == MouseEvent.BUTTON1)
			input.LMD();
		if(e.getButton() == MouseEvent.BUTTON3)
			input.RMD();	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("Released");
		if(e.getButton() == MouseEvent.BUTTON1)
			input.LMU();
		if(e.getButton() == MouseEvent.BUTTON3)
			input.RMU();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
