package uz.dariko.collections.news;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.news.dto.NewsCreateDto;

@RestController
@RequestMapping("api/admin/news")
public class NewsController extends AbstractController<NewsService> {

    public NewsController(NewsService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody NewsCreateDto newsCreateDto
    ){
        
        return service.create(newsCreateDto);

    }

}
