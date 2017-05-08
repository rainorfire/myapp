//package com.test.solr;
//
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
//import java.util.List;
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocument;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
//
//public class SolrTest {
//
//	public static void main(String[] args) {
//		
////		SolrClient crmSolrClient = CrmSolrClient.getHttpClientInstance();
////		
////		ShClue shClue = new ShClue();
////		
////		SolrInputDocument solrInputDoc = new SolrInputDocument();
////		solrInputDoc.addField("id", 1);
////		solrInputDoc.addField("title", "Solr-这是我的第一条数据");
////		
////		try {
////			crmSolrClient.add(solrInputDoc);
////			
////			crmSolrClient.commit();
////		} catch (SolrServerException e) {
////			log.error("",e);
////		} catch (IOException e) {
////			log.error("",e);
////		}
//		
//		Path path = FileSystems.getDefault().getPath("E:\\Zoom File\\Lucene\\Solr\\solr-5.4.0\\solr-5.4.0\\server\\solr", "");
//		EmbeddedSolrServer emSolrServer = new EmbeddedSolrServer(path,"crm_clue");
//		
//		SolrQuery sq = new SolrQuery();
//		sq.set("q", "contactName:*6");
//		
//		try {
//			QueryResponse response = emSolrServer.query(sq);
//			SolrDocumentList solrDocList = response.getResults();
//			for(SolrDocument sdoc : solrDocList){
//				String id = sdoc.getFieldValue("clueId").toString();
//				String contactName = sdoc.getFieldValue("contactName").toString();
//				System.out.println("id="+id+",title="+contactName);
//			}
//		} catch (SolrServerException e) {
//			log.error("",e);
//		} catch (IOException e) {
//			log.error("",e);
//		}finally{
//			try {
//				emSolrServer.close();
//			} catch (IOException e) {
//				log.error("",e);
//			}
//		}
//	}
//
//}
