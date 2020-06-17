package weird.io;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import lombok.Data;

/**
 * Processed Outgoing Data stream object/Serializer for quotes.
 *
 * @author Varun Gaur
 */
@Data
public class OutgoingDataStream {

    @JsonProperty("o")
    private double openingPrice;
    @JsonProperty("h")
    private double highPrice;
    @JsonProperty("l")
    private double lowPrice;
    @JsonProperty("c")
    private double currentPrice;
    @JsonProperty("pc")
    private double previousClose;
    @JsonProperty("t")
    private Date timestamp;
}

