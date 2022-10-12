package uz.dariko.collections.news;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.govSphere.GovSphereRepository;
import uz.dariko.collections.news.dto.NewsCreateDto;
import uz.dariko.collections.sphere.Sphere;
import uz.dariko.collections.sphere.SphereRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService implements BaseService {
    NewsRepository newsRepository;
    SphereRepository sphereRepository;
    GovSphereRepository govSphereRepository;


    public ResponseEntity<?> create(NewsCreateDto newsCreateDto) {
        Optional<Sphere> byId = sphereRepository.findById(UUID.fromString(newsCreateDto.getSphereId()));
        Optional<GovSphere> byGovId = govSphereRepository.findById(UUID.fromString(newsCreateDto.getGovSphereId()));

        if(byId.isPresent() && byGovId.isPresent()) {
            Sphere sphere = byId.get();
            GovSphere govSphere = byGovId.get();
            News news = new News(
                    newsCreateDto.getUzTitle(), newsCreateDto.getKrTitle(), newsCreateDto.getRuTitle(),
                    newsCreateDto.getUzBody(), newsCreateDto.getKrBody(), newsCreateDto.getRuBody(),
                    sphere,govSphere,newsCreateDto.isActual(),0,newsCreateDto.getSource()
                    );
            newsRepository.save(news);
            return ResponseEntity.status(201).body("saved");
        }
        return ResponseEntity.badRequest().body("sphere not found");
    }

}
