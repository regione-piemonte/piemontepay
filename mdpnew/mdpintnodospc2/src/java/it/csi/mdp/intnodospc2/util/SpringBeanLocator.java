package it.csi.mdp.intnodospc2.util;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextBeanFactoryReference;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @generated
 *
 */
public class SpringBeanLocator {

	/**
	 * @generated
	 */
	
	private static BeanFactoryReference beanFactoryReference;
	
	public static Object getBean(String beanName) {
		
		if (beanFactoryReference == null)
			start();
		
		Object beanObject = null;

		try {

			beanObject = beanFactoryReference.getFactory().getBean(beanName);

		} catch (Exception e) {
			System.out.println("Attenzione si è verificata un'eccezione: " + e);
		}

		return beanObject;
	}
	
	public static void start() {
		System.out.println("SpringBeanLocator: INVOCATO START");
		try {
			String[] springCfg = new String[]{"META-INF/beanAdapterContext.xml"};
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
					springCfg);
			beanFactoryReference = new ContextBeanFactoryReference(ctx);

		} catch (Exception e) {
			System.out.println("Impossibile inizializzare il contesto SPRING: " + e);
			e.printStackTrace();
		}
		System.out.println("SpringBeanLocator: START TERMINATO");
	}
	
	public static void relase () {
		System.out.println("SpringBeanLocator: INVOCATO RELEASE");
		try {
			
			if (beanFactoryReference != null)
				beanFactoryReference.release();

		} catch (Exception e) {
			System.out.println("Impossibile inizializzare il contesto SPRING: " + e);
			e.printStackTrace();
		}
		System.out.println("SpringBeanLocator: TERMINATO RELEASE");
	}
}
