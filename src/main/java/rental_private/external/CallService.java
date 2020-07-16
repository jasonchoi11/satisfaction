
package rental_private.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="Call", url="http://admin19-sk-call:8080")
public interface CallService {

    @RequestMapping(method= RequestMethod.POST, path="/calls")
    public void callSurvey(@RequestBody Call call);

}