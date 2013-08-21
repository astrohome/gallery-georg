package org.georg.web.controller;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.GalleryService;
import org.georg.web.impl.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class HomeController {

    @Autowired
    private GalleryService service;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("public");
        modelAndView.addObject("list", service.getAllByDate(false));
        return modelAndView;
    }

    @RequestMapping(value = "/", params = {"id"}, method = RequestMethod.GET)
    public ModelAndView singleGallery(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("view_gallery");
        Gallery gal = service.getById(id);
        modelAndView.addObject("gallery", gal);
        modelAndView.addObject("listImages", imageService.getImages(gal));
        return modelAndView;
    }

    @RequestMapping(value = "/getImage/{folder}/{image}")
    @ResponseBody
    public byte[] helloWorld(@PathVariable("folder") String folder,
                             @PathVariable("image") String image) throws UnsupportedEncodingException {
        return imageService.getThumb(URLDecoder.decode(folder, "UTF-8"), URLDecoder.decode(image, "UTF-8"));
    }

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String showCodeAccessPage() {
        return "private";
    }

    @RequestMapping(value = "/private", method = RequestMethod.POST)
    @Secured("ROLE_USER")
    public ModelAndView singlePrivateGallery(HttpServletResponse response, @RequestParam("code") String code) {
        ModelAndView modelAndView = new ModelAndView("view_gallery");
        Gallery gal = service.getByCode(code);
        modelAndView.addObject("gallery", gal);
        modelAndView.addObject("listImages", imageService.getImages(gal));

        return modelAndView;
    }
}
