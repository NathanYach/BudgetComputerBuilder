package org.Controllers;

import org.Models.BuilderForm;
import org.Models.HardwareList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
class WebController {

    HardwareList hardware = new HardwareList();
    @RequestMapping("/")
    public String PcBuilderWeb(Model model) {
        model.addAttribute("builderForm", new BuilderForm());
        return "PcBuilderWeb";
    }
    @PostMapping("/")
    public String submit(@ModelAttribute BuilderForm builderForm, Model model){

        model.addAttribute("builderForm", builderForm);
        RunScrappers scrappers = new RunScrappers();
        hardware = scrappers.run(builderForm.getBudget(),builderForm.getGpuWeight(), builderForm.getCpuWeight(), builderForm.getTax());
        System.out.println(hardware.getCpu().toString());
        model.addAttribute("cpu", hardware.getCpu());
        model.addAttribute("gpu", hardware.getGpu());
        model.addAttribute("leftover", hardware.getLeftover());

        return "PcListWeb";
        //return new ModelAndView("redirect:/PcListWeb");

    }

    @RequestMapping("/PcListWeb")
    public String PcListWeb(@ModelAttribute Model model){


        return "PcListWeb";
    }
}