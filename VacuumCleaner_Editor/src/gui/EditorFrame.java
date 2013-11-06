package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author fabrizio
 *
 */

public class EditorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final static int SCREEN_DEFAULT_SIZE_WIDTH = 800;
	protected final static int SCREEN_DEFAULT_SIZE_HEIGHT = 800;	
	
	private GraphicsEnvironment ge;	
	
	private JMenuItem newProject;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem exit;
	private JMenu file;
	private JMenuBar bar;
	
	private MenuListener menuListener;
	
	private FrameListener frameListener;
	
	private EditorPanel editorPanel;	
	
	public EditorFrame() {
		super();
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setTitle("VACUUM CLEANER - Editor");
		setSize(SCREEN_DEFAULT_SIZE_WIDTH, SCREEN_DEFAULT_SIZE_HEIGHT);
		setCenterScreen();
		setMenu();
		editorPanel = new EditorPanel();
		conteiner();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frameListener = new FrameListener(this);
		this.addWindowListener(frameListener);
	}
	
	private void setCenterScreen() {
		java.awt.Point center = ge.getCenterPoint();
	    java.awt.Rectangle bounds = ge.getMaximumWindowBounds();
	    int w = Math.max(bounds.width/2, Math.min(getWidth(), bounds.width));
	    int h = Math.max(bounds.height/2, Math.min(getHeight(), bounds.height));
	    int x = center.x - w/2, y = center.y - h/2;
	    setBounds(x, y, w, h);
        if (w == bounds.width && h == bounds.height)
            setExtendedState(MAXIMIZED_BOTH);
        validate();
	}
	
	private void setMenu() {
		
		newProject = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As...");
		exit = new JMenuItem("Exit");
		file = new JMenu("File");
		bar = new JMenuBar();
				
		/*
		 * BARRA MENU
		 */
		file.add(newProject);
		file.add(open);
		file.addSeparator();
		file.add(save);
			save.setEnabled(false);
		file.add(saveAs);
			//saveAs.setEnabled(false);
		file.addSeparator();
		file.add(exit);
		bar.add(file);
		this.setJMenuBar(bar);
		
		abiltyMenuListener();

	}
	
	public void abiltyMenuListener() {
		/*
		 * EVENTI
		 */
		menuListener = new MenuListener(this);
		newProject.addActionListener(menuListener);
		open.addActionListener(menuListener);
		save.addActionListener(menuListener);
		saveAs.addActionListener(menuListener);
		exit.addActionListener(menuListener);
	}
	
	private void conteiner() {
		this.setLayout(new BorderLayout());
		this.add(editorPanel,BorderLayout.CENTER);
	}
	
	
	public static void main(String[] args) {
		EditorFrame ef = new EditorFrame();
		ef.setVisible(true);
	}

}
