package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author fabrizio
 *
 */

public class MenuListener implements ActionListener {

	public MenuListener(EditorFrame editorFrame) {
		// TODO Auto-generated constructor stub
		super();
		this.editorFrame = editorFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().compareTo("Exit")==0) {
			//editorFrame.getMessage().saveProject();
			//editorFrame.dispose();
		}
		/*
		else if(e.getActionCommand().compareTo("Nuovo")==0)
			editorFrame.newProject();
		else if(e.getActionCommand().compareTo("Apri")==0)
			editorFrame.openFile();
		else if(e.getActionCommand().compareTo("Salva")==0)
			editorFrame.saveFile();
		*/
		else if(e.getActionCommand().compareTo("Save As...")==0)
			editorFrame.saveAsFile();
	}
	
	EditorFrame editorFrame;

}
