package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import core.JDomWriter;
import core.Room;
import core.Tile;

/**
 * 
 * @author fabrizio
 *
 */

public class EditorFrame extends JFrame {

	private static final int columSelectionPanel = 3;

	private static final int rowSelectionPanel = 13;

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

	JTextField sizeTextN;
	JTextField sizeTextM;
	JTextField energyTextField;
	JTextField percDirtyTextField;
	JTextField percWallTextField;
	JTextField percCleanTextField;
	JTextField costMoveTextField;
	JTextField costSuckTextField;

	JPanel optionPanel =new JPanel(new FlowLayout());
	//	JPanel selectionPanel=new JPanel(new GridLayout(rowSelectionPanel,columSelectionPanel));
	JPanel selectionPanel=new JPanel(new BorderLayout());

	JSplitPane js=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane jsMain=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

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
		editorPanel = new EditorPanel(room,this);
		conteiner();
		initOptionPanel();
		intSelectionPanel();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frameListener = new FrameListener(this);
		this.addWindowListener(frameListener);
	}

	private void intSelectionPanel() {
		JPanel panelSelectionPanel=new JPanel(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		JButton cleanButton=new JButton();
		cleanButton.setPreferredSize(new Dimension(40, 40));
		cleanButton.setBackground(Color.white);
		panelSelectionPanel.add(cleanButton,c);
		c.gridy++;
		c.weightx=5;c.weighty=5;
		JButton agentButton=new JButton(new ImageIcon(new ImageIcon("./img/agent.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panelSelectionPanel.add(agentButton,c);
		c.gridy++;
		JButton dirtButton=new JButton(new ImageIcon(new ImageIcon("./img/polvere_625833.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panelSelectionPanel.add(dirtButton,c);
		c.gridy++;c.weightx=5;c.weighty=5;
		JButton wallButton=new JButton(new ImageIcon(new ImageIcon("./img/wall.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panelSelectionPanel.add(wallButton,c);
		c.gridy++;c.weightx=5;c.weighty=5;
		JButton baseButton=new JButton(new ImageIcon(new ImageIcon("./img/base.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panelSelectionPanel.add(baseButton,c);
		/**
		 * listener on the button
		 */
		cleanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				editorPanel.setItemSelected(Room.CLEAN);
			}
		});
		agentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				editorPanel.setItemSelected(Room.AGENT);
			}
		});
		dirtButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				editorPanel.setItemSelected(Room.DIRTY);
			}
		});
		wallButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				editorPanel.setItemSelected(Room.WALL);
			}
		});
		baseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				editorPanel.setItemSelected(Room.BASE);
			}
		});
		selectionPanel.add(panelSelectionPanel,BorderLayout.CENTER);
	}


	private void initOptionPanel() {

		JPanel sizePanel=new JPanel(new GridLayout(1, 2));

		JLabel sizeLabel = new JLabel("Size");
		sizePanel.add(sizeLabel);

		JPanel nmPanel=new JPanel(new BorderLayout());
		{
			sizeTextN = new JTextField(String.valueOf(room.getSizeN()));
			sizeTextN.setColumns(3);
			sizeTextM = new JTextField(String.valueOf(room.getSizeM()));
			sizeTextM.setColumns(3);
			//		sizePanel.add(sizeTextN);
			//		sizePanel.add(sizeTextM);

			JPanel nPanel=new JPanel(new GridLayout(1,2));
			nPanel.add(new JLabel("N"));
			nPanel.add(sizeTextN);
			JPanel mPanel=new JPanel(new GridLayout(1,2));
			mPanel.add(new JLabel("M"));
			mPanel.add(sizeTextM);

			nmPanel.add(nPanel,BorderLayout.NORTH);
			nmPanel.add(mPanel,BorderLayout.SOUTH);
		}
		sizePanel.add(nmPanel);

		optionPanel.add(sizePanel);

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

		JLabel percWallLabel = new JLabel("% Wall");
		optionPanel.add(percWallLabel);
		percWallTextField = new JTextField(String.valueOf(room.getPerc_wall()));
		percWallTextField.setColumns(4);
		optionPanel.add(percWallTextField);

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

		sizeTextN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				if(e.getKeyChar()=='\n')
					changeMatrix(sizeTextN.getText().toString(),sizeTextM.getText().toString());
			}
		});
		sizeTextM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				if(e.getKeyChar()=='\n')
					changeMatrix(sizeTextN.getText().toString(),sizeTextM.getText().toString());
			}
		});

		JButton randomize=new JButton("Randomize");
		randomize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getAllItems();
				//					ArrayList<int [][]> results=room.randomizeCell();
				//					if(results!=null && results.size()>0)
				//						room.setCell(results.get(results.size()-1));
				//					else
				//						JOptionPane.showMessageDialog(editorPanel, "Uguale a zero");
				room.randomize();
				editorPanel.repaint();
				super.mouseClicked(e);
			}

			private void getAllItems() {
				room.setPerc_dirty(Double.parseDouble(percDirtyTextField.getText()));
				room.setPerc_wall(Double.parseDouble(percWallTextField.getText()));
			}
		});
		optionPanel.add(randomize);
	}

	protected void changeMatrix(String sizN, String sizM) {
		try{
			Integer sizeN=Integer.valueOf(sizN);
			Integer sizeM=Integer.valueOf(sizM);
			if(sizeN>1 && sizeM>1){
				Tile[][] cell = new Tile[sizeN][sizeM];
				for(int i=0; i<sizeN; i++)
					for(int j=0; j<sizeM; j++){
						if(i<room.getSizeN() && j<room.getSizeM())
							cell[i][j] = room.getAt(i, j);
						else{
							cell[i][j]=new Tile(Room.CLEAN, 1);
						}
					}
				room.setCell(cell);
				room.setSizeN(sizeN);
				room.setSizeM(sizeM);
			}

		}catch(Exception e){}
		editorPanel.updateUI();
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
		js.setDividerLocation(getWidth()-getWidth()/4);
		js.setLeftComponent(jsMain);
		//		js.setRightComponent(optionPanel);
		js.setRightComponent(optionPanel);

		jsMain.setDividerLocation(getHeight()-getHeight()/10);
		jsMain.setLeftComponent(editorPanel);
//		jsMain.setDividerLocation((double)0.90);
		//		jsMain.setRightComponent(selectionPanel);
		jsMain.setRightComponent(selectionPanel);
		this.add(js,BorderLayout.CENTER);
	}

	public void newProject() {
		room = new Room(Integer.parseInt(sizeTextN.getText()),Integer.parseInt(sizeTextM.getText()), Double.parseDouble(energyTextField.getText()), Double.parseDouble(percDirtyTextField.getText()),Double.parseDouble(percWallTextField.getText()), Double.parseDouble(percCleanTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costMoveTextField.getText()), Double.parseDouble(costSuckTextField.getText()));
		editorPanel.setRoom(room);
		editorPanel.validate();
		editorPanel.repaint();
	}

	public void saveAsFile() {
		/*
		 * UPDATE VARIABLES CLASS ROOM
		 */
		sizeTextN.setText(String.valueOf(room.getSizeN()));
		sizeTextM.setText(String.valueOf(room.getSizeM()));
		room.setEnergy(Double.parseDouble(energyTextField.getText()));
		room.setCost_move_up(Double.parseDouble(costMoveTextField.getText()));
		room.setCost_move_down(Double.parseDouble(costMoveTextField.getText()));
		room.setCost_move_left(Double.parseDouble(costMoveTextField.getText()));
		room.setCost_move_right(Double.parseDouble(costMoveTextField.getText()));
		room.setCost_suck(Double.parseDouble(costSuckTextField.getText()));

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

	public void openFile() {
		//TODO
	}

}
