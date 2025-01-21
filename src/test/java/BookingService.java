import org.example.CantBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    public boolean book(String userId, LocalDateTime from, LocalDateTime to) throws CantBookException {
        logger.info("Trying to book for user {} from {} to {}", userId, from, to);
        if (checkTimeInBD(from, to)) {
            boolean result = createBook(userId, from, to);
            logger.info("Booking successful: {}", result);
            return result;
        }
        logger.warn("Slot not available from {} to {}", from, to);
        throw new CantBookException();
    }

    public boolean checkTimeInBD(LocalDateTime from, LocalDateTime to) {
        return false;
    }

    public boolean createBook(String userId, LocalDateTime from, LocalDateTime to) {
        return false;
    }
}