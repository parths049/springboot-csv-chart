package com.axeman.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.axeman.model.ChartData;
import com.axeman.service.ChartService;
import com.axeman.utils.ViewConstant;

@Controller
public class ChartController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChartController.class);

  @Autowired
  private ChartService chartService;
  
  @GetMapping("/")
  public String getChartData(Model model) {
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
      model.addAttribute("xAxis", xAxis);
      model.addAttribute("score", score);
      model.addAttribute("avgScore", avgScore);
      model.addAttribute("chartData", chartData);
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      LOGGER.error("Error occerd while read data from csv", e);
    }
    return ViewConstant.CHART_PAGE;
  }

}
