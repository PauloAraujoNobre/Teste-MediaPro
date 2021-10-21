package com.media.pro.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.media.pro.model.Competition;
import com.media.pro.model.CompetitionList;
import com.media.pro.model.Highlight;
import com.media.pro.model.HighlightExtend;
import com.media.pro.model.Videos;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
public class FootService {
	
	public CompetitionList requestFootApi() throws IOException {
		OkHttpClient client = new OkHttpClient();
		
		String url = "https://www.scorebat.com/video-api/v3/";
		
		Request request = new Request.Builder()
		    .url(url)
		    .get()
		    .build();
		try (Response response = client.newCall(request).execute()) {
			CompetitionList competitionList = new Gson().fromJson(response.body().string(),
					CompetitionList.class);
			
			return competitionList;
		} catch(IOException e) {
			log.info("Error request data from foot api");
			throw new IOException();
		}
	}
	
	public ArrayList<String> getCompetitionStringFromData() throws IOException {
		CompetitionList competitionList = new CompetitionList();
		
		try {
			competitionList = requestFootApi();
		} catch (IOException e) {
			log.info("Error request data from competitionBean");
			throw new IOException();
		}
		
		ArrayList<String> beanList = new ArrayList<String>();
		
		for (Competition competition : competitionList.getResponse()) {	
			beanList.add(competition.getCompetition());
		}
		
		return beanList;
	}
	
	public Highlight getHighlightFromData(String competitionName) throws IOException {
		CompetitionList competitionList = new CompetitionList();
		
		try {
			competitionList = requestFootApi();
		} catch (IOException e) {
			log.info("Error request data from competitionBean");
			throw new IOException();
		}
		
		for (Competition competition : competitionList.getResponse()) {
			if (competition.getCompetition().equalsIgnoreCase(competitionName)) {
				Highlight highlight = Highlight.builder()
						.competition(competition.getCompetition())
						.highlight_title(competition.getTitle())
						.build();
				
				return highlight;
			}
		}
		
		return null;
	}
	
	public HighlightExtend getHighlightExtendFromData(String competitionName, int highlightIndex)
			throws IOException {
		CompetitionList competitionList = new CompetitionList();
		
		try {
			competitionList = requestFootApi();
		} catch (IOException e) {
			log.info("Error request data from competitionBean");
			throw new IOException();
		}
		
		for (Competition competition : competitionList.getResponse()) {
			if (competition.getCompetition().equalsIgnoreCase(competitionName)) {
				List<String> listHighlightEmbed = new ArrayList<String>();
				
				for (Videos video : competition.getVideos()) {
					if (video.getTitle().equalsIgnoreCase("Highlights")) {						
						listHighlightEmbed.add(video.getEmbed());
					}
				}
				
				if (highlightIndex > listHighlightEmbed.size()) {
					log.info("This highlight index is greater than the amount of highlights");
					throw new IOException();
				}
				
				HighlightExtend highlightExtend = HighlightExtend.builder()
						.competition(competition.getCompetition())
						.highlight_title(competition.getTitle())
						.thumbnail_url(competition.getThumbnail())
						.highlight_embed(listHighlightEmbed.get(highlightIndex - 1))
						.build();
				
				return highlightExtend;
			}
		}
		
		return null;
	}
}
