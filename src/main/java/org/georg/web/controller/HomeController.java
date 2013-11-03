package org.georg.web.controller;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.model.OrderItem;
import org.georg.web.impl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private PaperTypeService paperTypeService;

    @Autowired
    private FormatService formatService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private VideoService videoService;

    private void constructPublicMenu(ModelAndView modelAndView) {
        LinkedHashMap<String, String> menu = new LinkedHashMap();
        menu.put("/", "page.menu.public.index");
        menu.put("/private", "page.menu.public.code");
        menu.put("/videos", "page.menu.public.video");
        modelAndView.addObject("menuItems", menu);
    }

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("public");
        modelAndView.addObject("list", galleryService.getAllByDate(true));
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/", params = {"id"}, method = RequestMethod.GET)
    public ModelAndView singleGallery(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("view_gallery");
        Gallery gal = galleryService.getById(id);
        modelAndView.addObject("gallery", gal);
        modelAndView.addObject("listImages", imageService.getImages(gal));
        modelAndView.addObject("prices", priceService.getAll());
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/download/{gallery}", produces = "application/octet-stream")
    @ResponseBody
    public byte[] getPublicDirectoryArchive(@PathVariable("gallery") String gallery, HttpServletResponse response)
            throws UnsupportedEncodingException {

        Gallery gal = galleryService.getByTitle(gallery);
        if (!gal.isWatermark()) {
            response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''"
                    + URLEncoder.encode(gallery.replace(' ', '_'), "UTF-8") + ".zip");
            response.addHeader("Content-Transfer-Encoding", "binary");
            response.addCookie(new Cookie("fileDownload", "true") {{
                setPath("/");
            }});
            response.addHeader("Cache-Control", "max-age=60, must-revalidate");
            return imageService.createAndGetArchive(gallery);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/getThumb/{folder}/{image}")
    @ResponseBody
    public byte[] getThumb(@PathVariable("folder") String folder,
                           @PathVariable("image") String image) throws UnsupportedEncodingException {
        return imageService.getThumb(URLDecoder.decode(folder, "UTF-8"), URLDecoder.decode(image, "UTF-8"));
    }

    @RequestMapping(value = "/getImage/{folder}/{image}")
    @ResponseBody
    public byte[] getBigImage(@PathVariable("folder") String folder,
                              @PathVariable("image") String image) throws UnsupportedEncodingException {
        Gallery gal = galleryService.getByTitle(folder);
        return imageService.getBig(URLDecoder.decode(folder, "UTF-8"), URLDecoder.decode(image, "UTF-8"),
                gal.isWatermark());
    }

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public ModelAndView showCodeAccessPage() {
        ModelAndView modelAndView = new ModelAndView("private");
        modelAndView.addObject("prices", priceService.getAll());
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/private", method = RequestMethod.POST)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ModelAndView singlePrivateGallery(HttpServletResponse response, @RequestParam("code") String code) {
        ModelAndView modelAndView = new ModelAndView("view_gallery");
        Gallery gal = galleryService.getByCode(code);
        modelAndView.addObject("gallery", gal);
        modelAndView.addObject("prices", priceService.getAll());
        modelAndView.addObject("listImages", imageService.getImages(gal));
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/private/download/{gallery}", produces = "application/octet-stream")
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public byte[] getDirectoryArchive(@PathVariable("gallery") String gallery, HttpServletResponse response)
            throws UnsupportedEncodingException {

        response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''"
                + URLEncoder.encode(gallery.replace(' ', '_'), "UTF-8") + ".zip");
        response.addHeader("Content-Transfer-Encoding", "binary");
        response.addCookie(new Cookie("fileDownload", "true") {{
            setPath("/");
        }});
        response.addHeader("Cache-Control", "max-age=60, must-revalidate");
        return imageService.createAndGetArchive(gallery);
    }

    @RequestMapping(value = "/putOrder", method = RequestMethod.POST)
    public ModelAndView putOrder(HttpServletResponse response, HttpServletRequest request) {
        Integer itemCount = Integer.valueOf(request.getParameter("itemCount"));

        Set<OrderItem> items = new HashSet<>();

        for (int i = 1; i <= itemCount; i++) {
            String itemName = request.getParameter("item_name_" + i);
            String gallery = itemName.split("/")[0];
            String photo = itemName.split("/")[1];
            Integer itemQuantity = Integer.valueOf(request.getParameter("item_quantity_" + i));
            String opt = request.getParameter("item_options_" + i);
            Integer formatId = Integer.valueOf(opt.split(",")[1].split(": ")[1]);
            Integer paperId = Integer.valueOf(opt.split(",")[2].split(": ")[1]);

            OrderItem item = new OrderItem(gallery, photo, formatService.getById(formatId), paperTypeService.getById(paperId), itemQuantity);
            items.add(orderItemService.updateItem(item));
        }

        ModelAndView modelAndView = new ModelAndView("order/view");
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @RequestMapping(value = "/videos")
    public ModelAndView videos(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("video");
        modelAndView.addObject("videoList", videoService.getAll());
        constructPublicMenu(modelAndView);
        return modelAndView;
    }
}
