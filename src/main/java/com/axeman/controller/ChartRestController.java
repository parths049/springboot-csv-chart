package com.axeman.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.axeman.model.ChartData;
import com.axeman.response.ChartResponse;
import com.axeman.service.ChartService;
import com.axeman.utils.Constants;
import com.axeman.utils.ResponseGenerator;

@RestController
public class ChartRestController {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ChartController.class);

  @Autowired
  private ChartService chartService;
  
  @GetMapping("/chart/getData")
  public Object getChartData(Model model) {
    LOGGER.info("call getChartData()");
    try {
      List<ChartData> chartData = chartService.getChartDataFromCSV();
      model.addAttribute("chartData", chartData);
      List<String> xAxis = new ArrayList<>();
      List<Integer> score = new ArrayList<>();
      List<Integer> avgScore = new ArrayList<>();
      for(ChartData chart: chartData) {
        xAxis.add(chart.getWords());
        score.add(chart.getScore());
        avgScore.add(chart.getAvgScore());
      }
      ChartResponse chartResponse = new ChartResponse();
      chartResponse.setxAxis(xAxis);
      chartResponse.setScore(score);
      chartResponse.setAvgScore(avgScore);
      chartResponse.setChartData(chartData);
      return ResponseGenerator.generateResponse(Constants.SUCCESS, "Chart Data", chartResponse);
    } catch (Exception e) {
      LOGGER.error("Error occerd while read data from csv", e);
      return ResponseGenerator.generateResponse(Constants.ERROR, e.getMessage(), null);
    }
    
  }
}
