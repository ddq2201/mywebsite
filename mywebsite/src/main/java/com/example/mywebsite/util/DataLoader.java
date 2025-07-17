package com.example.mywebsite.util;

import com.example.mywebsite.model.MatchVideo;
import com.example.mywebsite.repository.MatchVideoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final MatchVideoRepository repo;

    public DataLoader(MatchVideoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repo.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/static/data/videos.json");
            List<MatchVideo> videos = Arrays.asList(
                    mapper.readValue(is, MatchVideo[].class)
            );
            repo.saveAll(videos);
        }
    }
}