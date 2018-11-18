package com.lyj.springinaction.web;

import com.lyj.springinaction.entity.Spitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register1", method = RequestMethod.POST)
    public String showRegistrationForm1(@RequestPart("profilePicture") MultipartFile file, Spitter spitter,Model model) {
        System.out.println(file.getOriginalFilename());
        // 使用模板的方式来定义 重定向url
        model.addAttribute("username",spitter.getUsername());
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String showRegistrationForm(@RequestPart("profilePicture") MultipartFile file, Spitter spitter, RedirectAttributes model) {
        System.out.println(file.getOriginalFilename());
        model.addAttribute("username",spitter.getUsername());
        model.addFlashAttribute(spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showSpitter(@PathVariable("username") String username, Model model){
        if(model.containsAttribute("spitter")){
            return "profile";
        }
//        Spitter spitter = new Spitter();
//        spitter.setUsername(username);
//        model.addAttribute(spitter);
        return "profile";
    }
}
