package lv.ctco.cukes.core.internal.resources;

import com.google.common.base.Joiner;
import com.google.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import lv.ctco.cukes.core.CukesRuntimeException;
import lv.ctco.cukes.core.internal.context.InflateContext;
import lv.ctco.cukes.core.internal.helpers.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@InflateContext
public class ResourceFileReader {

    @Inject
    FilePathService pathService;

    public String read(String path) {
        return Joiner.on("").join(readLines(path));
    }

    public List<String> readLines(String path) {
        try {
            InputStream inputStream = getResourceFile(path);
            return IOUtils.readLines(new InputStreamReader(inputStream));
        } catch (IOException e) {
            throw new CukesRuntimeException(e);
        }
    }

    public byte[] readBytes(String path) {
        try {
            InputStream inputStream = getResourceFile(path);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new CukesRuntimeException(e);
        }
    }

    public InputStream getResourceFile(String relativePath) throws FileNotFoundException {
        String normalizedPath = pathService.normalize(relativePath);
        if (normalizedPath.startsWith("classpath:")){
            // the resource is contained inside of a jar, or the path is in the class path
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return loader.getResourceAsStream(relativePath);
        } else {
            return new FileInputStream(new File(normalizedPath));
        }
    }
}
