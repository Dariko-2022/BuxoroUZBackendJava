package uz.dariko.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.admin.AdminRepository;
import uz.dariko.collections.file.File;
import uz.dariko.collections.file.FileRepository;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.govGroup.GovGroupRepository;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.govSphere.GovSphereRepository;
import uz.dariko.collections.link.Link;
import uz.dariko.collections.link.LinkRepository;
import uz.dariko.collections.news.News;
import uz.dariko.collections.news.NewsRepository;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.region.RegionRepository;
import uz.dariko.collections.sector.Sector;
import uz.dariko.collections.sector.SectorRepository;
import uz.dariko.collections.sphere.Sphere;
import uz.dariko.collections.sphere.SphereRepository;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.stateEmloyee.StateEmployeeRepository;
import uz.dariko.exception.exceptions.NotFoundException;
import uz.dariko.exception.exceptions.UniversalException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EntityGetter {

    private final AdminRepository adminRepository;
    private final FileRepository fileRepository;

    private final LinkRepository linkRepository;

    private final RegionRepository regionRepository;

    private final GovGroupRepository govGroupRepository;

    private final GovSphereRepository govSphereRepository;

    private final StateEmployeeRepository stateEmployeeRepository;

    private final SphereRepository sphereRepository;

    private final NewsRepository newsRepository;

    private final SectorRepository sectorRepository;

    private final BaseUtils baseUtils;

    public Admin getAdmin(String username) {
        Optional<Admin> optional = adminRepository.findByUsername(username);
        return optional.orElseThrow(() -> {
            throw new UniversalException("Admin topilmadi", HttpStatus.NOT_FOUND);
        });
    }

    public Link getLink(UUID linkID){
        Optional<Link> byIdAndIsDeleted = linkRepository.findByIdAndIsDeleted(linkID, false);
        return byIdAndIsDeleted.orElseThrow(() -> {
            throw new NotFoundException("Link Topilmadi");
        });
    }


//----------------------------------------------------File----------------------------------------------------

    public File getFile(UUID fileID){
        Optional<File> entityOptional = fileRepository.findByIdAndIsDeleted(fileID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("File IDsi noto'g'ri berildi");
        });
    }


    public File getFile(String fileId){
        UUID fileID = baseUtils.parseUUID(fileId);
        Optional<File> entityOptional = fileRepository.findByIdAndIsDeleted(fileID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("File IDsi noto'g'ri berildi");
        });
    }
    public List<File> getFiles(List<String> entitiesID) {
        List<UUID> list = baseUtils.parseUUID(entitiesID);
        List<File> files = new ArrayList<>();
        for (UUID id : list) {
            File file = getFile(id);
            files.add(file);
        }
        return files;
    }


//----------------------------------------------------Region----------------------------------------------------
    public Region getRegion(UUID regionID) {
        Optional<Region> entityOptional = regionRepository.findByIdAndIsDeleted(regionID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("Region IDsi noto'g'ri berildi");
        });
    }

    public Region getRegion(String regionId) {
        UUID regionID = baseUtils.parseUUID(regionId);
        Optional<Region> entityOptional = regionRepository.findByIdAndIsDeleted(regionID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("Region IDsi noto'g'ri berildi");
        });
    }



//----------------------------------------------------StateEmployee----------------------------------------------------
    public StateEmployee getStateEmployee(UUID stateEmployeeID){
        Optional<StateEmployee> entityOptional = stateEmployeeRepository.findByIdAndIsDeleted(stateEmployeeID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("StateEmployee IDsi noto'g'ri berildi");
        });
    }

    public StateEmployee getStateEmployee(String stateEmployeeId){
        UUID stateEmployeeID = baseUtils.parseUUID(stateEmployeeId);
        Optional<StateEmployee> entityOptional = stateEmployeeRepository.findByIdAndIsDeleted(stateEmployeeID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("StateEmployee IDsi noto'g'ri berildi");
        });
    }



//----------------------------------------------------GovGroup----------------------------------------------------
    public GovGroup getGovGroup(UUID govGroupID){
        Optional<GovGroup> entityOptional = govGroupRepository.findByIdAndIsDeleted(govGroupID,false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("Govgroup IDsi noto'g'ri berildi");
        });
    }



//----------------------------------------------------GovSphere ----------------------------------------------------
    public GovSphere getGovSphere(UUID govSphereID){
        Optional<GovSphere> byIdAndDeletedNot = govSphereRepository.findByIdAndDeletedNot(govSphereID);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Govgroup IDsi noto'g'ri berildi");
        });
    }

    public GovSphere getGovSphere(String govSphereID){
        UUID uuid = baseUtils.parseUUID(govSphereID);
        Optional<GovSphere> byIdAndDeletedNot = govSphereRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Govgroup IDsi noto'g'ri berildi");
        });
    }



//----------------------------------------------------Sphere----------------------------------------------------
    public Sphere getSphere(UUID uuid) {
        Optional<Sphere> byIdAndDeletedNot = sphereRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Sohani IDsi noto'g'ri berildi");
        });
    }

    public Sphere getSphere(String id) {
        UUID uuid = baseUtils.parseUUID(id);
        Optional<Sphere> byIdAndDeletedNot = sphereRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Sohani IDsi noto'g'ri berildi");
        });
    }



//----------------------------------------------------News----------------------------------------------------
    public News getNews(UUID id) {
        Optional<News> byIdAndDeletedNot = newsRepository.findByIdAndDeletedNot(id);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Yangilikni IDsi noto'g'ri berildi");
        });
    }



//----------------------------------------------------Sector----------------------------------------------------
    public Sector getSector(String id) {
        UUID uuid = baseUtils.parseUUID(id);
        Optional<Sector> byIdAndDeletedNot = sectorRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Sectorni IDsi noto'g'ri berildi");
        });
    }
    public Sector getSector(UUID uuid) {
        Optional<Sector> byIdAndDeletedNot = sectorRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Sectorni IDsi noto'g'ri berildi");
        });
    }
}


