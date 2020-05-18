package com.viswanth.covid19Tracker.service;

import com.viswanth.covid19Tracker.pojo.Datapojo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class DataService {
    private int totalEffected=0;
    ArrayList<Datapojo> totalCasesList = new ArrayList<>();
    ArrayList<Datapojo> USACasesList = new ArrayList<>();
    public int getTotalEffected() {
        return totalEffected;
    }

    public void setTotalEffected(int totalEffected) {
        this.totalEffected = totalEffected;
    }

    private static  String Data_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static  String USData_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
    @PostConstruct
    public  ArrayList<Datapojo> getData() throws URISyntaxException, IOException, InterruptedException {
        ArrayList<Datapojo> dataList = new ArrayList<>();
        int effectedCount=0;
        int previousDayCount=0;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Data_URL))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader=new StringReader(response.body());
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            Datapojo datapojo = new Datapojo();
            datapojo.setState(record.get("Province/State"));
            datapojo.setCountry(record.get("Country/Region"));
            effectedCount=Integer.parseInt(record.get(record.size()-1));
            previousDayCount=Integer.parseInt(record.get(record.size()-2));
            datapojo.setInfected_people(effectedCount);
            datapojo.setChangeFromPreviousDay(effectedCount-previousDayCount);
            dataList.add(datapojo);
        }
        this.totalCasesList=dataList;
        return totalCasesList;
    }
    @PostConstruct
    public  ArrayList<Datapojo> getUsData() throws URISyntaxException, IOException, InterruptedException {
        ArrayList<Datapojo> USdataList = new ArrayList<>();
        int effectedCount=0;
        int previousDayCount=0;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(USData_URL))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader=new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            Datapojo datapojo = new Datapojo();
            datapojo.setState(record.get("Province_State"));
            datapojo.setCountry(record.get("Country_Region"));
            datapojo.setCounty(record.get("Admin2"));
            effectedCount=Integer.parseInt(record.get(record.size()-1));
            previousDayCount=Integer.parseInt(record.get(record.size()-2));
            datapojo.setInfected_people(effectedCount);
            datapojo.setChangeFromPreviousDay(effectedCount-previousDayCount);
            USdataList.add(datapojo);
        }
//        for(Datapojo d:USdataList)
//        {
//            System.out.println(d);
//        }
        this.USACasesList=USdataList;
        return USACasesList;
    }
}
