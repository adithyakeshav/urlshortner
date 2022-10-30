package com.keshava.shorten.controller;

import com.keshava.shorten.entity.AuthenticationRequest;
import com.keshava.shorten.entity.AuthenticationResponse;
import com.keshava.shorten.entity.UrlShortener;
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