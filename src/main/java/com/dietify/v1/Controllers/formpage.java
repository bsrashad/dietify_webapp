package com.dietify.v1.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class formpage {
    
@GetMapping("/formpage")
public String surveyform(Model model) {
    String message="helloooo";
    model.addAttribute("message", message);
    return "form";
}


}
