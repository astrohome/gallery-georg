package org.georg.web.impl.service;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private FileUtils fileUtils;

    public File getFile(String gallery, String image) {
        File[] files = fileUtils.findFile(gallery, image + ".jpg");
        if (files.length == 1) {
            return files[0];
        }
        return null;
    }

    public byte[] getImage(File file) {
        byte[] result = new byte[(int) file.length()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(result);
        } catch (Exception ex) {
            System.out.println("GET IMAGE PROBLEM :: " + ex);
            ex.printStackTrace();
        }
        return result;
    }

    public List<String> getImages(Gallery gallery) {
        File[] images = fileUtils.findImages(gallery.getTitle());
        List<String> result = new ArrayList<>();
        for (File image : images) {
            result.add(image.getName().toLowerCase());
        }

        return result;
    }
}
