package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import core.JDomWriter;
import core.Room;

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
	
	protected final static int SCREEN_DEFAULT_SIZE_WIDTH = 730;
	protected final static int SCREEN_DEFAULT_SIZE_HEIGHT = 700;	
	
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
	
	JTextField sizeTextField;
	JTextField energyTextField;
	JTextField percDirtyTextField;
	JTextField percCleanTextField;
	JTextField costMoveTextField;
	JTextField costSuckTextField;
	
	JPanel optionPanel =new JPanel(new FlowLayout());
	JSplitPane js=new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	private Room room;
	
	private JFileChooser chooser;
	private String filename;
	
	private JDomWriter jDomWriter;
	
	public EditorFrame() {
		super();
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setTitle("VACUUM CLEANER - Editor");
		setSize(SCREEN_DEFAULT_SIZE_WIDTH, SCREEN_DEFAULT_SIZE_HEIGHT);
		setCenterScreen();
		setMenu();
		room =new Room();
		editorPanel = new EditorPanel(room);
		conteiner();
		initOptionPanel();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frameListener = new FrameListener(this);
		this.addWindowListener(frameListener);
	}
	
	private void initOptionPanel() {
		
		JLabel sizeLabel = new JLabel("Size");
		optionPanel.add(sizeLabel);
		sizeTextField = new JTextField(String.valueOf(room.getSize()));
		sizeTextField.setColumns(4);
		optionPanel.add(sizeTextField);
	
		JLabel energyLabel = new JLabel("Energy");
		optionPanel.add(energyLabel);
		energyTextField = new JTextField(String.valueOf(room.getEnergy()));
		energyTextField.setColumns(4);
		optionPanel.add(energyTextField);
		
		JLabel percDirtyLabel = new JLabel("% Dirty");
		optionPanel.add(percDirtyLabel);
		percDirtyTextField = new JTextField(String.valueOf(room.getPerc_dirty()));
		percDirtyTextField.setColumns(4);
		optionPanel.add(percDirtyTextField);
		
		JLabel percCleanLabel = new JLabel("% Clean");
		optionPanel.add(percCleanLabel);
		percCleanTextField = new JTextField(String.valueOf(room.getPerc_clean()));
		percCleanTextField.setColumns(4);
		optionPanel.add(percCleanTextField);
		
		JLabel costMoveLabel = new JLabel("Cost move");
		optionPanel.add(costMoveLabel);
		costMoveTextField = new JTextField(String.valueOf(room.getCost_move_up()));
		costMoveTextField.setColumns(4);
		optionPanel.add(costMoveTextField);
		
		JLabel costSuckLabel = new JLabel("Cost suck");
		optionPanel.add(costSuckLabel);
		costSuckTextField = new JTextField(String.valueOf(room.getCost_suck()));
		costSuckTextField.setColumns(4);
		optionPanel.add(costSuckTextField);
		
		JButton randomize=new JButton("Randomize");
		randomize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				room.randomizeCell();
				editorPanel.repaint();
				super.mouseClicked(e);
			}
		});
		optionPanel.add(randomize);
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
		js.setDividerLocation(getWidth()-getWidth()/5);
		js.setLeftComponent(editorPanel);
		js.setRightComponent(optionPanel);
		this.add(js,BorderLayout.CENTER);
	}
	
	public void newProject() {
		room = new Room(Integer.parseInt(sizeTextField.getText()), Double.parseDouble(energyTextField.getText()), Double.parseDouble(percDirtyTextField.getText()), Double.parseDouble(percCleanTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costSuckTextField.getText()));
		editorPanel.setRoom(room);
		editorPanel.validate();
		editorPanel.repaint();
	}
	
	public void saveAsFile() {
		chooser = new JFileChooser();
		final ExtensionFileFilter filter = new ExtensionFileFilter();
		filter.addExtension("xml");
		filter.setDescription("Environment (xml)");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(".//environment"));
		int result = chooser.showSaveDialog(saveAs);
		if(result==JFileChooser.APPROVE_OPTION) {
			filename = chooser.getSelectedFile().getName();
			jDomWriter = new JDomWriter(room);
			jDomWriter.createXML(filename);
		}
	}
	
	
	public static void main(String[] args) {
		EditorFrame ef = new EditorFrame();
		ef.setVisible(true);
	}

}
