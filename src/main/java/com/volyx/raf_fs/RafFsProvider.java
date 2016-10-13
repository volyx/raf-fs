package com.volyx.raf_fs;

import com.sun.nio.zipfs.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class RafFsProvider extends FileSystemProvider {

    private RfFileSystem fileSystem;

    public RafFsProvider() {

    }

    public RafFsProvider(FileSystemProvider provider) {
        super();
    }

    public String getScheme() {
        return "rf";
    }

    @Override
    public FileSystem newFileSystem(URI uri, Map<String, ?> map) throws IOException {
        fileSystem = new RfFileSystem(this);
        return fileSystem;
    }

    @Override
    public FileSystem getFileSystem(URI uri) {
        return fileSystem;
    }

    public void checkAccess(Path var1, AccessMode... var2) throws IOException {
//        toRfPath(var1).checkAccess(var2);
        throw new UnsupportedOperationException();
    }

    public void copy(Path var1, Path var2, CopyOption... var3) throws IOException {
//        toRfPath(var1).copy(toRfPath(var2), var3);
        throw new UnsupportedOperationException();
    }

    public void createDirectory(Path var1, FileAttribute... var2) throws IOException {
//        toRfPath(var1).createDirectory(var2);
        throw new UnsupportedOperationException();
    }

    public final void delete(Path var1) throws IOException {
//        toRfPath(var1).delete();
        throw new UnsupportedOperationException();
    }

    public <V extends FileAttributeView> V getFileAttributeView(Path var1, Class<V> var2, LinkOption... var3) {
//        return ZipFileAttributeView.get(toRfPath(var1), var2);
        throw new UnsupportedOperationException();
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException {
        return null;
    }

    @Override
    public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        return null;
    }

    @Override
    public void setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {

    }

    public FileStore getFileStore(Path var1) throws IOException {
//        return toRfPath(var1).getFileStore();
        throw new UnsupportedOperationException();
    }

    public boolean isHidden(Path var1) {
//        return toRfPath(var1).isHidden();
        throw new UnsupportedOperationException();
    }

    public boolean isSameFile(Path var1, Path var2) throws IOException {
//        return toRfPath(var1).isSameFile(var2);
        throw new UnsupportedOperationException();
    }

    public void move(Path var1, Path var2, CopyOption... var3) throws IOException {
//        toRfPath(var1).move(toRfPath(var2), var3);
        throw new UnsupportedOperationException();
    }

    public Path getPath(URI uri) {
        String var2 = uri.getSchemeSpecificPart();
        int var3 = var2.indexOf("!/");
        if(var3 == -1) {
            throw new IllegalArgumentException("URI: " + uri + " does not contain path info ex. jar:file:/c:/foo.zip!/BAR");
        } else {
            return this.getFileSystem(uri).getPath(var2.substring(var3 + 1), new String[0]);
        }
    }

    @Override
    public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
        return toRfPath(path).newByteChannel(options, attrs);
    }

    @Override
    public DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException {
        throw new UnsupportedOperationException();
    }


    private static RfPath toRfPath(Path var0) {
        if(var0 == null) {
            throw new NullPointerException();
        } else if(!(var0 instanceof RfPath)) {
            throw new ProviderMismatchException();
        } else {
            return (RfPath)var0;
        }
    }
}
