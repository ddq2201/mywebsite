package com.example.mywebsite.service;

import com.example.mywebsite.config.YouTubeConfig;
import com.example.mywebsite.model.MatchVideo;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeService {
    private static final Logger logger = LogManager.getLogger(YouTubeService.class);
    private final YouTubeConfig config;

    public YouTubeService(YouTubeConfig config) {
        this.config = config;
    }

    public List<MatchVideo> fetchVideos(String query) throws IOException {
        logger.info("Fetching videos from YouTube for query: {}", query);
        YouTube youtube = new YouTube.Builder(
                new NetHttpTransport(),
                new JacksonFactory(),
                request -> {}
        ).setApplicationName("mywebsite").build();
        YouTube.Search.List search = youtube.search().list("snippet");
        search.setQ(query);
        search.setType("video");
        search.setMaxResults(5L);
        search.setKey(config.getApiKey());
        SearchListResponse response = search.execute();
        List<MatchVideo> results = getMatchVideos(response);
        logger.info("Fetched {} videos from YouTube", results.size());
        return results;
    }

    private static List<MatchVideo> getMatchVideos(SearchListResponse response) {
        List<MatchVideo> results = new ArrayList<>();
        if (response.getItems() != null) {
            for (SearchResult item : response.getItems()) {
                if (item.getId() != null && item.getId().getVideoId() != null) {
                    MatchVideo video = new MatchVideo();
                    video.setTitle(item.getSnippet().getTitle());
                    video.setDescription(item.getSnippet().getDescription());
                    video.setUrl("https://www.youtube.com/embed/" + item.getId().getVideoId());
                    results.add(video);
                }
            }
        }
        return results;
    }
}