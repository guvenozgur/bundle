package io.guvenozgur.bundle.service;

import com.google.gson.Gson;
import io.guvenozgur.bundle.model.ProducerTypes;
import io.guvenozgur.bundle.model.RandomData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class FileWriterProducerImpl implements IDataProducer {

  private static final String DIRECTORY = "data-handler/out/random_data.txt";
  private static final Gson gson = new Gson();

  List<String> dataList = Collections.synchronizedList(new ArrayList<>());

  @Override
  public void send(RandomData randomData) {
    String data = gson.toJson(randomData);
    synchronized (dataList) {
      if(dataList.size() > 100) {
        log.info("Appending to the file: " + dataList.size() );
        appendToFile(dataList);
        dataList.clear();
      } else {
        log.info("Collecting data: " + dataList.size() );
        dataList.add(data);
      }
    }
  }

  @Override
  public String getType() {
    return ProducerTypes.FILE_WRITER.getType();
  }

  private void appendToFile(List<String> data) {
    String lineSeparator = System.getProperty("line.separator");
    String dataToWrite = data.stream().reduce("", (r, d)->r.concat(lineSeparator).concat(d)).trim();
    byte[] dataToBytes = dataToWrite.getBytes();
    try (FileOutputStream outputStream = new FileOutputStream(DIRECTORY, true)) {
      outputStream.write(dataToBytes);

      outputStream.write(lineSeparator.getBytes());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
