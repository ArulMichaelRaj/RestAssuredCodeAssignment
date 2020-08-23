package Model;

import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.restassured.response.Response;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestResponseSpecData {
    Response response;
    String path;
    List<String> loginCreds;
    String employeename;
}
