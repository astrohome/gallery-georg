package org.georg.web.controller;

import org.georg.web.container.FormatListContainer;
import org.georg.web.container.PaperTypeListContainer;
import org.georg.web.container.PriceListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    private FileService fileService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private FormatService formatService;

    @Autowired
    private PaperTypeService paperTypeService;

    @Autowired
    private PriceService priceService;

    private void constructMenu(ModelAndView modelAndView) {
        LinkedHashMap<String, String> menu = new LinkedHashMap();
        menu.put("?page=gal", "page.menu.admin.gals");
        menu.put("?page=format", "page.menu.admin.format");
        menu.put("?page=type", "page.menu.admin.type");
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
        } else if ("format".equals(page)) {
            modelAndView = new ModelAndView("admin/format");
            modelAndView.addObject("formatListContainer", new FormatListContainer(formatService.getAll("id", IGenericDAO.SortingTypes.asc)));
        } else if ("type".equals(page)) {
            modelAndView = new ModelAndView("admin/paper_type");
            modelAndView.addObject("paperTypeListContainer", new PaperTypeListContainer(paperTypeService.getAll("id", IGenericDAO.SortingTypes.asc)));
        } else if ("price".equals(page)) {
            modelAndView = new ModelAndView("admin/price");
            modelAndView.addObject("priceListContainer", new PriceListContainer(priceService.getAll()));
            modelAndView.addObject("formats", formatService.getAll("id", IGenericDAO.SortingTypes.asc));
            modelAndView.addObject("paperTypes", paperTypeService.getAll("id", IGenericDAO.SortingTypes.asc));
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

    @RequestMapping(value = "/editformat", method = RequestMethod.POST)
    public String editFormatListContainer(@ModelAttribute FormatListContainer formatListContainer, HttpSession session) {
        FormatListContainer newFormatListContainer = new FormatListContainer(formatService.updateFromContainer(formatListContainer));

        session.setAttribute("formatListContainer", newFormatListContainer);
        return "redirect:/admin?page=format&success=true&count=" + newFormatListContainer.getList().size();
    }

    @RequestMapping(value = "/editpapertype", method = RequestMethod.POST)
    public String editPaperTypeListContainer(@ModelAttribute PaperTypeListContainer paperTypeListContainer, HttpSession session) {
        PaperTypeListContainer newFormatListContainer = new PaperTypeListContainer(paperTypeService.updateFromContainer(paperTypeListContainer));

        session.setAttribute("formatListContainer", newFormatListContainer);
        return "redirect:/admin?page=type&success=true&count=" + newFormatListContainer.getList().size();
    }

    @RequestMapping(value = "/editprice", method = RequestMethod.POST)
    public String editPriceListContainer(@ModelAttribute PriceListContainer priceListContainer, HttpSession session) {
        PriceListContainer newPriceListContainer = new PriceListContainer(priceService.updateFromContainer(priceListContainer));

        session.setAttribute("priceListContainer", newPriceListContainer);
        return "redirect:/admin?page=price&success=true&count=" + newPriceListContainer.getList().size();
    }

    public class IntPropertyEditor extends PropertyEditorSupport {
        public String getAsText() {
            Integer value = (Integer) getValue();
            if (value == null) {
                return "";
            } else {
                return value.toString();
            }
        }

        public void setAsText(String value) {
            if (value != null && value.length() > 0) {
                setValue(Integer.valueOf(value));
            } else {
                setValue(null);
            }
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, new IntPropertyEditor());
        //binder.setAllowedFields("prop1", "prop2", ...);
        binder.setDisallowedFields("id", "*Id"); //wild-card pattern
        //binder.registerCustomEditor(String.class, new StringPropertyEditor(String.class, true));
    }
}
