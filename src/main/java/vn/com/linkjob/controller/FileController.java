package vn.com.linkjob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.linkjob.dto.file.DownloadFileResDTO;
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

    @GetMapping
    @ApiMessage("Download single file")
    public ResponseEntity<Resource> download(@RequestParam("fileName") String fileName,
                                             @RequestParam("folder") String folder)
            throws URISyntaxException, IOException {
        DownloadFileResDTO res = fileService.downloadFile(fileName, folder);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName)
                .contentLength(res.getFileLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(res.getResource());
    }
}
