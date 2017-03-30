package objects;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Denis on 05.03.2017.
 */
public class HttpResponse {

    private String[] domains = {"http://localhost:3000", "https://tp-front-end-js-game.herokuapp.com"};

    public HttpServletResponse getAccessAllowControlOriginHeaders(HttpServletResponse httpServletResponse,
                                                                  String newDomain) {
        System.out.print(newDomain);
        for(String domain : domains){
            if(domain.equals(newDomain)){
                httpServletResponse.addHeader("Access-Control-Allow-Origin", domain);
                break;
            }
        }
        return httpServletResponse;
    }
}
