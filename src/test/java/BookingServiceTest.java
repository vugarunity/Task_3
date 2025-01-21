import org.example.CantBookException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Test
    void bookShouldReturnTrueWhenSlotIsAvailable() throws CantBookException {
        // Arrange
        BookingService service = Mockito.spy(new BookingService());
        LocalDateTime from = LocalDateTime.now().plusDays(1);
        LocalDateTime to = from.plusHours(1);

        doReturn(true).when(service).checkTimeInBD(from, to);
        doReturn(true).when(service).createBook("user123", from, to);

        // Act
        boolean result = service.book("user123", from, to);

        // Assert
        assertThat(result).isTrue();
        verify(service).checkTimeInBD(from, to);
        verify(service).createBook("user123", from, to);
    }

    @Test
    void bookShouldThrowExceptionWhenSlotIsNotAvailable() {
        // Arrange
        BookingService service = Mockito.spy(new BookingService());
        LocalDateTime from = LocalDateTime.now().plusDays(1);
        LocalDateTime to = from.plusHours(1);

        doReturn(false).when(service).checkTimeInBD(from, to);

        // Act & Assert
        assertThatThrownBy(() -> service.book("user123", from, to))
                .isInstanceOf(CantBookException.class);

        verify(service).checkTimeInBD(from, to);
        verify(service, never()).createBook(anyString(), any(), any());
    }
}