package com.spring.scanner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

public class PackageClassScanner {
	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	
	public Set<BeanDefinition> doScan(String packagePath){
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
				resolveBasePackage(packagePath) + "/" + DEFAULT_RESOURCE_PATTERN;
		
		Set<BeanDefinition> set = new HashSet<BeanDefinition>();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();  
		CachingMetadataReaderFactory meataDataReader = new CachingMetadataReaderFactory(resourcePatternResolver);
		
		try {
			Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
			for(Resource r : resources){
				
				if(r.isReadable()){
					MetadataReader reader = meataDataReader.getMetadataReader(r);
					
					String className = reader.getClassMetadata().getClassName();
					
					ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(reader);
					sbd.setResource(r);
					sbd.setSource(r);
					System.out.println("=======>"+sbd.getBeanClassName());
					set.add(sbd);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}
	
	/**
	 * Resolve the specified base package into a pattern specification for
	 * the package search path.
	 * <p>The default implementation resolves placeholders against system properties,
	 * and converts a "."-based package path to a "/"-based resource path.
	 * @param basePackage the base package as specified by the user
	 * @return the pattern specification to be used for package searching
	 */
	protected String resolveBasePackage(String basePackage) {
		String resolvePlaceHodlerBasePackage = SystemPropertyUtils.resolvePlaceholders(basePackage);
		return ClassUtils.convertClassNameToResourcePath(resolvePlaceHodlerBasePackage);
	}
}
