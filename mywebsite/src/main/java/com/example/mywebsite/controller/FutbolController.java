package com.example.mywebsite.controller;

import com.example.mywebsite.model.MatchVideo;
import com.example.mywebsite.repository.MatchVideoRepository;
import com.example.mywebsite.service.YouTubeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class FutbolController {
    private static final Logger logger = LogManager.getLogger(FutbolController.class);
    private final YouTubeService youTubeService;
    private final MatchVideoRepository videoRepo;
    public FutbolController(MatchVideoRepository videoRepo, YouTubeService youTubeService) {
        this.videoRepo = videoRepo;
        this.youTubeService = youTubeService;
    }

    private static final int DEFAULT_PAGE_SIZE = 3;

    @GetMapping("/matches")
    public String viewMatches(@RequestParam(defaultValue = "") String query,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id"));
//        Page<MatchVideo> videoPage = videoRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
//                query, query, pageable);
//        Page<MatchVideo> videoPage = videoRepo.findBySearchQueryContainingIgnoreCase(query, pageable);
        Page<MatchVideo> videoPage = videoRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSearchQueryContainingIgnoreCase(
                query, query, query, pageable);

        logger.info("Query='{}' | Page={} | Total Matches={}", query, page, videoPage.getTotalElements());
        model.addAttribute("pageTitle", "Football Matches");
        model.addAttribute("query", query);
        model.addAttribute("videos", videoPage.getContent());
        model.addAttribute("totalVideos", videoPage.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", videoPage.getTotalPages());
        model.addAttribute("hasNext", page + 1 < videoPage.getTotalPages());
        model.addAttribute("hasPrevious", page > 0);
        model.addAttribute("contentFragment", "match");
        return "layout";
    }

    @GetMapping("/matches/new")
    public String showMatchForm(Model model) {
        model.addAttribute("pageTitle", "Add Match Highlight");
        model.addAttribute("video", new MatchVideo());
        model.addAttribute("contentFragment", "match_form");
        return "layout";
    }

    @PostMapping("/matches")
    public String addMatch(@ModelAttribute MatchVideo video,
                           @RequestParam(defaultValue = "") String query) throws UnsupportedEncodingException {
        video.setSearchQuery(query);
        videoRepo.save(video);
        return "redirect:/matches?query=" + URLEncoder.encode(query, String.valueOf(StandardCharsets.UTF_8));
    }

    @PostMapping("/matches/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoRepo.deleteById(id);
        return "redirect:/matches";
    }

    @GetMapping("/matches/sync")
    public String syncVideos(@RequestParam(defaultValue = "football highlights") String query) throws IOException {
        logger.info("Syncing videos for query: {}", query);
        List<MatchVideo> videos = youTubeService.fetchVideos(query);
        for (MatchVideo video : videos) {
            video.setSearchQuery(query);
            if (!videoRepo.existsByUrl(video.getUrl())) {
                videoRepo.save(video);
            }
        }
        return "redirect:/matches?query=" + URLEncoder.encode(query, String.valueOf(StandardCharsets.UTF_8));
    }

    @PostMapping("/matches/deleteAll")
    public String deleteAllMatches() {
        videoRepo.deleteAll();
        return "redirect:/matches";
    }
}