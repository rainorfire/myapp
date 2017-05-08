package com.spring.scanner;

import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Controller;


public class ClassScannerTest {
	
	public static void main(String[] args) {
//		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//		ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanFactory);
//		classPathBeanDefinitionScanner.scan("com.test");
//		System.out.println(ArrayUtils.toString(beanFactory.getBeanDefinitionNames()));
		
//		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);  
//		Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com/test"); 
//		for(BeanDefinition bean : beanDefinitions){
//			System.out.println(bean.getBeanClassName()+"-"+bean.getResourceDescription());
//		}
		
		PackageClassScanner scanner = new PackageClassScanner();//
		Set<BeanDefinition> set = scanner.doScan("com/test");
		for(BeanDefinition bean : set){
			String clazzName = bean.getBeanClassName();
			Class<?> forName = null;
			try {
				forName = Class.forName(clazzName);
				Controller controllerAnnotation = forName.getAnnotation(Controller.class);
				if(controllerAnnotation != null){
					Object obj = forName.newInstance();
//					Class<?> controllerObj = ;
					System.out.println(forName.cast(obj));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
