package com.volyx.raf_fs;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;

public class RfFileSystem extends FileSystem {

    private FileSystemProvider provider;

    public RfFileSystem(FileSystemProvider provider) {
        this.provider = provider;
    }

    @Override
    public FileSystemProvider provider() {
        return provider;
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOpen() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isReadOnly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSeparator() {
        return File.separator;
    }

    @Override
    public Iterable<Path> getRootDirectories() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<FileStore> getFileStores() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path getPath(String first, String... more) {
        String var3;
        if(more.length == 0) {
            var3 = first;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            String[] var5 = more;
            int var6 = more.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String var8 = var5[var7];
                if(var8.length() > 0) {
                    if(sb.length() > 0) {
                        sb.append('/');
                    }

                    sb.append(var8);
                }
            }

            var3 = sb.toString();
        }

        return new RfPath(this);
    }

    @Override
    public PathMatcher getPathMatcher(String syntaxAndPattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WatchService newWatchService() throws IOException {
        throw new UnsupportedOperationException();
    }

    public SeekableByteChannel newByteChannel(byte[] resolvedPath, Set<? extends OpenOption> options, FileAttribute<?>[] attrs) {
        throw new UnsupportedOperationException();
    }
}
