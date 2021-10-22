package at.kaindorf.springintro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/home") ==> dann sind alle gets/posts/etc. ein subpfad von dem
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "homepage";
    }

}
