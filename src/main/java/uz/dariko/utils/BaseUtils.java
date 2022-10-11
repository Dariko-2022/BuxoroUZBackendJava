package uz.dariko.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

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

    public static final String LIST_WITH_ID_PATH = "/list/{code}";
    public static final String CHANGE_ORDER_PATH = "/change_order";
    @Value("${income.base.url}")
    public String BASE_URL;
}
