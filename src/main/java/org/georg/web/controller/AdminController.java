package org.georg.web.controller;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.FileService;
import org.georg.web.impl.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
@SessionAttributes
public class AdminController {

    @Autowired
    private FileService fileService;

    @Autowired
    private GalleryService galleryService;

    private void constructMenu(ModelAndView modelAndView) {
        SortedMap<String, String> menu = new TreeMap();
        menu.put("?page=gal", "page.menu.admin.gals");
        menu.put("?page=price", "page.menu.admin.price");
        menu.put("?page=system", "page.menu.admin.system");
        menu.put("?page=videos", "page.menu.admin.videos");
        modelAndView.addObject("menuItems", menu);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ModelAndView adminGet(@RequestParam(required = false) Boolean success, @RequestParam(required = true) String page)
            throws IOException {
        ModelAndView modelAndView;
        if ("admin".equals(page)) {
            modelAndView = new ModelAndView("admin/gallery");
        } else if ("price".equals(page)) {
            modelAndView = new ModelAndView("admin/price");
        } else {
            modelAndView = new ModelAndView("admin/gallery");
        }

        modelAndView.addObject("listDirectories", fileService.getDirectories());
        if (success != null) {
            modelAndView.addObject("success", success);
        }
        constructMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin", params = {"title"}, method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ModelAndView adminEditGallery(@RequestParam("title") String title) throws IOException {

        String realTitle = URLDecoder.decode(title, "UTF-8");
        ModelAndView modelAndView = new ModelAndView("edit_gallery");
        modelAndView.addObject("gallery", fileService.getDirectoryByTitle(realTitle));
        return modelAndView;
    }

    @RequestMapping(value = "/saveGallery", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ModelAndView adminPost(@ModelAttribute("gallery") Gallery gallery) throws IOException {
        galleryService.update(gallery);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin?page=gal");
        modelAndView.addObject("listDirectories", fileService.getDirectories());
        modelAndView.addObject("success", true);
        constructMenu(modelAndView);
        return modelAndView;
    }
}
