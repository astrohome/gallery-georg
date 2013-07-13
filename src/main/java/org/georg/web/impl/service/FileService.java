package org.georg.web.impl.service;

import org.georg.web.impl.model.Directory;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
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

    public List<Directory> getDirectories() {
        List<Directory> list = new ArrayList<>();

        for (File directory : fileUtils.findDirectories()) {
            list.add(new Directory(directory.getName()));
        }
        return list;
    }

    public List<Directory> getFiles(String dir) {
        List<Directory> list = new ArrayList<>();

        for (File directory : fileUtils.findFiles(dir)) {
            list.add(new Directory(directory.getName()));
        }
        return list;
    }
}
