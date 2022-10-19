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
import uz.dariko.collections.infoGroup.InfoGroup;
import uz.dariko.collections.infoGroup.InfoGroupRepository;
import uz.dariko.collections.informations.Information;
import uz.dariko.collections.informations.InformationRepository;
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

    private final StateEmployeeRepository stateEmployeeRepository;

    private final SphereRepository sphereRepository;

    private final NewsRepository newsRepository;

    private final SectorRepository sectorRepository;

    private final InfoGroupRepository infoGroupRepository;
    private final InformationRepository informationRepository;

    private final BaseUtils baseUtils;

    public Admin getAdmin(String username) {
        Optional<Admin> optional = adminRepository.findByUsername(username);
        return optional.orElseThrow(() -> {
            throw new UniversalException("Admin topilmadi", HttpStatus.NOT_FOUND);
        });
    }
    public Admin getAdmin(UUID adminID) {
        Optional<Admin> optional = adminRepository.findByIdAndDeleted(adminID,false);
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



    public List<File> getFiles(List<UUID> list) {
        List<File> files = new ArrayList<>();
        for (UUID id : list) {
            File file = getFile(id);
            files.add(file);
        }
        return files;
    }

    public List<String> getGeneratedNames(List<File> files){
        List<String> generatedNames=new ArrayList<>();
        for (File file : files) {
            generatedNames.add(getGeneratedName(file));
        }
        return generatedNames;
    }

    public String getGeneratedName(File file){
        return file.getGeneratedName();
    }


//----------------------------------------------------Region----------------------------------------------------
    public Region getRegion(UUID regionID) {
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





//----------------------------------------------------GovGroup----------------------------------------------------
    public GovGroup getGovGroup(UUID govGroupID){
        Optional<GovGroup> entityOptional = govGroupRepository.findByIdAndIsDeleted(govGroupID,false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("Govgroup IDsi noto'g'ri berildi");
        });
    }
    public List<GovGroup> getGovGroup(List<UUID> govGroupIDs){
        List<GovGroup> govGroups=new ArrayList<>();
        for (UUID govGroupID : govGroupIDs) {
            govGroups.add(getGovGroup(govGroupID));
        }
        return govGroups;
    }








//----------------------------------------------------Sphere----------------------------------------------------
    public Sphere getSphere(UUID uuid) {
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

    public Sector getSector(UUID uuid) {
        Optional<Sector> byIdAndDeletedNot = sectorRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Sectorni IDsi noto'g'ri berildi");
        });
    }


//----------------------------------------------------InfoGroup----------------------------------------------------

    public InfoGroup getInfoGroup(UUID uuid) {
        Optional<InfoGroup> byIdAndDeletedNot = infoGroupRepository.findByIdAndDeletedNot(uuid);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Info groupni IDsi noto'g'ri berildi");
        });
    }
    //-------------------------------------------------Information---------------------------------------------------

    public Information getInformation(UUID uuid){
        Optional<Information> byIdAndDeletedNot = informationRepository.findByIdAndDeletedNot(uuid,false);
        return byIdAndDeletedNot.orElseThrow(() -> {
            throw new NotFoundException("Information IDsi noto'g'ri berildi");
        });
    }
}


