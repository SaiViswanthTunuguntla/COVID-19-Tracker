package com.viswanth.covid19Tracker.service;

import com.viswanth.covid19Tracker.pojo.Datapojo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public  interface dataServiceInterface {
    public ArrayList<Datapojo> getData() throws URISyntaxException, IOException, InterruptedException;
}
