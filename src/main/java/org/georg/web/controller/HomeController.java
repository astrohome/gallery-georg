package org.georg.web.controller;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.GalleryService;
import org.georg.web.impl.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private GalleryService service;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("public");
        modelAndView.addObject("list", service.getAllByDate());
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
                             @PathVariable("image") String image) {
        File file = imageService.getFile(folder, image);
        return imageService.getImage(file);
    }
}
