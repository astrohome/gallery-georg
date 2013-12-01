package org.georg.web.impl.service;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${gallery.delta}")
    private Integer delta;

    public File getFile(String gallery, String image) {
        File[] files = fileUtils.findFiles(gallery, image + ".jpg");
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

    private byte[] proceedFile(String gallery, String image, boolean big, boolean watermark) {
        byte[] imageInByte;
        try {
            String path = fileUtils.getThumbNameWithPath(gallery, image, big);

            BufferedImage img;
            if (big) {
                img = Thumbnails.of(getFile(gallery, image))
                        .size(800, 800)
                        .outputFormat("jpg")
                        .asBufferedImage();
                if (watermark) {
                    BufferedImage watermarkImage = ImageIO.read(fileUtils.getWatermarkImage());

                    Watermark filter = new Watermark(Positions.CENTER, watermarkImage, 0.5f);
                    img = filter.apply(img);
                }
            } else {
                img = Thumbnails.of(getFile(gallery, image))
                        .size(150, 150)
                        .crop(Positions.CENTER)
                        .outputFormat("jpg")
                        .outputQuality(0.4f)
                        .asBufferedImage();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

            File output = new File(path);
            output.createNewFile();

            FileOutputStream fo = new FileOutputStream(output);

            ImageIO.write(img, "jpg", fo);
            fo.flush();
            fo.close();
        } catch (IOException e) {
            return null;
        }

        return imageInByte;
    }

    public byte[] getBig(String gallery, String image, boolean watermark) {
        File file = fileUtils.findBig(gallery, image);
        if (file == null) {
            return proceedFile(gallery, image, true, watermark);
        }
        return getImage(file);
    }

    public byte[] getThumb(String gallery, String image) {
        File file = fileUtils.findThumb(gallery, image);
        if (file == null) {
            return proceedFile(gallery, image, false, false);
        }
        return getImage(file);
    }

    public List<String> getImages(Gallery gallery, int page) {
        if (page <= 0) page = 0;

        File[] images = fileUtils.findImages(gallery.getTitle(), page);
        List<String> result = new ArrayList<>();
        for (File image : images) {
            result.add(image.getName().toLowerCase());
        }

        return result;
    }

    public Integer getImagesCount(Gallery gallery) {
        return fileUtils.getCount(gallery.getTitle());
    }

    public byte[] createAndGetArchive(String gallery) {
        File file = fileUtils.downloadDirectory(gallery);
        if (file == null)
            return null;
        return getImage(file);
    }
}
