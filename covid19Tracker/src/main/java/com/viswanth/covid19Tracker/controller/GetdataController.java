package com.viswanth.covid19Tracker.controller;

import com.viswanth.covid19Tracker.pojo.Datapojo;
import com.viswanth.covid19Tracker.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Controller("/")
public class GetdataController {
    @Autowired
    DataService dataService;
    @GetMapping("/getdata")
    public String getWorlddata(Model model) throws InterruptedException, IOException, URISyntaxException {
        ArrayList<Datapojo> data = dataService.getData();
        int totalEffectedpeople = data.stream()
                .mapToInt(x -> x.getInfected_people())
                .sum();
        model.addAttribute("datalist",data);
        model.addAttribute("effectedpeople",totalEffectedpeople);
        return "home";
    }
    @GetMapping("/getUSAdata")
    public String getUSdata(Model model) throws InterruptedException, IOException, URISyntaxException {
        ArrayList<Datapojo> data = dataService.getUsData();
        int UStotalEffectedpeople = data.stream()
                .mapToInt(x -> x.getInfected_people())
                .sum();
        model.addAttribute("datalist",data);
        model.addAttribute("effectedpeople",UStotalEffectedpeople);
        return "USdata";
    }
}
