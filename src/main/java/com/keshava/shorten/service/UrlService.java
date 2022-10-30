package com.keshava.shorten.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.keshava.shorten.entity.UrlIdentity;
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

    public List<UrlShortener> getUrls(String userName) {
        LOGGER.info("Getting URLs from the repository");

        return urlRepository.findByUrlIdentityUser(userName);
    }

    public UrlShortener getExpandedUrl(UrlIdentity key) throws ResourceNotFoundException {
        return urlRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("Handle Not Found : /" + key));
    }

    public UrlShortener shortenUrl(UrlShortener urlShortener) throws UrlAlreadyTakenException,InvalidUrlException {
        if (urlRepository.findById(urlShortener.getId()).orElse(null) != null) {
            LOGGER.error("URL is already taken : " + urlShortener.getExpansionString());
            throw new UrlAlreadyTakenException("Url '" + urlShortener.getId() + "' already taken");
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