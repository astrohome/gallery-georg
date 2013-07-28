package org.georg.web.impl.service;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.util.FileUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
        if (file == null) return null;

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

    public byte[] getThumb(String gallery, String image) {
        File file = fileUtils.findThumb(gallery, image);
        if (file == null) {
            byte[] imageInByte;
            try {
                BufferedImage img = ImageIO.read(getFile(gallery, image));
                BufferedImage thumbnail =
                        Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
                                200, 200, Scalr.OP_ANTIALIAS);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(thumbnail, "jpg", baos);
                baos.flush();
                imageInByte = baos.toByteArray();
                baos.close();

                File output = new File(fileUtils.getThumbNameWithPath(gallery, image));
                output.createNewFile();

                FileOutputStream fo = new FileOutputStream(output);

                ImageIO.write(thumbnail, "jpg", fo);
                fo.flush();
                fo.close();
            } catch (IOException e) {
                return null;
            }

            return imageInByte;
        }
        return getImage(file);
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
