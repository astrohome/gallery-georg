package org.georg.web.impl.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class FileUtils {

    @Value("${file.scan.directory}")
    private String path;

    @Value("${file.scan.extention}")
    private String extension;

    @Value("${file.thumbs.directory}")
    private String thumbsPath;

    @Value("${file.thumbs.strategy}")
    private String thumbsStrategy;

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

    public File findThumb(String dir, String name) {
        File root = new File(thumbsPath);

        File[] result = null;

        final String thumbName = getDigest(dir, name);
        result = root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile() && f.getName().equalsIgnoreCase(thumbName);
            }
        });

        if (result == null || result.length != 1) return null;
        return result[0];
    }

    public String getDigest(String dir, String name) {
        try {
            return Hex.encodeHexString(MessageDigest.getInstance(thumbsStrategy).digest((dir + "/" + name).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String getThumbsPath() {
        return thumbsPath;
    }

    public String getThumbNameWithPath(String dir, String name) {
        return getThumbsPath() + "/" + getDigest(dir, name);
    }

    protected String getExtension(String name) {
        String[] str = name.split("\\.");
        if (str.length > 1) {
            return str[str.length - 1];
        }

        return ""; //-- no extension
    }
}
