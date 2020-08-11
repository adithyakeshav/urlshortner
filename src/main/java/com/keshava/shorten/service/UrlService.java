package com.keshava.shorten.service;

import java.util.List;

import com.keshava.shorten.entity.UrlShortener;
import com.keshava.shorten.exceptionhandler.ResourceNotFoundException;
import com.keshava.shorten.exceptionhandler.UrlAlreadyTakenException;
import com.keshava.shorten.repository.UrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    
    @Autowired
    UrlRepository urlRepository ;

    public List<UrlShortener> getUrls() {
        return urlRepository.findAll();
    }

    public UrlShortener getExpandedUrl(String url) throws ResourceNotFoundException {
        return urlRepository.findById(url).orElseThrow(
            () -> new ResourceNotFoundException("Url Not Found") );
    }

    public UrlShortener shortenUrl( UrlShortener urlShortener) throws UrlAlreadyTakenException {
        if(urlRepository.findById(urlShortener.getShortString()).orElse(null) != null) {
            throw new UrlAlreadyTakenException("Url '" + urlShortener.getShortString() + "' already taken");
        }

        return urlRepository.save(urlShortener);
    }

}