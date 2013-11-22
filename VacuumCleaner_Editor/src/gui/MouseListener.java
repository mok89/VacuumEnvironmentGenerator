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
//		editorPanel.setCell(arg0.getX(), arg0.getY(), Room.DIRTY);
		draw(arg0);
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
		//		if(arg0.getButton()==MouseEvent.BUTTON1)
		//			editorPanel.setCell(arg0.getX(), arg0.getY());
		//		else
		//			editorPanel.setCell(arg0.getX(), arg0.getY(), Room.BASE);

		draw(arg0);
	}


	protected void draw(MouseEvent arg0) {
		int selected=editorPanel.getItemSelected();
		switch (selected) {
		case 0:
			editorPanel.setCell(arg0.getX(), arg0.getY(), Room.CLEAN);
			break;
		case 1:
			editorPanel.setCell(arg0.getX(), arg0.getY(), Room.DIRTY);
			break;
		case 2:
			editorPanel.setCell(arg0.getX(), arg0.getY(), Room.WALL);
			break;
		case -1:
			editorPanel.setCell(arg0.getX(), arg0.getY(), Room.BASE);
			break;
		case -2:
			editorPanel.setCell(arg0.getX(), arg0.getY(), Room.AGENT);
			break;
		default:
			break;
		}
	}	

}
