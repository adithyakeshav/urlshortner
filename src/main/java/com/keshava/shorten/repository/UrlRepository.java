package com.keshava.shorten.repository;

import com.keshava.shorten.entity.UrlIdentity;
import com.keshava.shorten.entity.UrlShortener;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository <UrlShortener, UrlIdentity>{
    List<UrlShortener> findByIdUsername(String user);
}