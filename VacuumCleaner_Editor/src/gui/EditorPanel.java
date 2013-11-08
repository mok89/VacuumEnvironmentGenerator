package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core.Room;

/**
 * 
 * @author fabrizio
 *
 */

public class EditorPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Room room;
	private Image imgDirty;
	private Image imgWall;
	private Image imgBase;
	private Image imgAgent;
	
	private MouseListener mouseListener;
	
	public EditorPanel(Room room) {
		this.room = room;
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		uploadPictures();
		mouseListener = new MouseListener(this);
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	private void uploadPictures() {
		try {
			imgDirty = ImageIO.read(new File(".//img//polvere_625833.jpg"));
			imgWall = ImageIO.read(new File(".//img//wall.jpg"));
			imgBase = ImageIO.read(new File(".//img//base.jpg"));
			imgAgent = ImageIO.read(new File(".//img//agent.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int optimalSizeCellWidth() {
		return (this.getWidth()/room.getSize());
	}
	
	public int optimalSizeCellHeight() {
		return (this.getHeight()/room.getSize());
	}
	
	private int getPositionX(int pixelWidth) {
		return (pixelWidth/optimalSizeCellWidth());
	}
	
	private int getPositionY(int pixelHeight) {
		return (pixelHeight/optimalSizeCellHeight());
	}
	
	public void setCell(int pixelWidth, int pixelHeight, int state) {
		int positionX = getPositionX(pixelWidth);
		int positionY = getPositionY(pixelHeight);
		/*
		System.out.println("x: " + positionX);
		System.out.println("y: " + positionY);
		*/
		room.setCell(positionY, positionX, state);
		repaint();
	}
	
	public void setCell(int pixelWidth, int pixelHeight) {
		int positionX = getPositionX(pixelWidth);
		int positionY = getPositionY(pixelHeight);
		/*
		System.out.println("x: " + positionX);
		System.out.println("y: " + positionY);
		*/
		if(room.getCell()[positionY][positionX]==Room.CLEAN)
			room.setCell(positionY, positionX, Room.DIRTY);
		else if(room.getCell()[positionY][positionX]==Room.DIRTY)
			room.setCell(positionY, positionX, Room.WALL);
		else if(room.getCell()[positionY][positionX]==Room.WALL)
			room.setCell(positionY, positionX, Room.AGENT);
		else
			room.setCell(positionY, positionX, Room.CLEAN);
		repaint();
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<room.getSize(); i++)
			for(int j=0; j<room.getSize(); j++) {
				if(room.getCell()[i][j]==Room.DIRTY)
					g.drawImage(this.imgDirty, j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight(), null);
				else if(room.getCell()[i][j]==Room.WALL)
					g.drawImage(this.imgWall, j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight(), null);
				else if(room.getCell()[i][j]==Room.BASE)
					g.drawImage(this.imgBase, j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight(), null);
				else if(room.getCell()[i][j]==Room.AGENT)
					g.drawImage(this.imgAgent, j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight(), null);
				g.drawRect(j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight());
			}
	}
	
}
