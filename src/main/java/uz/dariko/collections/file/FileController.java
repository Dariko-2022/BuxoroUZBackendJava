package uz.dariko.collections.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.response.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController extends AbstractController<FileService> {

    public FileController(FileService service) {
        super(service);
    }

    @PostMapping("/upload/{orgId}")
    private ResponseEntity<Data<UUID>> upload(@PathVariable String orgId, MultipartHttpServletRequest request) throws IOException {
        return service.upload(orgId, request);
    }


    @GetMapping("/view/{generatedName}")
    private ResponseEntity<InputStreamResource> viewFile(@PathVariable String generatedName) throws FileNotFoundException {
        return service.viewFile(generatedName);
    }


    @GetMapping("/download/{generatedName}")
    private ResponseEntity<Data<String>> download(HttpServletResponse response, @PathVariable String generatedName) throws IOException {
        return service.download(response, generatedName);
    }
}
