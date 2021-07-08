package fr.eni.papeterie.dal;

import java.lang.reflect.Constructor;

public class DAOFactory {

	public static ArticleDAO getArticleDAO() {
		Object object = null;
		try {
			Class<?> clazz = Class.forName(Settings.getProperty("articledao"));
			Constructor<?> ctor = clazz.getConstructor();
			object = ctor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArticleDAO) object;
	}
	
}
