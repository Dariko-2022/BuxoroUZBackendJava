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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController extends AbstractController<FileService> {

    public FileController(FileService service) {
        super(service);
    }

    @PostMapping("/uploads")
    private ResponseEntity<?> uploads(List<MultipartHttpServletRequest> requests) throws IOException {
        return service.uploads(requests);
    }


    @GetMapping("/view/{generatedName}")
    private ResponseEntity<InputStreamResource> viewFile(@PathVariable String generatedName) throws FileNotFoundException {
        return service.viewFile(generatedName);
    }

    @GetMapping("/view")
    private ResponseEntity<?> viewFile(@PathVariable List<String> generatedNames) throws IOException {
        return service.viewFiles(generatedNames);
    }


    @GetMapping("/download/{generatedName}")
    private ResponseEntity<Data<String>> download(HttpServletResponse response, @PathVariable String generatedName) throws IOException {
        return service.download(response, generatedName);
    }
}
