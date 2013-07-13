package org.georg.web.impl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;

/**
 * Created with IntelliJ IDEA.
 * User: felix
 * Date: 7/13/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FileUtils {

    @Value("${file.scandirectory}")
    private String path;

    public File[] findDirectories() {
        File root = new File(path);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory();
            }
        });
    }

    public File[] findFiles(String dir) {
        File root = new File(path + "/" + dir);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile();
            }
        });
    }
}
