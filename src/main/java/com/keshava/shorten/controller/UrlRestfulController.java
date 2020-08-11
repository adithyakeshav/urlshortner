package com.keshava.shorten.controller;

import java.util.List;

import com.keshava.shorten.entity.UrlShortener;
import com.keshava.shorten.exceptionhandler.InvalidUrlException;
import com.keshava.shorten.exceptionhandler.UrlAlreadyTakenException;
import com.keshava.shorten.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlRestfulController {

    @Autowired
    UrlService urlService;

    @GetMapping("/urls")
    public List<UrlShortener> getUrls() {
        return urlService.getUrls();
    }

    @GetMapping("/url/{shorten}")
    public UrlShortener getExpandedUrl(@PathVariable String shorten) {
        return urlService.getExpandedUrl(shorten);
    }

    @PostMapping("/shorten")
    public UrlShortener shortenUrl(@RequestBody UrlShortener urlShortener)
            throws UrlAlreadyTakenException, InvalidUrlException {
        return urlService.shortenUrl(urlShortener);
    }

}