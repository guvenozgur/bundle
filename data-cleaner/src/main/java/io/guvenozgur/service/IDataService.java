package io.guvenozgur.service;

import java.util.List;

public interface IDataService {

    Integer[] cleanData(String[][] data);
    List<Integer> cleanData(List<List<String>> data);
}
