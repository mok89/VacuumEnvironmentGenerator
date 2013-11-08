package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class FrameListener extends WindowAdapter {
	
	public FrameListener(EditorFrame editorFrame) {
		super();
		this.editorFrame = editorFrame;
	}
	
	public void windowClosing(WindowEvent e) {
		int ret = JOptionPane.showConfirmDialog(editorFrame, "Do you want to exit from editor?", "EDITOR", JOptionPane.YES_NO_OPTION);
		if(ret == JOptionPane.YES_OPTION) {
			editorFrame.dispose();
		}
	}

	EditorFrame editorFrame;
	
}
 