package m2i.java.projet1;

import java.util.ArrayList;

public interface IDAO<T> {
	
	/**
	 * Instancie une table avec une liste d'objets prédéfinie
	 * 
	 * @return résultat de la requête SQL (succès = 1, échec = 0, exception = -1)
	 */
	int Instanciate();

	/**
	 * Insère un objet dans la table
	 * 
	 * @param obj
	 * @return résultat de la requête SQL (succès = 1, échec = 0, exception = -1)
	 */
	int Create(T obj);

	/**
	 *  Insère plusieurs objets dans la table
	 * 
	 * @param obj
	 * @return le résultat de la requête SQL (succès = 1, échec = 0, exception = -1)
	 */
	int CreateSeveral(ArrayList<T> obj); //
	
	/**
	 * Cherche et renvoie un objet dont l'id est passé en paramètre
	 * 
	 * @param id
	 * @return l'objet (ou null s'il n'est pas dans la table)
	 */
	T Read(int id);

	/**
	 * Renvoie la collection d'objets stockés dans la table
	 * 
	 * @return tous les objets de type T en tant que collection
	 */
	ArrayList<T> ReadAll();

	/**
	 * Modifie tous les attributs d'une entrée dont l'id correspond à l'objet passé en paramètre
	 * 
	 * @param obj
	 * @return résultat de la requête SQL (succès = 1, échec = 0, exception = -1)
	 */
	int Update(T obj); // extrait les données de l'objet T et renvoie le nombre de modifications

	/**
	 * Supprime une entrée de la table 
	 * 
	 * @param id
	 * @return résultat de la requête SQL (succès = 1, échec = 0, exception = -1)
	 */
	int Delete(int id); // supprime la donnée dont on passe l'ID en prm
}
