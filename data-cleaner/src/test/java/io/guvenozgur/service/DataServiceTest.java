package io.guvenozgur.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataServiceTest {
  @InjectMocks
  DataService dataService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCleanData_withList() {
    List<String> data_1 = Arrays.asList("0", "s1", null, "35", "90", "60");
    List<String> data_2 = Arrays.asList("ttt", null, null, "15");
    List<String> data_3 = Arrays.asList("75", "95", "0", "0", null, "ssss", "0", "-15");
    List<String> data_4 = Arrays.asList("25", "fgdfg", "", "105", "dsfdsf", "-5");

    List<Integer> resp = dataService.cleanData(Arrays.asList(data_1, data_2, data_3, data_4));
    Assertions.assertEquals(resp.size(), 11);

  }

  @Test
  public void testCleanData_withEmptyList() {
    List<Integer> resp = dataService.cleanData(new ArrayList<>());
    Assertions.assertEquals(resp.size(), 0);
  }

  @Test
  public void testCleanData_withListWhichContainsEmptyList() {

    List<String> data_1 = Arrays.asList("0", "s1", null, "35", "90", "60");
    List<String> data_2 = Arrays.asList("ttt", null, null, "15");
    List<String> data_3 = Arrays.asList("75", "95", "0", "0", null, "ssss", "0", "-15");
    List<String> data_4 = null;

    List<Integer> resp = dataService.cleanData(Arrays.asList(data_1, data_2, data_3, data_4));
    Assertions.assertEquals(resp.size(), 9);
  }

  @Test
  public void testCleanData_withArray() {

    String[][] test = {
      {"0", "s1", null, "35", "90", "60"},
      {"ttt", null, null, "15"},
      {"75", "95", "0", "0", null, "ssss", "0", "-15"},
      {"25", "fgdfg", "", "105", "dsfdsf", "-5"}
    };
    Integer[] resp = dataService.cleanData(test);
    Assertions.assertEquals(resp.length, 11);
  }


  @Test
  public void testCleanData_withEmptyArray() {

    String[][] test = {};
    Integer[] resp = dataService.cleanData(test);
    Assertions.assertEquals(resp.length, 0);
  }

  @Test
  public void testCleanData_withArrayWhichContainsEmptyArray() {

    String[][] test = {
            {"0", "s1", null, "35", "90", "60"},
            {"ttt", null, null, "15"},
            {"75", "95", "0", "0", null, "ssss", "0", "-15"},
            {}
    };
    Integer[] resp = dataService.cleanData(test);
    Assertions.assertEquals(resp.length, 9);
  }
}
