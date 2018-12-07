package com.axeman.service;

import java.util.List;
import com.axeman.model.ChartData;

public interface ChartService {

  public List<ChartData> getChartDataFromCSV() throws Exception;

}
