package org.webapp.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.webapp.Models.*;
import org.webapp.Repositories.CPURepository;
import org.webapp.Repositories.GPURepository;
import org.webapp.Services.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
class WebController {

    HardwareList hardware = new HardwareList();
    private final HardwareService hardwareService;

    @RequestMapping("/")
    public String PcBuilderWeb(Model model,HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        System.out.println(ipAddress);
        model.addAttribute("builderForm", new BuilderForm(0.4,0.6,0,0));
        return "PcBuilderWeb";
    }
    @PostMapping("/")
    public String submit(@ModelAttribute BuilderForm builderForm, Model model){

        model.addAttribute("builderForm", builderForm);

        BuildPC buildPC = new BuildPC(
                hardwareService.getMap(hardwareService.getAllGPUs()),
                hardwareService.getMap(hardwareService.getAllCPUs()),
                builderForm.getBudget(),
                builderForm.getGpuWeight(),
                builderForm.getCpuWeight(),
                builderForm.getTax()
        );

        //BuildPC builder = new BuildPC(GPU.getGpuCatalog(), CPU.getCpuCatalog(), builderForm.getBudget(), builderForm.getGpuWeight(), builderForm.getCpuWeight());
        // Build the PC based on the specified parameters
        hardware = buildPC.build();
        //System.out.println(hardware.getCpu().toString());
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

    @Autowired
    public WebController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }
}