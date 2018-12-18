package com.axeman.response;

import java.util.List;
import com.axeman.model.ChartData;

public class ChartResponse {
  
  private List<String> xAxis;
  private List<Integer> score;
  private List<Integer> avgScore;
  private List<ChartData> chartData;
  
  public List<String> getxAxis() {
    return xAxis;
  }
  public void setxAxis(List<String> xAxis) {
    this.xAxis = xAxis;
  }
  public List<Integer> getScore() {
    return score;
  }
  public void setScore(List<Integer> score) {
    this.score = score;
  }
  public List<Integer> getAvgScore() {
    return avgScore;
  }
  public void setAvgScore(List<Integer> avgScore) {
    this.avgScore = avgScore;
  }
  public List<ChartData> getChartData() {
    return chartData;
  }
  public void setChartData(List<ChartData> chartData) {
    this.chartData = chartData;
  }
  
}
