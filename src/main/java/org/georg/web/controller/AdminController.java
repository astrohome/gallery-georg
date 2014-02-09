package org.georg.web.controller;

import org.georg.web.container.*;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.*;
import org.georg.web.impl.util.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
public class AdminController {

    @Log
    Logger logger;

    @Autowired
    private FileService fileService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private FormatService formatService;

    @Autowired
    private PaperTypeService paperTypeService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    private void constructMenu(ModelAndView modelAndView) {
        LinkedHashMap<String, String> menu = new LinkedHashMap<>();
        menu.put("?page=gal", "page.menu.admin.gals");
        menu.put("?page=format", "page.menu.admin.format");
        menu.put("?page=type", "page.menu.admin.type");
        menu.put("?page=price", "page.menu.admin.price");
        menu.put("?page=payment", "page.menu.admin.payment");
        menu.put("?page=system", "page.menu.admin.system");
        menu.put("?page=video", "page.menu.admin.videos");
        modelAndView.addObject("menuItems", menu);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ModelAndView adminGet(@RequestParam(required = false) Boolean success, @RequestParam(required = false) String page)
            throws IOException {
        ModelAndView modelAndView;

        if (page == null || page.isEmpty())
            page = "admin";

        switch (page) {
            case "admin":
                modelAndView = new ModelAndView("admin/gallery");
                modelAndView.addObject("listDirectories", fileService.getDirectories());
                break;
            case "format":
                modelAndView = new ModelAndView("admin/format");
                modelAndView.addObject("formatListContainer", new FormatListContainer(formatService.getAll("id", IGenericDAO.SortingTypes.asc)));
                break;
            case "type":
                modelAndView = new ModelAndView("admin/paper_type");
                modelAndView.addObject("paperTypeListContainer", new PaperTypeListContainer(paperTypeService.getAll("id", IGenericDAO.SortingTypes.asc)));
                break;
            case "price":
                modelAndView = new ModelAndView("admin/price");
                modelAndView.addObject("priceListContainer", new PriceListContainer(priceService.getAll()));
                modelAndView.addObject("formats", formatService.getAll("id", IGenericDAO.SortingTypes.asc));
                modelAndView.addObject("paperTypes", paperTypeService.getAll("id", IGenericDAO.SortingTypes.asc));
                break;
            case "payment":
                modelAndView = new ModelAndView("admin/payment");
                modelAndView.addObject("paymentMethods", new PaymentMethodListContainer(paymentMethodService.getAll("id", IGenericDAO.SortingTypes.asc)));
                break;
            case "video":
                modelAndView = new ModelAndView("admin/video");
                modelAndView.addObject("videoListContainer", new VideoListContainer(videoService.getAll("id", IGenericDAO.SortingTypes.asc)));
                break;
            case "galleryProcessing":
                modelAndView = new ModelAndView("admin/processing");
                break;
            default:
                modelAndView = new ModelAndView("admin/gallery");
                modelAndView.addObject("listDirectories", fileService.getDirectories());
                break;
        }
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
        modelAndView.addObject("gallery", galleryService.getByTitleFromDBorFileSystem(realTitle));
        return modelAndView;
    }

    @RequestMapping(value = "/saveGallery", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String adminPost(@ModelAttribute("gallery") Gallery gallery) throws IOException {
        final Gallery updatedGallery = galleryService.updateItem(gallery);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                imageService.generatePreviews(updatedGallery);
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(r);
        return "redirect:/admin?page=galleryProcessing";
    }

    @RequestMapping(value = "/deleteGallery", method = RequestMethod.GET, headers = "Accept=application/json")
    @Secured("ROLE_ADMIN")
    public ModelAndView adminDeleteGallery(@RequestParam("id") Integer id) {
        galleryService.deleteItem(Long.valueOf(id));
        ModelAndView modelAndView = new ModelAndView("redirect:/admin?page=gal");
        modelAndView.addObject("listDirectories", fileService.getDirectories());
        modelAndView.addObject("success", true);
        constructMenu(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/getProgress", method = RequestMethod.GET, headers = "Accept=application/json")
    @Secured("ROLE_ADMIN")
    public
    @ResponseBody
    int getProgress() {
        return imageService.getProgress();
    }

    @RequestMapping(value = "/getTotal", method = RequestMethod.GET, headers = "Accept=application/json")
    @Secured("ROLE_ADMIN")
    public
    @ResponseBody
    int getTotal() {
        return imageService.getTotal();
    }

    @RequestMapping(value = "/processingFinished")
    @Secured("ROLE_ADMIN")
    public ModelAndView processingFinished() {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin?page=gal");
        modelAndView.addObject("listDirectories", fileService.getDirectories());
        modelAndView.addObject("success", true);
        constructMenu(modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "/editformat", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String editFormatListContainer(@ModelAttribute FormatListContainer formatListContainer, HttpSession session) {
        FormatListContainer newFormatListContainer = new FormatListContainer(formatService.updateFromContainer(formatListContainer));

        session.setAttribute("formatListContainer", newFormatListContainer);
        return "redirect:/admin?page=format&success=true&count=" + newFormatListContainer.getList().size();
    }

    @RequestMapping(value = "/editpapertype", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String editPaperTypeListContainer(@ModelAttribute PaperTypeListContainer paperTypeListContainer, HttpSession session) {
        PaperTypeListContainer newFormatListContainer = new PaperTypeListContainer(paperTypeService.updateFromContainer(paperTypeListContainer));

        session.setAttribute("formatListContainer", newFormatListContainer);
        return "redirect:/admin?page=type&success=true&count=" + newFormatListContainer.getList().size();
    }

    @RequestMapping(value = "/editvideo", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String editVideoListContainer(@ModelAttribute VideoListContainer videoListContainer, HttpSession session) {
        VideoListContainer newVideoListContainer = new VideoListContainer(videoService.updateFromContainer(videoListContainer));

        session.setAttribute("videoListContainer", newVideoListContainer);
        return "redirect:/admin?page=video&success=true&count=" + newVideoListContainer.getList().size();
    }

    @RequestMapping(value = "/editprice", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String editPriceListContainer(@ModelAttribute PriceListContainer priceListContainer, HttpSession session) {
        PriceListContainer newPriceListContainer = new PriceListContainer(priceService.updateFromContainer(priceListContainer));

        session.setAttribute("priceListContainer", newPriceListContainer);
        return "redirect:/admin?page=price&success=true&count=" + newPriceListContainer.getList().size();
    }

    @RequestMapping(value = "/editpaymentmethod", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String editPaymentMethods(@ModelAttribute PaymentMethodListContainer paymentMethodListContainer, HttpSession session) {
        PaymentMethodListContainer newPaymentMethodListContainer = new PaymentMethodListContainer(paymentMethodService.updateFromContainer(paymentMethodListContainer));
        return "redirect:/admin?page=payment&success=true&count=" + newPaymentMethodListContainer.getList().size();
    }
}
