package com.example.mywebsite.repository;

import com.example.mywebsite.model.MatchVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchVideoRepository extends JpaRepository<MatchVideo, Long> {
    Page<MatchVideo> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title, String description, Pageable pageable
    );
    boolean existsByUrl(String url);
    Page<MatchVideo> findBySearchQueryContainingIgnoreCase(String query, Pageable pageable);
    Page<MatchVideo> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSearchQueryContainingIgnoreCase(
            String title, String desc, String searchKey, Pageable pageable);

}