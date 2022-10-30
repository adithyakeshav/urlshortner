package com.keshava.shorten.controller;

import com.keshava.shorten.entity.ShortenRequest;
import com.keshava.shorten.entity.UrlIdentity;
import com.keshava.shorten.entity.UrlShortener;
import com.keshava.shorten.service.UrlService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlWebController.class);

    @Autowired
    UrlService urlService;

    @GetMapping("/")
    public String homePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("urlShortener", new UrlShortener());
        model.addAttribute("urls", urlService.getUrls(userDetails.getUsername()));
        return "home";
    }

    @GetMapping("/{shortened}")
    public String gotoUrl(@PathVariable String shortened, RedirectAttributes redirectAttributes) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UrlShortener urlShortener = urlService.getExpandedUrl(new UrlIdentity(userDetails.getUsername(), shortened));
            LOGGER.info("Redirecting to the URL : " + urlShortener.getExpansionString());
            return "redirect:" + urlShortener.getExpansionString();
        } catch(Exception e) {
            LOGGER.error("Error while finding GoTo URL : " + e);
            redirectAttributes.addFlashAttribute("message", "ERROR ! " + e.getMessage());

            return "redirect:/";
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String postMethodName(@ModelAttribute("urlShortener") ShortenRequest shortenRequest, RedirectAttributes redirectAttributes) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UrlShortener shortener = new UrlShortener(
                    new UrlIdentity(userDetails.getUsername(), shortenRequest.getShortString()),
                    shortenRequest.getExpansionString()
            );

            urlService.shortenUrl(shortener);
            LOGGER.info("Shorten URL success");
        } catch(Exception e) {
            LOGGER.error("Error while shortening URL : " + e);
            redirectAttributes.addFlashAttribute("message", "ERROR ! Could not shorten the request URL !! " + e.getMessage());
            return "redirect:/";
        }
        
        return "redirect:/";
    }
    
}