package com.keshava.shorten.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.keshava.shorten.entity.UrlShortener;
import com.keshava.shorten.exceptionhandler.InvalidUrlException;
import com.keshava.shorten.exceptionhandler.ResourceNotFoundException;
import com.keshava.shorten.exceptionhandler.UrlAlreadyTakenException;
import com.keshava.shorten.repository.UrlRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlService.class);

    @Autowired
    UrlRepository urlRepository;

    public List<UrlShortener> getUrls() {
        LOGGER.info("Getting URLs from the repository");
        return urlRepository.findAll();
    }

    public UrlShortener getExpandedUrl(String url) throws ResourceNotFoundException {
        return urlRepository.findById(url).orElseThrow(() -> new ResourceNotFoundException("Handle Not Found : /" + url));
    }

    public UrlShortener shortenUrl(UrlShortener urlShortener) throws UrlAlreadyTakenException,InvalidUrlException {
        if (urlRepository.findById(urlShortener.getShortString()).orElse(null) != null) {
            LOGGER.error("URL is already taken : " + urlShortener.getExpansionString());
            throw new UrlAlreadyTakenException("Url '" + urlShortener.getShortString() + "' already taken");
        }
        try {
            LOGGER.info("Validating if expansionString is valid URL");
            new URL(urlShortener.getExpansionString());
        } catch(MalformedURLException exception) {
            LOGGER.error("Invalid URL : " + urlShortener.getExpansionString());
            throw new InvalidUrlException("Invalid URL : " + urlShortener.getExpansionString());
        }
        LOGGER.info("Saving the shortcut to the repository");
        return urlRepository.save(urlShortener);
    }

}