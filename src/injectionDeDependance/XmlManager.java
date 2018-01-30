/**
 * 
 * @author ZOUNON William
 * @desciption Classe XmlManager permettant de gérer certaines operations sur un fichier xml
 * @date_creation 28/01/2018
 * @niveau MIAGE-GI Licence 3
 * @version 1.0
 * 
 * */

package injectionDeDependance;

import java.awt.List;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

public class XmlManager {
		
	/********************************Constructeurs********************************/
	
	public void XmlManager() {}
	
	/********************************Eciture d'un ficier xml à partir d'une pile********************************/
	
	public void piletoXml(Pile pile, String nom_file) {
		
		/*Creation de la racine du fichier xml*/
		Element racine = new Element("pile");
		
		/*Creation d'un nouveau document*/
		Document document = new Document(racine);
		
		/*Ajout des elements de la pile dans le fichier xml*/
		for(int i = 0; i < pile.size(); i++) {
			Element donnee = new Element("donnee");
			donnee.setText((String) pile.getElement(i));
			racine.addContent(donnee);
		}
		
		afficher(document);
		enregistrer(document, nom_file);
	}
	
	void afficher(Document doc) {
		try {
			/*Utilisation d'un format xml classique*/
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			
			sortie.output(doc, System.out);
		}
		catch(Exception e) {
			System.out.println("Erreur : " + e.toString());
		}
	}
	
	boolean enregistrer(Document doc, String fichier) {
		
		try {
			/*Utilisation d'un format xml classique*/
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			
			sortie.output(doc, new FileOutputStream(fichier));
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/********************************Lecture du contenu d'un fichier xml********************************/
	
	public void afficherXml(String fichier) {
		SAXBuilder sx_builder = new SAXBuilder();
		Document document;
		Element racine;
		
		try
	    {
	         document = sx_builder.build(new File(fichier));
	         racine = document.getRootElement();
	         afficheALL(racine);
	    }
	    catch(Exception e){
	    	System.out.println("Erreur : " + e.getMessage());
	    }
	}
	
	static void afficheALL(Element racine)
	{
		java.util.List<Element> listDonnee = racine.getChildren("donnee");
	    Iterator i = listDonnee.iterator();
	      while(i.hasNext()) {
	         Element courant = (Element) i.next();
	         System.out.println(courant.getText());
	      }
	   }
	
}
