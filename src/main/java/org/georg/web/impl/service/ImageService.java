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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private FileUtils fileUtils;

    @Value("${gallery.delta}")
    private Integer delta;

    public Path getFile(String gallery, String image) {
        try {
            return fileUtils.getImage(gallery, image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] readImage(Path file) {
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] proceedFile(String gallery, String image, boolean big, boolean watermark) {
        byte[] imageInByte;
        try {
            String path = fileUtils.getThumbNameWithPath(gallery, image, big);

            BufferedImage img;
            if (big) {
                img = Thumbnails.of(getFile(gallery, image).toFile())
                        .size(800, 800)
                        .outputFormat(fileUtils.getExtension())
                        .asBufferedImage();
                if (watermark) {
                    BufferedImage watermarkImage = ImageIO.read(fileUtils.getWatermarkImage());

                    Watermark filter = new Watermark(Positions.CENTER, watermarkImage, 0.5f);
                    img = filter.apply(img);
                }
            } else {
                img = Thumbnails.of(getFile(gallery, image).toFile())
                        .size(150, 150)
                        .crop(Positions.CENTER)
                        .outputFormat(fileUtils.getExtension())
                        .outputQuality(0.4f)
                        .asBufferedImage();
            }

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(img, fileUtils.getExtension(), baos);
                baos.flush();
                imageInByte = baos.toByteArray();
            }

            File output = new File(path);
            output.createNewFile();

            try (FileOutputStream fo = new FileOutputStream(output)) {
                ImageIO.write(img, fileUtils.getExtension(), fo);
                fo.flush();
            }
        } catch (IOException e) {
            return null;
        }

        return imageInByte;
    }

    public byte[] getBig(String gallery, String image, boolean watermark) {
        Path file = null;
        try {
            file = fileUtils.getBigThumb(gallery, image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (file == null || !Files.exists(file)) {
            return proceedFile(gallery, image, true, watermark);
        }
        return readImage(file);
    }

    public byte[] getThumb(String gallery, String image) {
        Path file = null;
        try {
            file = fileUtils.getSmallThumb(gallery, image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (file == null || !Files.exists(file)) {
            return proceedFile(gallery, image, false, false);
        }
        return readImage(file);
    }

    public List<String> getImages(Gallery gallery, int page) {
        if (page <= 0) page = 0;

        List<Path> images = fileUtils.findImagesInDirectoryStartingFrom(gallery.getTitle(), page);
        List<String> result = new ArrayList<>();
        for (Path image : images) {
            result.add(image.getFileName().toString());
        }

        return result;
    }

    private Integer progress;
    private Integer imageCount;

    public void generatePreviews(Gallery gallery) {
        imageCount = getImagesCount(gallery);
        Integer pageSize = getPageSize();
        Integer pages = (int) Math.ceil(imageCount / (float) pageSize);

        for (int i = 0; i < pages; i++) {
            List<String> images = getImages(gallery, i);
            for (int j = 0; j < images.size(); j++) {
                getThumb(gallery.getTitle(), images.get(j));
                getBig(gallery.getTitle(), images.get(j), gallery.isWatermark());
                this.progress = (i * pageSize + j);
                System.out.println("-------- processing preview " + (i * pageSize + j) + " of " + imageCount);
            }
        }
    }

    public Integer getPageSize() {
        return this.delta;
    }

    public Integer getImagesCount(Gallery gallery) {
        return fileUtils.getCount(gallery.getTitle());
    }

    public byte[] createAndGetArchive(String gallery) {
        File file = fileUtils.downloadGallery(gallery);
        if (file == null)
            return null;
        return readImage(file.toPath());
    }

    public Integer getProgress() {
        return progress;
    }

    public Integer getTotal() {
        return imageCount;
    }
}
