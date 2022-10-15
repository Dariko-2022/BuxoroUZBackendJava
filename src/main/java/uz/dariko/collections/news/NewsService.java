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

    private final BaseUtils baseUtils;
    private final SphereRepository sphereRepository;
    private final GovSphereRepository govSphereRepository;

    private final EntityGetter entityGetter;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, BaseUtils baseUtils, SphereRepository sphereRepository, GovSphereRepository govSphereRepository, EntityGetter entityGetter) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.baseUtils = baseUtils;
        this.sphereRepository = sphereRepository;
        this.govSphereRepository = govSphereRepository;
        this.entityGetter = entityGetter;
    }


    public ResponseEntity<?> getForHome() throws Exception{
        List<News> list = newsRepository.findNewsByIsActualAndLimit(true,3);
        list.addAll(newsRepository.findNewsByIsActualAndLimit(false,4));
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> create(NewsCreateDTO newsCreateDto) throws Exception {

        Optional<Sphere> byId = sphereRepository.findById(UUID.fromString(newsCreateDto.getSphereID()));
        Optional<GovSphere> byGovId = govSphereRepository.findById(UUID.fromString(newsCreateDto.getGovSphereID()));
        List<UUID> uuids = baseUtils.parseUUID(newsCreateDto.getImageIDs());
        List<File> images = entityGetter.getFiles(uuids);

        if(byId.isPresent()) {
            Sphere sphere = byId.get();
            GovSphere govSphere = byGovId.get();

            News news = newsMapper.fromCreateDto(newsCreateDto);
            news.setImages(images);
            news.setSphere(sphere);
            news.setGovSphere(govSphere);
            newsRepository.save(news);
            return ResponseEntity.status(201).body("saved");
        }
        return ResponseEntity.badRequest().body("sphere not found");
    }


    public ResponseEntity<Data<NewsDTO>> getById(UUID code) {
        Optional<News> byId = newsRepository.findById(code);
        if (byId.isEmpty()) {
            throw new UniversalException("Yangilik Topilmadi", HttpStatus.NOT_FOUND);
        }
        News news = byId.get();
        news.setCountView(news.getCountView() + 1);
        News save = newsRepository.save(news);
        NewsDTO newsDTO = newsMapper.toDto(save);

        return ResponseEntity.ok(new Data<>(newsDTO));
    }

//    public ResponseEntity<Data<NewsDTO>> update(NewsUpdateDTO dto) {
//        Optional<News> byId = newsRepository.findById(dto.getId());
//        if (byId.isEmpty()) {
//            throw new UniversalException("Yangilik Topilmadi", HttpStatus.NOT_FOUND);
//        }
//
//    }






}
