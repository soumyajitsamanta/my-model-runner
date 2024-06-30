package model.loaders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReader {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    public static <T> T readFromJson(final Path configFile, Class<T> resultType) {
        return readFromJson(configFile.toFile(), resultType);
    }
    public static <T> T readFromJson(final File configFile, Class<T> resultType) {
        try {
            return JSON_MAPPER.readValue(configFile,
                    resultType);
        } catch (final StreamReadException e) {
            throw new RuntimeException("Reading config file content failed.",
                    e);
        } catch (final DatabindException e) {
            throw new RuntimeException("Creating config object failed.", e);
        } catch (final IOException e) {
            throw new RuntimeException("Opening, locating config file failed..",
                    e);
        }
    }
}
