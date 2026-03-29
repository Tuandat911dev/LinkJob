package vn.com.linkjob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.com.linkjob.dto.file.UploadFileResDTO;
import vn.com.linkjob.service.FileService;
import vn.com.linkjob.util.annotation.ApiMessage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @PostMapping
    @ApiMessage("Upload single file")
    public ResponseEntity<UploadFileResDTO> uploadSingleFile(@RequestParam("file") MultipartFile file,
                                                             @RequestParam("folder") String folder) throws URISyntaxException, IOException {
        fileService.createUploadFolder(folder);
        fileService.validateFile(file);
        String fileName = fileService.store(file, folder);

        return ResponseEntity.ok()
                .body(UploadFileResDTO.builder()
                        .fileName(fileName)
                        .uploadedAt(Instant.now())
                        .build());
    }
}
