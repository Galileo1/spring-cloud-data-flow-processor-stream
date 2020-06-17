package weird.io;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Responsibility of the class is just to transform timestamp to datetime.
 *
 * @author Vaurn Gaur
 */
@Slf4j
@Service
@AllArgsConstructor
public class DateTransformer {

    /**
     * transforms the date to date timestamp.
     *
     * @param unixMilliSecond received from source
     * @return Date
     */
    public Date transformDate(final int unixMilliSecond) {

        return new Date((long)unixMilliSecond * 1000);
    }

}
