package org.georg.web.controller;

import org.georg.web.impl.dao.GalleryDAO;
import org.georg.web.impl.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private GalleryDAO dao;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("public");
        modelAndView.addObject("list", dao.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletResponse response, @RequestParam("dir") String dir) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin");
        if ("".equals(dir)) {
            modelAndView.addObject("listDirectories", fileService.getDirectories());
        } else {
            modelAndView.addObject("listDirectories", fileService.getFiles(dir));
        }
        return modelAndView;
    }
}
