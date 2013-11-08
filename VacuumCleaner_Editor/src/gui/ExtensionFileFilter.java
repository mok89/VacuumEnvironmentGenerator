package gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author fabrizio
 *
 */

public class ExtensionFileFilter extends FileFilter {

	public ExtensionFileFilter() {
		// TODO Auto-generated constructor stub
		this.description = "";
		this.extensions = new ArrayList<String>();
	}
	
	public void addExtension(String extension) {
		if(!extension.startsWith("."))
			extension = "." + extension;
		extensions.add(extension.toLowerCase());
	}
		
	@Override
	public boolean accept(File arg0) {
		// TODO Auto-generated method stub
		if(arg0.isDirectory()) 
			return true;
		String name = arg0.getName().toLowerCase();
		/*
		 * VERIFICA SE IL NOME DI FILE TERMINA CON UNA DELLE ESTENSIONI
		 */
		for(String extension : extensions)
			if(name.endsWith(extension))
				return true;
		return false;
	}

	public void setDescription(String aDescription) {
		this.description = aDescription;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}
	
	
	private String description;
	private ArrayList<String> extensions;

}
