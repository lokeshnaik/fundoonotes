package com.bridgelabz.fundoonotes.serviceimplementation;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.service.NoteSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class NoteSearchImplementation implements NoteSearch 
{

	String INDEX = "notes";
	String TYPE = "note";
	@Autowired
	private RestHighLevelClient client;
	@Autowired
	private ObjectMapper objectMapper;
		
	@Override
	@SuppressWarnings("unchecked")
	public String creatingNote(Notes note) throws IOException {
		Map<String, Object> documentMappper = objectMapper.convertValue(note, Map.class);		
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, String.valueOf(note.getNoteId())).source(documentMappper);
		System.out.println(note.getNoteId()+"------------");
		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		return indexResponse.getResult().name();
		
		
	}
	
	@Override
	public String updatingNote(Notes note) throws Exception {
		Notes notes = findNoteById(String.valueOf(note.getNoteId()));
		System.out.println(notes+"------------");
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, String.valueOf(notes.getNoteId()));

		@SuppressWarnings("unchecked")
		Map<String, Object> mapDoc = objectMapper.convertValue(note, Map.class);
		updateRequest.doc(mapDoc);

		UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
		return updateResponse.getResult().name();
		
		
    	}
	@Override
	public String deletingNote(String id) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
		DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);

		return response.getResult().name();
		
	}
	@Override
	public Notes findNoteById(String id) throws Exception {
			GetRequest getRequest = new GetRequest(INDEX, TYPE, id);

			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
			Map<String, Object> resultMap = getResponse.getSource();

			return objectMapper.convertValue(resultMap, Notes.class);

		
	}
	@Override
	public List<Notes> searchingByTitle(String text) {
		
		SearchRequest searchRequest = buildSearchRequest(INDEX, TYPE);
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(QueryBuilders.queryStringQuery(text).lenient(true).field("title").field("description"))
				.should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("title")
						.field("description"));
		searchSourceBuilder.query(query);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getSearchResult(searchResponse);
		
	}
	private List<Notes> getSearchResult(SearchResponse searchResponse) 
	{
		
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		List<Notes> notesDoc = new ArrayList<Notes>();
		if (searchHits.length > 0) {
			Arrays.stream(searchHits)
					.forEach(hit -> notesDoc.add(objectMapper.convertValue(hit.getSourceAsMap(), Notes.class)));
		}

		return notesDoc;
		
	}
	
	private SearchRequest buildSearchRequest(String index, String type) 
	{
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices(index);
		searchRequest.types(type);

		return searchRequest;
	
		
		
	}
	
}
