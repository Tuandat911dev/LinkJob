package vn.com.linkjob.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.com.linkjob.service.FileService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public String uploadSingleFile(@RequestParam("file") MultipartFile file,
                                   @RequestParam("folder") String folder) throws URISyntaxException, IOException {
        fileService.createUploadFolder(folder);
        fileService.store(file, folder);
        return file.getOriginalFilename() + folder;
    }
}
