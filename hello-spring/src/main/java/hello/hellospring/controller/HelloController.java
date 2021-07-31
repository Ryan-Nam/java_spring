package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // move to hello.html TEMPLATE
    }

    @GetMapping("hello-mvc")
    public String hellowMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template"; // move to hello-template TEMPLATE
    }
}