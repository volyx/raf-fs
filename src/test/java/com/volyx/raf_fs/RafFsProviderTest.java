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
        Path tempFile = Files.createTempFile("abc", "tmp");
        System.out.println(tempFile.toAbsolutePath());
        FileSystem fileSystem = FileSystems.newFileSystem(URI.create("rf:foo/"), Collections.emptyMap() );

        Files.createFile(Paths.get(new URI("rf:file:" + tempFile.toAbsolutePath() + "!/ok")));
    }

}