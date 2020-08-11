package com.keshava.shorten.repository;

import com.keshava.shorten.entity.UrlShortener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository <UrlShortener, String>{
    
}