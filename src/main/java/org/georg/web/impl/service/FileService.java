package org.georg.web.impl.service;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private GalleryService galleryService;

    public List<Gallery> getDirectories() {
        List<Gallery> list = new ArrayList<>();

        try {
            for (Path directory : fileUtils.findDirectories()) {
                if (galleryService.getByTitle(String.valueOf(directory.getFileName())) != null) {
                    list.add(galleryService.getByTitle(String.valueOf(directory.getFileName())));
                } else {
                    list.add(convert(directory));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Gallery getDirectoryByTitle(String title) {
        Path file = null;
        try {
            file = fileUtils.getGalleryByName(title);
        } catch (IOException ex) {
            System.out.append("ERROR");
        }

        return convert(file);
    }

    public Gallery convert(Path file) {
        if (file != null) {
            try {
                return new Gallery(file.getFileName().toString(), new Date(fileUtils.getLastModified(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
