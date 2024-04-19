package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpController {
    @PostMapping("/create")
    public void CreateEmp(Model model) {
         model.addAttribute("message", "что то");
    }
}
