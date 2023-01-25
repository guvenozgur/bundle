package io.guvenozgur.bundle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class RandomData implements Serializable {

    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("randomVal")
    private Integer randomVal;
    @JsonProperty("hash")
    private String hash;
}
