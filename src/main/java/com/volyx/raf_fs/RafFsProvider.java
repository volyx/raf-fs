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
        toRfPath(var1).checkAccess(var2);
    }

    public void copy(Path var1, Path var2, CopyOption... var3) throws IOException {
        toRfPath(var1).copy(toRfPath(var2), var3);
    }

    public void createDirectory(Path var1, FileAttribute... var2) throws IOException {
        toRfPath(var1).createDirectory(var2);
    }

    public final void delete(Path var1) throws IOException {
        toRfPath(var1).delete();
    }

    public <V extends FileAttributeView> V getFileAttributeView(Path var1, Class<V> var2, LinkOption... var3) {
        return ZipFileAttributeView.get(toRfPath(var1), var2);
    }

    public FileStore getFileStore(Path var1) throws IOException {
        return toRfPath(var1).getFileStore();
    }

    public boolean isHidden(Path var1) {
        return toRfPath(var1).isHidden();
    }

    public boolean isSameFile(Path var1, Path var2) throws IOException {
        return toRfPath(var1).isSameFile(var2);
    }

    public void move(Path var1, Path var2, CopyOption... var3) throws IOException {
        toRfPath(var1).move(toRfPath(var2), var3);
    }

    public AsynchronousFileChannel newAsynchronousFileChannel(Path var1, Set<? extends OpenOption> var2, ExecutorService var3, FileAttribute... var4) throws IOException {
        throw new UnsupportedOperationException();
    }

    public SeekableByteChannel newByteChannel(Path var1, Set<? extends OpenOption> var2, FileAttribute... var3) throws IOException {
        return toRfPath(var1).newByteChannel(var2, var3);
    }

    public DirectoryStream<Path> newDirectoryStream(Path var1, DirectoryStream.Filter<? super Path> var2) throws IOException {
        return toRfPath(var1).newDirectoryStream(var2);
    }

    public FileChannel newFileChannel(Path var1, Set<? extends OpenOption> var2, FileAttribute... var3) throws IOException {
        return toRfPath(var1).newFileChannel(var2, var3);
    }

    public InputStream newInputStream(Path var1, OpenOption... var2) throws IOException {
        return toRfPath(var1).newInputStream(var2);
    }

    public OutputStream newOutputStream(Path var1, OpenOption... var2) throws IOException {
        return toRfPath(var1).newOutputStream(var2);
    }

    @Override
    public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
        return toRfPath(path).newByteChannel(options, attrs);
    }

    public <A extends BasicFileAttributes> A readAttributes(Path var1, Class<A> var2, LinkOption... var3) throws IOException {
        return var2 != BasicFileAttributes.class && var2 != ZipFileAttributes.class?null:toRfPath(var1).getAttributes();
    }

    public Map<String, Object> readAttributes(Path var1, String var2, LinkOption... var3) throws IOException {
        return toRfPath(var1).readAttributes(var2, var3);
    }

    public Path readSymbolicLink(Path var1) throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void setAttribute(Path var1, String var2, Object var3, LinkOption... var4) throws IOException {
        toRfPath(var1).setAttribute(var2, var3, var4);
    }

    public Path getPath(URI var1) {
        String var2 = var1.getSchemeSpecificPart();
        int var3 = var2.indexOf("!/");
        if(var3 == -1) {
            throw new IllegalArgumentException("URI: " + var1 + " does not contain path info ex. jar:file:/c:/foo.zip!/BAR");
        } else {
            return this.getFileSystem(var1).getPath(var2.substring(var3 + 1), new String[0]);
        }
    }



    static final RfPath toRfPath(Path var0) {
        if(var0 == null) {
            throw new NullPointerException();
        } else if(!(var0 instanceof ZipPath)) {
            throw new ProviderMismatchException();
        } else {
            return (RfPath)var0;
        }
    }
}
