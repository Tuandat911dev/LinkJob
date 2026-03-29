package vn.com.linkjob.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {
    @Value("${app.upload-file.base-uri}")
    private String baseURI;

    public void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.FILE_NOT_EMPTY);
        }

        List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx");
        boolean isValidExtension =
                allowedExtensions.stream()
                        .anyMatch((item) -> Objects.requireNonNull(
                                file.getOriginalFilename()).toLowerCase().endsWith(item)
                        );

        if (!isValidExtension) {
            throw new AppException(ErrorCode.FILE_EXTENSION_INVALID);
        }

        long maxSize = 50 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new AppException(ErrorCode.FILE_TOO_LARGE);
        }
    }

    public void createUploadFolder(String folder) throws URISyntaxException {
        URI uri = new URI(baseURI + folder);
        Path path = Paths.get(uri);
        File tmpDir = new File(path.toString());
        if (!tmpDir.isDirectory()) {
            try {
                Files.createDirectory(tmpDir.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String store(MultipartFile file, String folder) throws URISyntaxException, IOException {
        String finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        URI uri = new URI(baseURI + folder + "/" + finalName);
        Path path = Paths.get(uri);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }

        return finalName;
    }
}
