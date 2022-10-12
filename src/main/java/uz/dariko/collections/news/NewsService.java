package uz.dariko.collections.news;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.file.FileRepository;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.govSphere.GovSphereRepository;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.sphere.Sphere;
import uz.dariko.collections.sphere.SphereRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService implements BaseService {
    NewsRepository newsRepository;
    SphereRepository sphereRepository;
    GovSphereRepository govSphereRepository;

    FileRepository fileRepository;


    public ResponseEntity<?> create(NewsCreateDTO newsCreateDto) {
        Optional<Sphere> byId = sphereRepository.findById(UUID.fromString(newsCreateDto.getSphereID()));
        Optional<GovSphere> byGovId = govSphereRepository.findById(UUID.fromString(newsCreateDto.getGovSphereID()));

        if(byId.isPresent() && byGovId.isPresent()) {
            Sphere sphere = byId.get();
            GovSphere govSphere = byGovId.get();
            List<File> images = new ArrayList<>();
            for(String imgId : newsCreateDto.getImages()) {
                Optional<File> file = fileRepository.findById(UUID.fromString(imgId));
                file.ifPresent(images::add);
            }
            News news = new News(
                    newsCreateDto.getUzTitle(), newsCreateDto.getKrTitle(), newsCreateDto.getRuTitle(),
                    newsCreateDto.getUzBody(), newsCreateDto.getKrBody(), newsCreateDto.getRuBody(),
                    sphere,govSphere,images,newsCreateDto.isActual(),0,newsCreateDto.getSource()
                    );
            newsRepository.save(news);
            return ResponseEntity.status(201).body("saved");
        }
        return ResponseEntity.badRequest().body("sphere not found");
    }

}
