package org.georg.web.impl.service;

import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: felix
 * Date: 7/13/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileService {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private GalleryService galleryService;

    public List<Gallery> getDirectories() {
        List<Gallery> list = new ArrayList<>();

        for (File directory : fileUtils.findDirectories()) {
            if (galleryService.getByTitle(directory.getName()) != null) {
                list.add(galleryService.getByTitle(directory.getName()));
            } else {
                list.add(convert(directory));
            }
        }
        return list;
    }

    public Gallery getDirectoryByTitle(String title) {
        File file = null;
        try {
            file = fileUtils.findDirectoryByTitle(title);
        } catch (IOException ex) {
            System.out.append("ERROR");
        }

        return convert(file);
    }

    public Gallery convert(File file) {
        if (file != null) {
            return new Gallery(file.getName(), new Date(file.lastModified()));
        }
        return null;
    }


    /*
    public List<Directory> getFiles(String dir) {
        List<Directory> list = new ArrayList<>();

        for (File directory : fileUtils.findFiles(dir)) {
            list.add(new Directory(directory.getName()));
        }
        return list;
    }   */
}
