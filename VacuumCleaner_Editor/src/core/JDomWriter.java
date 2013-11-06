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
	
	public JDomWriter() {
		// TODO Auto-generated constructor stub
	}
	
	private void WriteToFile(Document document, String filename) {
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		try {
			outputter.output(document, new FileOutputStream(".//environment//"+filename+".xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}
	
	public static void main(String[] args) {
		JDomWriter jdw = new JDomWriter();
		jdw.TestJDom();
	}

}
