package com.volyx.raf_fs;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

import static org.junit.Assert.*;


public class RafFsProviderTest {

    @Test
    public void test() throws IOException, URISyntaxException {
        FileSystem fileSystem = FileSystems.newFileSystem(new URI("rf:/hello"), Collections.emptyMap());
        FileSystemProvider provider = fileSystem.provider();
        provider.createDirectory(Paths.get("abc"));
//        Files.createFile(Paths.get());
    }

}