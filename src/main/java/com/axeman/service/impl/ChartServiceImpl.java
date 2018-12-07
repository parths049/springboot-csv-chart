package com.axeman.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.axeman.model.ChartData;
import com.axeman.service.ChartService;

@Service
public class ChartServiceImpl implements ChartService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChartServiceImpl.class);

  @Value("${chart.csv.path}")
  public String chartCsvPath;

  @Override
  public List<ChartData> getChartDataFromCSV() throws Exception {

    BufferedReader reader = new BufferedReader(new FileReader(chartCsvPath));

    // read file line by line
    String line = null;
    Scanner scanner = null;
    int index = 0;
    List<ChartData> list = new ArrayList<>();
    reader.readLine(); // skip first one
    while ((line = reader.readLine()) != null) {
      ChartData chartData = new ChartData();
      scanner = new Scanner(line);
      scanner.useDelimiter(",");
      while (scanner.hasNext()) {
        String data = scanner.next();
        switch (index) {
          case 0:
            chartData.setWords(data);
            break;
          case 1:
            chartData.setScore(Integer.parseInt(data));
            break;
          case 2:
            chartData.setAvgScore(Integer.parseInt(data));
            break;
          default:
            break;
        }
        index++;
      }
      index = 0;
      if(chartData.getWords().length() > 0)
        list.add(chartData);
    }
    // close reader
    reader.close();
    LOGGER.info("Total Records : ", list.size());
    return list;
  }

}
