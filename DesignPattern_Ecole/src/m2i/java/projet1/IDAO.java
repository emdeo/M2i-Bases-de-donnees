package m2i.java.projet1;

import java.util.ArrayList;

public interface IDAO<T> {
	
	/**
	 * Instancie une table avec une liste d'objets pr�d�finie
	 * 
	 * @return r�sultat de la requ�te SQL (succ�s = 1, �chec = 0, exception = -1)
	 */
	int Instanciate();

	/**
	 * Ins�re un objet dans la table
	 * 
	 * @param obj
	 * @return r�sultat de la requ�te SQL (succ�s = 1, �chec = 0, exception = -1)
	 */
	int Create(T obj);

	/**
	 *  Ins�re plusieurs objets dans la table
	 * 
	 * @param obj
	 * @return le r�sultat de la requ�te SQL (succ�s = 1, �chec = 0, exception = -1)
	 */
	int CreateSeveral(ArrayList<T> obj); //
	
	/**
	 * Cherche et renvoie un objet dont l'id est pass� en param�tre
	 * 
	 * @param id
	 * @return l'objet (ou null s'il n'est pas dans la table)
	 */
	T Read(int id);

	/**
	 * Renvoie la collection d'objets stock�s dans la table
	 * 
	 * @return tous les objets de type T en tant que collection
	 */
	ArrayList<T> ReadAll();

	/**
	 * Modifie tous les attributs d'une entr�e dont l'id correspond � l'objet pass� en param�tre
	 * 
	 * @param obj
	 * @return r�sultat de la requ�te SQL (succ�s = 1, �chec = 0, exception = -1)
	 */
	int Update(T obj); // extrait les donn�es de l'objet T et renvoie le nombre de modifications

	/**
	 * Supprime une entr�e de la table 
	 * 
	 * @param id
	 * @return r�sultat de la requ�te SQL (succ�s = 1, �chec = 0, exception = -1)
	 */
	int Delete(int id); // supprime la donn�e dont on passe l'ID en prm
}
