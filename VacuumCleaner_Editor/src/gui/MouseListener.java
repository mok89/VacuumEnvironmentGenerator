package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.Room;

/**
 * 
 * @author fabrizio
 *
 */

public class MouseListener extends MouseAdapter {

	EditorPanel editorPanel;
	
	public MouseListener(EditorPanel ep) {
		this.editorPanel=ep;
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		super.mouseDragged(arg0);
		editorPanel.setCell(arg0.getX(), arg0.getY(), Room.DIRTY);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.mouseReleased(arg0);
		/*
		if(arg0.getButton()==arg0.BUTTON1)
			editorPanel.setCell(arg0.getX(), arg0.getY());
		else {}
		*/
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		super.mouseClicked(arg0);
		if(arg0.getButton()==MouseEvent.BUTTON1)
			editorPanel.setCell(arg0.getX(), arg0.getY());
	}
	
	
	
}
