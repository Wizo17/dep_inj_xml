/**
 * 
 * @author ZOUNON William
 * @desciption Classe contenant la méthode main
 * @date_creation 28/01/2018
 * @niveau MIAGE-GI Licence 3
 * @version 1.0
 * 
 **/

package injectionDeDependance;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {
	
	static Document document_gen;
	static Element racine_gen;
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Scanner sc = new Scanner(System.in);
		XmlManager xml_manager = new XmlManager();
		int choix;
		
		System.out.println("1 - Eciture d'un ficier xml à partir d'une pile");
		System.out.println("2 - Lecture du contenu d'un fichier xml");
		System.out.println("3 - Injection avec un fichier xml préconfiguré");
		System.out.print("Faites votre choix : ");
		choix = sc.nextInt();
		
		switch(choix) {
		case 1:
			Pile<String> pile = new Pile<String>();
			pile.empiler("Java");
			pile.empiler("Php");
			pile.empiler("C++");
			pile.empiler("Python");
			pile.empiler("Visual basic");
			System.out.println("\nLe fichier xml obtenu est :\n\n\n");
			xml_manager.piletoXml(pile, "xml/pile.xml");
			break;
			
		case 2:
			System.out.println("\nLes données du xml sont : \n");
			xml_manager.afficherXml("xml/pile.xml");
			break;
			
		case 3:
			System.out.println();
			SAXBuilder sx_builder = new SAXBuilder();
			try {
				document_gen = sx_builder.build(new File("xml/injectionDeDependance.xml"));
				racine_gen = document_gen.getRootElement();
				Constructor constructor = getClasse().getConstructor(new Class[]{List.class});
				Pile<String> pile2 = (Pile<String>) constructor.newInstance(getInjection().newInstance());
				String tab[] = {"Java", "Php", "C++", "Python", "Visual basic"};
			
				System.out.println("++++++++EMPILAGE : " + tab[0]);
				pile2.empiler(tab[0]);
				System.out.println("++++++++EMPILAGE : " + tab[1]);
				pile2.empiler(tab[1]);
				System.out.println("++++++++EMPILAGE : " + tab[2]);
				pile2.empiler(tab[2]);
				System.out.println("++++++++EMPILAGE : " + tab[3]);
				pile2.empiler(tab[3]);
				System.out.println("++++++++EMPILAGE : " + tab[4]);
				pile2.empiler(tab[4]);
				System.out.println("--------DEPILAGE : " + pile2.depiler());
			}
			catch(Exception e){
				System.out.println("Eurreur : " + e.getMessage());
			}
			break;
			
		default:
			System.out.println("\nARRET DU PROGRAMME");
			break;
		}
		
			
	}
	
	static Class getClasse() throws ClassNotFoundException
	{
		Element injection = racine_gen.getChild("injection");
		
		return  Class.forName("injectionDeDependance." + injection.getAttributeValue("classe"));
	}
	
	static Class getInjection() throws ClassNotFoundException
	{
		Element injection = racine_gen.getChild("injection");
		return  Class.forName(injection.getAttributeValue("injection"));
	}

}
