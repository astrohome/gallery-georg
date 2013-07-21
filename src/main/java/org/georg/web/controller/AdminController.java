package org.georg.web.controller;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.FileService;
import org.georg.web.impl.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

@Controller
@SessionAttributes
public class AdminController {

    @Autowired
    private FileService fileService;

    @Autowired
    private GalleryService galleryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminGet(@RequestParam(required = false) Boolean success, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("listDirectories", fileService.getDirectories());
        if (success != null) {
            modelAndView.addObject("success", success);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin", params = {"title"}, method = RequestMethod.GET)
    public ModelAndView adminEditGallery(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam("title") String title) throws IOException {

        String realTitle = URLDecoder.decode(title, "UTF-8");
        ModelAndView modelAndView = new ModelAndView("edit_gallery");
        modelAndView.addObject("gallery", fileService.getDirectoryByTitle(realTitle));
        return modelAndView;
    }

    @RequestMapping(value = "/saveGallery", method = RequestMethod.POST)
    public ModelAndView adminPost(@ModelAttribute("gallery") Gallery gallery, BindingResult result) throws IOException {
        galleryService.update(gallery);
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("listDirectories", fileService.getDirectories());
        return new ModelAndView("redirect:/admin?success=true");
    }
}
