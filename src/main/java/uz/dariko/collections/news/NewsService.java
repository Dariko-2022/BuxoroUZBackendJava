package uz.dariko.collections.news;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.govSphere.GovSphereRepository;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;
import uz.dariko.collections.sphere.Sphere;
import uz.dariko.collections.sphere.SphereRepository;
import uz.dariko.exception.exceptions.UniversalException;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService implements BaseService {
    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final EntityGetter entityGetter;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper,  EntityGetter entityGetter) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.entityGetter = entityGetter;
    }


    public ResponseEntity<?> getForHome() throws Exception{
        Optional<List<News>> newsByIsActualAndLimit = newsRepository.findNewsByIsActualAndLimit(true, 3);
        Optional<List<News>> newsByIsActualAndLimit1 = newsRepository.findNewsByIsActualAndLimit(false, 4);
        if(newsByIsActualAndLimit1.isPresent() & newsByIsActualAndLimit.isPresent()) {
            List<News> list = newsByIsActualAndLimit.get();
            list.addAll(newsByIsActualAndLimit1.get());

            return ResponseEntity.ok(list);
        }
        return null;
    }

    public ResponseEntity<?> create(NewsCreateDTO newsCreateDto) throws Exception {

        Sphere sphere = entityGetter.getSphere(newsCreateDto.getSphereID());
        GovSphere govSphere = entityGetter.getGovSphere(newsCreateDto.getGovSphereID());
        List<File> images = entityGetter.getFiles(newsCreateDto.getImageIDs());

        News news = newsMapper.fromCreateDto(newsCreateDto);
        news.setImages(images);
        news.setSphere(sphere);
        news.setGovSphere(govSphere);
        newsRepository.save(news);
        return ResponseEntity.status(201).body("saved");

    }


    public ResponseEntity<Data<NewsDTO>> getById(UUID id) {

        News news = entityGetter.getNews(id);
        news.setCountView(news.getCountView() + 1);
        News save = newsRepository.save(news);
        NewsDTO newsDTO = newsMapper.toDto(save);

        return ResponseEntity.ok(new Data<>(newsDTO));
    }

    public ResponseEntity<Data<NewsDTO>> update(NewsUpdateDTO dto) {
        News news0 = entityGetter.getNews(dto.getId());
        News news = newsMapper.fromUpdateDto(dto, news0);

        List<File> images = entityGetter.getFiles(dto.getImageIDs());

        Sphere sphere = entityGetter.getSphere(dto.getGovSphereID());
        GovSphere govSphere = entityGetter.getGovSphere(dto.getGovSphereID());
        news.setImages(images);
        news.setSphere(sphere);
        news.setGovSphere(govSphere);
        newsRepository.save(news);
        NewsDTO newsDTO = newsMapper.toDto(news);
        return ResponseEntity.ok(new Data<>(newsDTO));

    }
    public ResponseEntity<Data<Boolean>> delete(UUID id) {
        News news = entityGetter.getNews(id);
        news.setDeleted(true);
        newsRepository.save(news);
        return ResponseEntity.ok(new Data<>(true));

    }

    public ResponseEntity<Data<List<NewsDTO>>> get() {
        Optional<List<News>> allByDeleted = newsRepository.findAllByDeleted(false);
        if (allByDeleted.isEmpty()) {
            throw new UniversalException("Yangiliklar Topilmadi", HttpStatus.NOT_FOUND);
        }

        List<News> list = allByDeleted.get();
        List<NewsDTO> newsDTOS = newsMapper.toDto(list);

        return ResponseEntity.ok(new Data<>(newsDTOS));
    }










}
