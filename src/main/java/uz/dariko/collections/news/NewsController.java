package uz.dariko.collections.news;


import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> update(NewsUpdateDTO dto) {
        return service.update(dto);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("code") UUID code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(@PathVariable("code") UUID code) {
        return service.getById(code);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.get();
    }


    @GetMapping("getAll")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "size") int size,
            @RequestParam(name = "page") int page
    ) {
        return  service.getAll(PageRequest.of(page,size));
    }


    @GetMapping("getBySphere")
    public ResponseEntity<?> getBySphere(UUID uuid,boolean isDeleted) {
        return service.getBySphere(uuid,isDeleted);
    }

}
