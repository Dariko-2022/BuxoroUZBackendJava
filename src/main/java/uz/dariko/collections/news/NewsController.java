package uz.dariko.collections.news;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("news/")
public class NewsController extends AbstractController<NewsService> implements GenericCRUDController<NewsCreateDTO, NewsUpdateDTO, NewsDTO, UUID> {

    public NewsController(NewsService service) {
        super(service);
    }


    @GetMapping("getForHome")
    public ResponseEntity<?> getForHome() throws Exception {
        return service.getForHome();
    }

    @Override
    public ResponseEntity<?> create (NewsCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<Data<NewsDTO>> update(NewsUpdateDTO dto) {
        return service.update(dto);
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(UUID id) {
        return service.delete(id);
    }

    @Override
    public ResponseEntity<Data<NewsDTO>> get(UUID code) {
        return service.getById(code);
    }

    @Override
    public ResponseEntity<Data<List<NewsDTO>>> list() {
        return service.get();
    }



}
