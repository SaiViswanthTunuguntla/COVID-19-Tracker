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
        int totalEffectedpeople = effectedPeople(data);
        int casesAddedToday=casesAddedToday(data);
        model.addAttribute("datalist",data);
        model.addAttribute("effectedpeople",totalEffectedpeople);
        model.addAttribute("casesAddedToady",casesAddedToday);
        return "home";
    }
    @GetMapping("/getUSAdata")
    public String getUSdata(Model model) throws InterruptedException, IOException, URISyntaxException {
        ArrayList<Datapojo> data = dataService.getUsData();
        int UStotalEffectedpeople = effectedPeople(data);
        int USCasesAddedToday=casesAddedToday(data);
        model.addAttribute("datalist",data);
        model.addAttribute("effectedpeople",UStotalEffectedpeople);
        model.addAttribute("casesAddedToady",USCasesAddedToday);
        return "USdata";
    }
   int  effectedPeople(ArrayList<Datapojo> data)
    {
        int totalEffectedPeople = data.stream()
                .mapToInt(x -> x.getInfected_people())
                .sum();
        return totalEffectedPeople;

    }
    int  casesAddedToday(ArrayList<Datapojo> data)
    {

    int totalCasesAddedToday = data.stream()
            .mapToInt(x -> x.getChangeFromPreviousDay())
            .sum();
        return totalCasesAddedToday;
    }
}
