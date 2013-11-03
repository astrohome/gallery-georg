package org.georg.web.impl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class FileUtils {

    @Value("${file.scan.directory}")
    private String path;

    @Value("${file.scan.extention}")
    private String extension;

    @Value("${file.thumbs.directory}")
    private String thumbsPath;

    @Value("${file.download.temp}")
    private String archivePath;

    @Value("${file.thumbs.watermark}")
    private String thumbsWatermark;

    @Autowired
    private HashCodeUtil hashCodeUtil;

    final static int BUFFER = 2048;

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

    public File downloadDirectory(String directory) {
        try {
            BufferedInputStream origin = null;

            String title = archivePath + "/" + Calendar.getInstance().getTimeInMillis() + ".zip";

            FileOutputStream dest =
                    new FileOutputStream(new File(title));

            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            byte data[] = new byte[BUFFER];

            File subDir = new File(path + "/" + directory);
            String subdirList[] = subDir.list();
            for (String sd : subdirList) {
                // get a list of files from current directory
                File f = new File(path + "/" + directory + "/" + sd);
                if (!f.isDirectory() && getExtension(f.getName()).equalsIgnoreCase(extension)) {
                    FileInputStream fi = new FileInputStream(f);
                    origin = new BufferedInputStream(fi, BUFFER);
                    ZipEntry entry = new ZipEntry(sd);
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER)) != -1) {
                        out.write(data, 0, count);
                        out.flush();
                    }
                }
            }
            origin.close();
            out.flush();
            out.close();

            File result = new File(title);
            return result;
        } catch (Exception e) {
            return null;

        }
    }

    public File getWatermarkImage() {
        File watermark = new File(thumbsWatermark);
        return watermark;
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
