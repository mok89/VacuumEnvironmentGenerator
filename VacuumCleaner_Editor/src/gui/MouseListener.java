package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		editorPanel.setDirty(arg0.getX(), arg0.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.mouseReleased(arg0);
		if(arg0.getButton()==arg0.BUTTON1)
			editorPanel.setDirty(arg0.getX(), arg0.getY());
		else
			editorPanel.setClean(arg0.getX(), arg0.getY());
	}
	
	
	
}
