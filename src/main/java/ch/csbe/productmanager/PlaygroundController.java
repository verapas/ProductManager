package ch.csbe.productmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("playground")
public class PlaygroundController {

    @GetMapping("hello-world")
    public String helloWorld(){
        return "Hello Java Spring Boot";
    }


}

