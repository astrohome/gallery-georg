package org.georg.web.impl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

@Component
public class FileUtils {

    @Value("${file.scan.directory}")
    private String path;

    @Value("${file.scan.extention}")
    private String extension;

    @Value("${file.thumbs.directory}")
    private String thumbsPath;

    @Autowired
    private HashCodeUtil hashCodeUtil;

    public File[] findDirectories() {
        File root = new File(path);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory();
            }
        });
    }

    public String getPath() {
        return path;
    }

    public String getPath(String file) {
        return path + "/" + file;
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

    public File[] findFiles(String dir, final String name) {
        File root = new File(path + "/" + dir);
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile() && f.getName().equalsIgnoreCase(name);
            }
        });
    }

    public File findThumb(String dir, String name) {
        return getImage(dir, name, false);
    }

    public File findBig(String dir, String name) {
        return getImage(dir, name, true);
    }

    private File getImage(String dir, String name, boolean big) {
        File root = new File(thumbsPath);

        File[] result = null;

        final String thumbName = ((big) ? "big" : "") + hashCodeUtil.getDigest(dir + "/" + name);
        result = root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile() && f.getName().equalsIgnoreCase(thumbName);
            }
        });

        if (result == null || result.length != 1) return null;
        return result[0];
    }

    public String getThumbsPath() {
        return thumbsPath;
    }

    public String getThumbNameWithPath(String dir, String name, boolean big) {
        return getThumbsPath() + "/" + ((big) ? "big" : "") + hashCodeUtil.getDigest(dir + "/" + name);
    }

    protected String getExtension(String name) {
        String[] str = name.split("\\.");
        if (str.length > 1) {
            return str[str.length - 1];
        }

        return ""; //-- no extension
    }
}
