//package com.test.solr;
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//
//public class CrmSolrClient{
//	
//	private static final String BASE_URL = "http://localhost:8983/solr/crm_clue";
//	
//	private static SolrClient solrClient;
//	
//	private CrmSolrClient(){
//		
//	}
//	
//	public static SolrClient getHttpClientInstance(){
//		solrClient = new HttpSolrClient(BASE_URL);
//		return solrClient;
//	}
//	
//}
