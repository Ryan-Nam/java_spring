package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    //Data...
    @GetMapping("hello-api")
    @ResponseBody
    // make object first
    public Hello helloApi(@RequestParam("name") String name) {   //publich Hello = object
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello { // this Object can be used in this class of "HelloController"
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}