package org.georg.web.impl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

@Component
public class FileUtils {

    @Value("${file.scandirectory}")
    private String path;

    @Value("${file.scanextention}")
    private String extension;

    public File[] findDirectories() {
        File root = new File(path);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory();
            }
        });
    }

    public File findDirectoryByTitle(final String title) throws IOException {
        File root = new File(path);
        File[] result = root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return title.equalsIgnoreCase(f.getName());
            }
        });
        if (result.length != 1) throw new IOException();
        return result[0];
    }

    public File[] findImages(String dir) {
        File root = new File(path + "/" + dir);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile() && getExtension(f.getName()).equalsIgnoreCase(extension);
            }
        });
    }

    public File[] findFile(String dir, final String name) {
        File root = new File(path + "/" + dir);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile() && f.getName().equalsIgnoreCase(name);
            }
        });
    }

    protected String getExtension(String name) {
        String[] str = name.split("\\.");
        if (str.length > 1) {
            return str[str.length - 1];
        }

        return ""; //-- no extension
    }
}
