package uz.dariko.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.dariko.criteria.ResponsePage;
import uz.dariko.exception.exceptions.BadRequestException;
import uz.dariko.exception.exceptions.UniversalException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BaseUtils {
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String PATH = API + VERSION;
    public static final RestTemplate TEMPLATE = new RestTemplate();

    public static final String CREATE_PATH = "/create";
    public static final String UPDATE_PATH = "/update";
    public static final String DELETE_PATH = "/delete/{code}";
    public static final String GET_PATH = "/get/{code}";
    public static final String DETAIL_PATH = "detail/{code}";
    public static final String LIST_PATH = "/list";


    public static final String UPLOAD_DIR = "newsPhotos";

    public static final String LIST_WITH_ID_PATH = "/list/{code}";
    public static final String CHANGE_ORDER_PATH = "/change_order";
//    @Value("${income.base.url}")
//    public String BASE_URL;

    public LocalDate dateFormat(String date) {
        if (date == null) {
            return null;
        }
        String[] split = date.split("\\.");
        if (split.length != 3) {
            throw new UniversalException(String.format("Wrong date format %s", date), HttpStatus.BAD_REQUEST);
        }
        date = String.format("%s-%s-%s", split[2], split[1], split[0]);
        return LocalDate.parse(date);
    }

    public Boolean checkDateFormat(String date) {
        if (date == null) {
            return null;
        }
        String[] split = date.split("\\.");
        if (split.length != 3) {
            return false;
        }
        date = String.format("%s-%s-%s", split[2], split[1], split[0]);
        return true;
    }

    public String dateFormat(LocalDate date) {
        if (date == null) {
            return null;
        }
        return String.format("%s-%s-%s", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    public List<String> stringTrim(String... strs) {
        List<String> res = new ArrayList<>();
        for (String str : strs) {
            res.add(str.trim());
        }
        return res;
    }


    public UUID parseUUID(String id) {
        if (id == null) {
            return null;
        }
        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            throw new BadRequestException("ID UUID formatida bo'lishi kerak");
        }
    }

    public List<UUID> parseUUID(List<String> ids) {
        List<UUID> uuids = new ArrayList<>();
        for (String id : ids) {
            UUID uuid = parseUUID(id);
            uuids.add(uuid);
        }
        return uuids;
    }


    public Long parseLong(String id) {
        try {
            return Long.valueOf(id);
        } catch (Exception e) {
            throw new BadRequestException("ID Long toifasida bo'lishi kerak");
        }
    }


    public <E, D> ResponsePage<D> toResponsePage(Page<E> page, List<D> content) {
        ResponsePage<D> responsePage = new ResponsePage<>();
        responsePage.setNumberOfElements(page.getNumberOfElements());
        responsePage.setNumber(page.getNumber());
        responsePage.setTotalPages(page.getTotalPages());
        responsePage.setContent(content);
        responsePage.setTotalElements(page.getTotalElements());
        responsePage.setSize(page.getSize());
        return responsePage;
    }

}
