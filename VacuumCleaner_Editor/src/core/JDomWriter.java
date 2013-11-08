package core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * 
 * @author fabrizio
 *
 */

public class JDomWriter {
	
	/*
	 * impostare struttura xml
	 */
	
	private Room room;
	
	public JDomWriter(Room room) {
		this.room=room;
	}
	
	private void WriteToFile(Document document, String filename) {
		String ext = filename.substring(filename.length()-4, filename.length());
		if(!ext.equals(".xml"))
			filename += ".xml";
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		try {
			outputter.output(document, new FileOutputStream(".//environment//"+filename/*+".xml"*/));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createXML(String filename) {
		Element vacuum_istance = new Element("vacuum_istance");
		
		Document document = new Document(vacuum_istance);
		
		Element board = new Element("board");
		Element size = new Element("size");
		size.setText(String.valueOf(room.getSize()));
		board.addContent(size);
		Element tile_state;
		
		int base_x=-1, base_y=-1;
		int agent_x=-1, agent_y=-1;
		
		for(int i=0; i<room.getSize(); i++)
			for(int j=0; j<room.getSize(); j++)
				if(room.getCell()[i][j]!=Room.CLEAN && room.getCell()[i][j]!=Room.BASE && room.getCell()[i][j]!=Room.AGENT) { 
					tile_state = new Element("tile_state");
					tile_state.setAttribute("x", String.valueOf(i));
					tile_state.setAttribute("y", String.valueOf(j));
					if(room.getCell()[i][j]==Room.DIRTY)
						tile_state.setText("dirty");
					else if(room.getCell()[i][j]==Room.WALL)
						tile_state.setText("obstacle");
					/*
					else if(room.getCell()[i][j]==Room.CLEAN)
						tile_state.setText("clean");
					*/
					board.addContent(tile_state);
				}
				else if(room.getCell()[i][j]==Room.BASE) {
					base_x=i;
					base_y=j;
				}
				else if(room.getCell()[i][j]==Room.AGENT) {
					agent_x=i;
					agent_y=j;
				}
		vacuum_istance.addContent(board);
		
		Element action_costs = new Element("action_costs");
		Element up = new Element("up");
		up.setText(String.valueOf(room.getCost_move_up()));
		action_costs.addContent(up);
		Element down = new Element("down");
		down.setText(String.valueOf(room.getCost_move_down()));
		action_costs.addContent(down);
		Element left = new Element("left");
		left.setText(String.valueOf(room.getCost_move_left()));
		action_costs.addContent(left);
		Element right = new Element("right");
		right.setText(String.valueOf(room.getCost_move_right()));
		action_costs.addContent(right);
		Element suck = new Element("suck");
		suck.setText(String.valueOf(room.getCost_suck()));
		action_costs.addContent(suck);
		vacuum_istance.addContent(action_costs);
		
		Element agent = new Element("agent");
		Element xA = new Element("x");
		xA.setText(String.valueOf(agent_x));
		Element yA = new Element("y");
		yA.setText(String.valueOf(agent_y));
		agent.addContent(xA);
		agent.addContent(yA);
		Element energy = new Element("energy");
		energy.setText(String.valueOf(room.getEnergy()));
		agent.addContent(energy);
		vacuum_istance.addContent(agent);
		
		Element base = new Element("base");
		Element xB = new Element("x");
		xB.setText(String.valueOf(base_x));
		Element yB = new Element("y");
		yB.setText(String.valueOf(base_y));
		base.addContent(xB);
		base.addContent(yB);		
		vacuum_istance.addContent(base);
		
		WriteToFile(document, filename);
	}
	
	public void TestJDom() {
		Element root = new Element("utenti");
		Document document = new Document(root);
		Element utente = new Element("utente");
		Element nome = new Element("nome");
		nome.setText("Fabrizio");
		Element cognome = new Element("cognome");
		cognome.setText("Cava");
		utente.addContent(nome);
		utente.addContent(cognome);
		root.addContent(utente);
		WriteToFile(document, "testJDom");
		
		/*
		 * <?xml version="1.0" encoding="UTF-8"?>
		 * <utenti>
         *		<utente>
         *			<nome>Fabrizio</nome>
         *			<cognome>Cava</cognome>
         *		</utente>
         * </utenti>
		 */
	}
	
	public static void main(String[] args) {
		JDomWriter jdw = new JDomWriter(new Room());
		jdw.createXML("XML_TEST");
	}

}
