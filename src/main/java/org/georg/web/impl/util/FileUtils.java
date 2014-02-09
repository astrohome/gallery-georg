package org.georg.web.impl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class FileUtils {

    final static int BUFFER = 2048;
    DirectoryStream.Filter<Path> imagesFilter = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path file) throws IOException {
            final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*." + extension);
            return (!Files.isDirectory(file) && matcher.matches(file));
        }
    };
    DirectoryStream.Filter<Path> directoryFilter = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path file) throws IOException {
            if (Files.isDirectory(file)) {
                return (findImages(file.getFileName().toString()).size() > 0);
            }
            return false;
        }
    };
    @Value("${file.scan.directory}")
    private String galleriesPath;
    @Value("${file.scan.extention}")
    private String extension;
    @Value("${file.thumbs.directory}")
    private String thumbsPath;
    @Value("${file.download.temp}")
    private String archivePath;
    @Value("${file.thumbs.watermark}")
    private String thumbsWatermark;
    @Value("${gallery.delta}")
    private Integer delta;
    @Autowired
    private HashCodeUtil hashCodeUtil;

    public String getFormattedExtension() {
        return "." + extension;
    }

    public List<Path> findDirectories() throws IOException {
        Path root = getFileOrDirectory(galleriesPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(root, directoryFilter)) {
            List<Path> result = new ArrayList<>();
            for (Path path : stream) {
                result.add(path);
            }
            return result;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Returns last modification time in ms.
     *
     * @param file
     * @return
     * @throws IOException
     */
    public long getLastModified(Path file) throws IOException {
        BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
        return attrs.lastModifiedTime().toMillis();
    }

    public File downloadGallery(String directory) {
        try {
            BufferedInputStream origin = null;

            String title = archivePath + "/" + Calendar.getInstance().getTimeInMillis() + ".zip";

            try (FileOutputStream dest =
                         new FileOutputStream(new File(title));
                 ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest))) {
                byte data[] = new byte[BUFFER];

                File subDir = new File(galleriesPath + "/" + directory);
                String subdirList[] = subDir.list();

                for (String sd : subdirList) {
                    // get a list of files from current directory
                    File f = new File(galleriesPath + "/" + directory + "/" + sd);
                    if (!f.isDirectory() /*&& getFormattedExtension(f.getName()).equalsIgnoreCase(extension)*/) {
                        try (FileInputStream fi = new FileInputStream(f)) {
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
                }
                origin.close();
                out.flush();
                out.close();
            }

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

    /**
     * Gets gallery by its name.
     *
     * @param galleryName
     * @return
     * @throws IOException
     */
    public Path getGalleryByName(final String galleryName) throws IOException {
        return getFileOrDirectory(galleriesPath, galleryName);
    }

    /**
     * Finds all images (with given extension)
     *
     * @param galleryName directory imageName
     * @param start       pagination start
     * @return list of images, null if nothing found
     */
    public List<Path> findImagesInDirectoryStartingFrom(String galleryName, int start) {

        if (start < 0 || galleryName == null || galleryName.isEmpty()) return null;

        List<Path> files = findImages(galleryName);

        if (files.size() > 0) {
            Collections.sort(files, new FileNameSorter());

            if (files.size() > delta) {
                return files.subList((start - 1) * delta,
                        ((start) * delta) > files.size() ? files.size() : (start) * delta);
            }

            return files;
        }

        return null;
    }

    /**
     * Internal method for listing all images.
     *
     * @param galleryName
     * @return
     */
    private List<Path> findImages(String galleryName) {
        Path baseDir;
        try {
            baseDir = getFileOrDirectory(galleriesPath, galleryName);
        } catch (FileNotFoundException e) {
            //TODO: add logger
            return null;
        }

        try (DirectoryStream<Path> stream
                     = Files.newDirectoryStream(baseDir, imagesFilter)) {
            List<Path> files = new ArrayList<>();

            for (Path file : stream) {
                files.add(file);
            }
            return files;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Return total number of images.
     *
     * @param dir
     * @return
     */
    public Integer getCount(String dir) {
        return findImages(dir).size();
    }

    /**
     * Gets single image for gallery
     *
     * @param galleryName
     * @param imageName
     * @return
     */
    public Path getImage(String galleryName, final String imageName) throws FileNotFoundException {
        return getFileOrDirectory(galleriesPath, galleryName, imageName);
    }

    /**
     * Get small thumb for image in gallery.
     *
     * @param galleryName
     * @param imageName
     * @return
     */
    public Path getSmallThumb(String galleryName, String imageName) throws FileNotFoundException {
        return getThumb(galleryName, imageName, false);
    }

    /**
     * Get big thumb for image in gallery.
     *
     * @param galleryName
     * @param imageName
     * @return
     */
    public Path getBigThumb(String galleryName, String imageName) throws FileNotFoundException {
        return getThumb(galleryName, imageName, true);
    }

    /**
     * Internal method for getting
     *
     * @param galleryName
     * @param imageName
     * @param big
     * @return
     */
    private Path getThumb(String galleryName, String imageName, boolean big) throws FileNotFoundException {
        Path thumb = getFileOrDirectory(thumbsPath, false,
                ((big) ? "big" : "") + hashCodeUtil.getDigest(galleryName + "/" + imageName) + getFormattedExtension());
        return thumb;
    }

    /**
     * Internal universal method for getting file or directory.
     *
     * @param basePath
     * @param paths
     * @return
     * @throws FileNotFoundException
     */
    private Path getFileOrDirectory(String basePath, String... paths) throws FileNotFoundException {
        return getFileOrDirectory(basePath, true, paths);
    }

    private Path getFileOrDirectory(String basePath, boolean returnOnlyIfExists, String... paths) throws FileNotFoundException {
        Path directory = FileSystems.getDefault().getPath(basePath, paths);
        if (returnOnlyIfExists) {
            if (Files.exists(directory)) {
                return directory;
            }

            throw new FileNotFoundException("File or directory not found");
        } else return directory;
    }

    public String getThumbNameWithPath(String galleryName, String imageName, boolean big) throws FileNotFoundException {
        return getThumb(galleryName, imageName, big).toString();
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    private class FileNameSorter implements Comparator<Path> {
        @Override
        public int compare(Path o1, Path o2) {
            if (o1.getFileName().toString().length() > o2.getFileName().toString().length()) {
                return 1;
            } else if (o1.getFileName().toString().length() < o2.getFileName().toString().length()) {
                return -1;
            }
            return o1.getFileName().toString().compareTo(o2.getFileName().toString());

        }
    }
}
