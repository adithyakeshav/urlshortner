package com.keshava.shorten.controller;

import com.keshava.shorten.entity.*;
import com.keshava.shorten.exceptionhandler.InvalidUrlException;
import com.keshava.shorten.exceptionhandler.UrlAlreadyTakenException;
import com.keshava.shorten.service.UrlService;
import com.keshava.shorten.service.UserService;
import com.keshava.shorten.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UrlRestfulController {

    @Autowired
    JwtUtil utils;

    @Autowired
    UrlService urlService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager manager;

    private UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Invalid Username or Password", ex);
        }
        String token = utils.generateToken(userService.loadUserByUsername(request.getUser()));
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @GetMapping("/urls")
    public List<UrlShortener> getUrls() {
        UserDetails userDetails = getUserDetails();
        return urlService.getUrls(userDetails.getUsername());
    }

    @GetMapping("/url/{shorten}")
    public UrlShortener getExpandedUrl(@PathVariable String shorten) {
        UserDetails userDetails = getUserDetails();
        return urlService.getExpandedUrl(new UrlIdentity(userDetails.getUsername(), shorten));
    }

    @PostMapping("/shorten")
    public UrlShortener shortenUrl(@RequestBody ShortenRequest shortenRequest)
            throws UrlAlreadyTakenException, InvalidUrlException {
        UserDetails userDetails = getUserDetails();
        UrlShortener shortener = new UrlShortener(
                new UrlIdentity(userDetails.getUsername(), shortenRequest.getShortString()),
                shortenRequest.getExpansionString()
        );
        return urlService.shortenUrl(shortener);
    }

}