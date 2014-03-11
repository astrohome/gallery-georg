package org.georg.web.controller;

import org.georg.web.container.OrderItemContainer;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.model.Order;
import org.georg.web.impl.model.OrderItem;
import org.georg.web.impl.model.User;
import org.georg.web.impl.service.*;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private OrderService orderService;

    @Value("${gallery.delta}")
    private Integer delta;

    @Autowired
    private FileUtils fileUtils;

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

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView singleGallery(@RequestParam("id") Integer id, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        ModelAndView modelAndView = new ModelAndView("view_gallery");
        Gallery gal = galleryService.getById(id);
        modelAndView.addObject("gallery", gal);

        int total = imageService.getImagesCount(gal);
        int pages = 1;

        if (total / delta > 1) {
            pages = (int) Math.ceil(total / (float) delta);
        }

        if (page <= 0)
            page = 1;
        if (page > pages)
            page = pages;

        modelAndView.addObject("pages", pages);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("listImages", imageService.getImages(gal, page));
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
        return imageService.getThumb(URLDecoder.decode(folder, "UTF-8"), URLDecoder.decode(image, "UTF-8") + fileUtils.getFormattedExtension());
    }

    @RequestMapping(value = "/getImage/{folder}/{image}")
    @ResponseBody
    public byte[] getBigImage(@PathVariable("folder") String folder,
                              @PathVariable("image") String image) throws UnsupportedEncodingException {
        Gallery gal = galleryService.getByTitle(folder);
        return imageService.getBig(URLDecoder.decode(folder, "UTF-8"), URLDecoder.decode(image, "UTF-8") + fileUtils.getFormattedExtension(),
                gal.isWatermark());
    }

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public ModelAndView showCodeAccessPage() {
        ModelAndView modelAndView = new ModelAndView("private");
        modelAndView.addObject("prices", priceService.getAll());
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ModelAndView singlePrivateGallery(HttpServletResponse response,
                                             @RequestParam("code") String code,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page) {

        ModelAndView modelAndView = new ModelAndView("view_gallery");
        Gallery gal = galleryService.getByCode(code);

        int total = imageService.getImagesCount(gal);
        int pages = 1;

        if (total / delta > 1) {
            pages = (int) Math.ceil(total / (float) delta);
        }

        if (page <= 0)
            page = 1;
        if (page > pages)
            page = pages;

        modelAndView.addObject("pages", pages);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("gallery", gal);
        modelAndView.addObject("prices", priceService.getAll());
        modelAndView.addObject("listImages", imageService.getImages(gal, page));
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
    @Secured({"ROLE_USER"})
    public ModelAndView putOrder(HttpServletResponse response, HttpServletRequest request) {

        Integer itemCount = Integer.valueOf(request.getParameter("itemCount"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<OrderItem> items = new ArrayList<>();

        for (int i = 1; i <= itemCount; i++) {
            String itemName = request.getParameter("item_name_" + i);
            String gallery = itemName.split("/")[0];
            String photo = itemName.split("/")[1];
            Integer itemQuantity = Integer.valueOf(request.getParameter("item_quantity_" + i));
            String opt = request.getParameter("item_options_" + i);
            Integer formatId = Integer.valueOf(opt.split(",")[1].split(": ")[1]);
            Integer paperId = Integer.valueOf(opt.split(",")[2].split(": ")[1]);

            Gallery galleryDB = galleryService.getByTitle(gallery);

            OrderItem item = new OrderItem(photo, galleryDB, priceService.getById(formatId, paperId), itemQuantity, user);
            items.add(orderItemService.updateItem(item));
        }

        ModelAndView modelAndView = new ModelAndView("order/confirmation");
        modelAndView.addObject("container", new OrderItemContainer(items));
        return modelAndView;
    }

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    @Secured({"ROLE_USER"})
    public ModelAndView confirmOrder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<OrderItem> allConfirmedItems = orderItemService.findAllConfirmedItems(user);
        Order order = orderService.composeOrder(allConfirmedItems);

        ModelAndView modelAndView = new ModelAndView("order/finished");
        String orderDetails = order.getId() + ";" + user.getLogin();
        modelAndView.addObject("orderDetails", orderDetails);
        return modelAndView;
    }

    @RequestMapping(value = "/videos")
    public ModelAndView videos(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("video");
        modelAndView.addObject("videoList", videoService.getAll());
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/profile")
    @Secured("ROLE_USER")
    public ModelAndView profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        ModelAndView modelAndView = new ModelAndView("user/profile");
        List<Order> byUser = orderService.getByUser(user);
        modelAndView.addObject("orderListContainer", byUser);
        constructPublicMenu(modelAndView);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleNotFoundException(Exception ex) {

        ModelAndView model = new ModelAndView("service/404");
        return model;
    }
}
