package at.itproject.api;


import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping(value="/history", consumes= "application/json", method= RequestMethod.POST)
    public void postHistory(@RequestBody String history) throws IOException {
        try {
            System.out.println(history);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
