package uz.dariko.collections.news;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/news")
public class NewsController extends AbstractController<NewsService> implements GenericCRUDController<NewsCreateDTO, NewsUpdateDTO, NewsDTO, UUID> {

    public NewsController(NewsService service) {
        super(service);
    }


    @PostMapping("createWithPhoto")
    public ResponseEntity<?> create(NewsCreateDTO DTO, @RequestParam("image") MultipartHttpServletRequest multipartFile) throws Exception {
        return service.create(DTO,multipartFile);
    }


    @Override
    public ResponseEntity<?> create(NewsCreateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(NewsUpdateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<Data<NewsDTO>> get(UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<NewsDTO>>> list() {
        return null;
    }
}
