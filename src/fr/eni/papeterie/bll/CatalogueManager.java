package fr.eni.papeterie.bll;


import java.util.List;
import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {
	private static CatalogueManager instance= null;
	private ArticleDAO daoArticle;
	

	public CatalogueManager() {
		daoArticle = DAOFactory.getArticleDAO();
	}
	
	public static CatalogueManager getInstance() {
		
		if(instance == null) {
			instance = new CatalogueManager();//Singleton on créé une instance de CatalogueManager s'il n'y en a pas et aucune s'il y en a déjà une
		}
		
		return instance;
	}

	
	public void addArticle(Article a) throws BLLException{
		//validerArticle(a);
		if(a.getIdArticle() == null)
			throw new BLLException("Attention l'article existe déjà");
		try {
			validerArticle(a);
			daoArticle.insert(a);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List <Article> getCatalogue() throws BLLException{
		
		List<Article> catalogue = null;
		try {
			catalogue =daoArticle.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BLLException("Erreur recupération catalogue", e);//levée exception bll
			
		}
		return catalogue;
		
	}	
	
	public Article getArticle(int index) throws BLLException{
		Article articleARecuperer =null;
		try {
			
			articleARecuperer=daoArticle.selectById(index);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  articleARecuperer;
	}
		
	public void updateArticle(Article a) throws BLLException{
		try {
			validerArticle(a);
			daoArticle.update(a);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void removeArticle(int index) throws BLLException{
		if (index <= 0 )
			throw new BLLException("Attention la clé doit etre un entier positif pour suppression");	
		
		try {
			daoArticle.delete(index);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void validerArticle(Article a) throws BLLException{
		//valide uniquement si tous les champs sont renseignés l'id peut être renseigné ou non ça n'a pas d'importance
		if(a == null)
			throw new BLLException("Attention l'article doit exister pour le vérifier");
		if (a.getMarque() == null || a.getMarque().trim().length() == 0)
			throw new BLLException("Attention la marque  doit exister pour vérifier un article");
		if (a.getReference() == null || a.getReference().trim().length() == 0)
			throw new BLLException("Attention la référence  doit exister pour vérifier un article");
		if (a.getDesignation() == null || a.getDesignation().trim().length() == 0)
			throw new BLLException("Attention la désignation  doit exister pour vérifier un article");
		if (a.getPrixUnitaire() < 0)//un float ne peut pas être nul la personne qui saisie la valeur ne peut pas ne rien rentrer ou saisir autre chose qu'un float la levée d'une exception prixUnitaire vaut null
			throw new BLLException("Attention le prix unitaire  doit être positif pour vérifier un article");
		if (!(a.getQteStock()  < 0))//un int ne peut pas être nul la personne qui saisie la valeur ne peut pas ne rien rentrer ou saisir autre chose qu'un int la levée d'une exception qteStock vaut null
			throw new BLLException("Attention la quantité du stock  doit être positive pour vérifier un article");
		
		if(a instanceof Ramette) {
			 
			 if(((Ramette)a).getGrammage() < 0)
					throw new BLLException("Attention le grammage  doit être positif pour verifier une ramette");			 
		}
		

	}


}
