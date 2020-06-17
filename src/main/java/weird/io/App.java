package weird.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.util.Date;

@ComponentScan(basePackages = "weird.io")
@SpringBootApplication
@EnableBinding(Processor.class)
public class App {
    /**
     * Main method to initiate app execution.
     * @param args cli parameters if any
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private DateTransformer dateTransformer;

    /**
     * Function interface that transforms the incoming stream data.
     *
     * @return transformed stream
     */
    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public OutgoingDataStream transform(Message<IncomingDataStream>  message) {
        final IncomingDataStream rates = message.getPayload();

        final OutgoingDataStream outgoingDataStream = new OutgoingDataStream();

        final Date convertDate = dateTransformer.transformDate(rates.getTimestamp());
        outgoingDataStream.setCurrentPrice(rates.getCurrentPrice());
        outgoingDataStream.setHighPrice(rates.getHighPrice());
        outgoingDataStream.setLowPrice(rates.getLowPrice());
        outgoingDataStream.setOpeningPrice(rates.getOpeningPrice());
        outgoingDataStream.setPreviousClose(rates.getPreviousClose());
        outgoingDataStream.setTimestamp(convertDate);

        System.out.println("Outgoing stream::"+ outgoingDataStream);

        return outgoingDataStream;

    }

}