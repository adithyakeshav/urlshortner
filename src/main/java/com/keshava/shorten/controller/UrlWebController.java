package com.keshava.shorten.controller;

import com.keshava.shorten.entity.UrlShortener;
import com.keshava.shorten.exceptionhandler.InvalidUrlException;
import com.keshava.shorten.exceptionhandler.UrlAlreadyTakenException;
import com.keshava.shorten.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlWebController {

    @Autowired
    UrlService urlService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("urlShortener", new UrlShortener());
        model.addAttribute("urls", urlService.getUrls());
        return "home";
    }

    @GetMapping("/{shortened}")
    public RedirectView gotoUrl(@PathVariable String shortened) {
        UrlShortener urlShortener = urlService.getExpandedUrl(shortened);

        return new RedirectView(urlShortener.getExpansionString());
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public RedirectView postMethodName(@ModelAttribute("urlShortener") UrlShortener urlShortener)
            throws InvalidUrlException, UrlAlreadyTakenException {
        urlService.shortenUrl(urlShortener);
        
        return new RedirectView("/");
    }
    
}