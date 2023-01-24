package io.guvenozgur.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService implements IDataService{

    private static final Integer THRESHOLD = 90;


    @Override
    public List<Integer> cleanData(List<List<String>> data) {

        List<Integer> response = new ArrayList<>();

        if(CollectionUtils.isEmpty(data)) return response;

        List<Integer> tempTriple = new ArrayList<>();
        Integer tempSum = 0;


        for(List<String> innerList: data) {
            if (CollectionUtils.isEmpty(innerList)) continue;
            for(String val: innerList) {
                tempSum = checkConditionsReturnSum(val, response, tempTriple, tempSum);
            }
        }
        checkThresholdAndMerge(response, tempTriple, tempSum);
        return response;
    }

    @Override
    public Integer[] cleanData(String[][] data) {

        List<Integer> responseAsList = new ArrayList<>();
        Integer[] response = {};

        if (data == null || data.length == 0) return response;

        List<Integer> tempTriple = new ArrayList<>();
        Integer tempSum = 0;

        for(String[] innerList: data) {
            if (innerList == null || innerList.length == 0) continue;
            for(String val: innerList) {
                tempSum = checkConditionsReturnSum(val, responseAsList, tempTriple, tempSum);
            }
        }
        checkThresholdAndMerge(responseAsList, tempTriple, tempSum);
        response = responseAsList.toArray(response);
        return response;
    }

    private Integer checkConditionsReturnSum(String val, List<Integer> accumulator, List<Integer> tempTriple, Integer tempSum) {
        if(val != null && !val.isEmpty()){
            try{
                Integer value = Integer.valueOf(val);
                tempSum += value;
                tempTriple.add(value);
                if(tempTriple.size() == 3) {
                    checkThresholdAndMerge(accumulator, tempTriple, tempSum);
                    tempSum = 0;
                    tempTriple.clear();
                }
            } catch (NumberFormatException e) {
                // No need to handle
            }
        }

        return tempSum;

    }

    private void checkThresholdAndMerge(List<Integer> accumulator, List<Integer> temp, Integer sum) {
        if(sum > THRESHOLD) {
            accumulator.addAll(temp);
        }
    }

}
