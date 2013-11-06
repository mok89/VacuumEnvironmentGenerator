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
	private MouseListener mouseListener;
	
	public EditorPanel() {
		room = new Room();
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		uploadPictures();
		mouseListener = new MouseListener(this);
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
	}
	
	private void uploadPictures() {
		try {
			imgDirty = ImageIO.read(new File(".//img//polvere_625833.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int optimalSizeCellWidth() {
		return (this.getWidth()/Room.DEFAULT_SIZE);
	}
	
	public int optimalSizeCellHeight() {
		return (this.getHeight()/Room.DEFAULT_SIZE);
	}
	
	private int getPositionX(int pixelWidth) {
		return (pixelWidth/optimalSizeCellWidth());
	}
	
	private int getPositionY(int pixelHeight) {
		return (pixelHeight/optimalSizeCellHeight());
	}
	
	public void setDirty(int pixelWidth, int pixelHeight) {
		int positionX = getPositionX(pixelWidth);
		int positionY = getPositionY(pixelHeight);
		/*
		System.out.println("x: " + positionX);
		System.out.println("y: " + positionY);
		*/
		room.setCell(positionY, positionX, Room.DIRTY);
		repaint();
	}
	
	public void setClean(int pixelWidth, int pixelHeight) {
		int positionX = getPositionX(pixelWidth);
		int positionY = getPositionY(pixelHeight);
		room.setCell(positionY, positionX, Room.CLEAN);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<Room.DEFAULT_SIZE; i++)
			for(int j=0; j<Room.DEFAULT_SIZE; j++) {
				if(room.getCell()[i][j]==Room.DIRTY)
					g.drawImage(this.imgDirty, j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight(), null);
				g.drawRect(j*this.optimalSizeCellWidth(), i*this.optimalSizeCellHeight(), this.optimalSizeCellWidth(), this.optimalSizeCellHeight());
			}
	}
	
}
