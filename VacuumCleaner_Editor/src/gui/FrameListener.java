package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class FrameListener extends WindowAdapter {
	
	public FrameListener(EditorFrame editorFrame) {
		// TODO Auto-generated constructor stub
		super();
		this.editorFrame = editorFrame;
	}
	
	public void saveProject() {
		int ret = JOptionPane.showConfirmDialog(editorFrame, "Vuoi salvare il progetto?", "SALVA PROGETTO", JOptionPane.YES_NO_OPTION);
		if(ret == JOptionPane.YES_OPTION) {
			/*
			if(editorFrame.getNameFileOpened().equals(""))
				editorFrame.saveAsFile();
			else
				editorFrame.saveFile();
			*/
		}
	}
	
	public void windowClosing(WindowEvent e) {
		int ret = JOptionPane.showConfirmDialog(editorFrame, "Uscire dall'editor?", "EDITOR", JOptionPane.YES_NO_OPTION);
		if(ret == JOptionPane.YES_OPTION) {
			saveProject();
			editorFrame.dispose();
		}
	}

	EditorFrame editorFrame;
	
}
 