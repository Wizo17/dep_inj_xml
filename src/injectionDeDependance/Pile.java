/**
 * 
 * @author ZOUNON William
 * @desciption Classe Pile permettant de gérer une stucture de Pile à partir d'un LinkedList
 * @date_creation 28/01/2018
 * @niveau MIAGE-GI Licence 3
 * @version 1.0
 * 
 **/

package injectionDeDependance;

import java.util.LinkedList;
import java.util.List;

public class Pile<T> {
	
	private List<T> pile;
	
	public Pile(){
		 pile = new LinkedList<T>();
	}
	public Pile(T val) {
		pile.add(val);
	}
	public Pile(List<T> list) {
		pile = list;
	}
	
	boolean empiler(T val) {
		try {
			pile.add(val);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	T depiler() {
		if(!(pile.isEmpty())) {
			T val;
			int taille = pile.size();
			try {
				val = (T) pile.get(taille-1);
				pile.remove(taille-1);
			}
			catch(Exception e)
			{
				return null;
			}
			return val;
		}
		else
			return null;
	}
	
	void afficher() {
		for(int i = 0; i < pile.size(); i++)
			System.out.println(pile.get(pile.size()-i-1));
	}
	
	int size() {
		return pile.size();
	}
	
	T getElement(int i) {
		return (T) pile.get(i);
	}
}

