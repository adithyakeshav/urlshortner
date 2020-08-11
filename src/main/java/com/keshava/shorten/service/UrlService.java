package com.keshava.shorten.service;

import com.keshava.shorten.repository.UrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    
    @Autowired
    UrlRepository urlRepository ;

}