package com.selesy.hibp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PossibleTests {

    static final String ERROR_MESSAGE = "This is an error";
    static final String VALUE= "This is a value";

    Possible<String> cut;

    @Mock
    Consumer<String> mockConsumer;

    @Nested
    public class ErrorPresentTests {

        @BeforeEach
        void beforeEach() {
            cut = Possible.of(new Exception(ERROR_MESSAGE));
        }

        @Test
        void testError() {
            Throwable throwable = cut.error();
            assertThat(throwable).isInstanceOf(Exception.class);
            assertThat(throwable.getMessage()).isEqualTo(ERROR_MESSAGE);
        }

        @Test
        void testGet() {
            Throwable throwable = assertThrows(NoSuchElementException.class, () -> cut.get());
            assertThat(throwable).hasMessage(Possible.NO_VALUE_MESSAGE);
        }

        @Test
        void testIfPresent() {
            cut.ifPresent(mockConsumer);
            verify(mockConsumer, never()).accept(anyString());
        }

        @Test
        void testIsPresent() {
            assertThat(cut.isPresent()).isFalse();
        }

        @Test
        void testOptional() {
            assertThat(cut.optional()).isNotPresent();
        }

        @Test
        void testToString() {
            assertThat(cut.toString()).isEqualTo("Error: Exception, " + ERROR_MESSAGE);
        }

    }

    @Nested
    public class ValuePresentTests {

        @BeforeEach
        void beforeEach() {
            cut = Possible.of(VALUE);
        }

        @Test
        void testError() {
            Throwable throwable = assertThrows(NoSuchElementException.class, () -> cut.error());
            assertThat(throwable).hasMessage(Possible.NO_ERROR_MESSAGE);
        }

        @Test
        void testGet() {
            assertThat(cut.get()).isEqualTo(VALUE);
        }

        @Test
        void testIfPresent() {
            cut.ifPresent(mockConsumer);
            verify(mockConsumer, times(1)).accept(VALUE);
        }

        @Test
        void testIsPresent() {
            assertThat(cut.isPresent()).isTrue();
        }

        @Test
        void testOptional() {
            Optional<String> optional = cut.optional();
            assertThat(optional).isPresent();
            assertThat(optional).hasValue(VALUE);
        }

        @Test
        void testToString() {
            assertThat(cut.toString()).isEqualTo("Value: " + VALUE);
        }

        @Test
        void testJunk() {
            Optional<String> full = Optional.ofNullable("Full");
            String string = full.orElse("Empty");
            assertThat(string).isEqualTo("Full");
            Optional<String> empty = Optional.ofNullable(null);
            string = empty.orElse("Empty");
            assertThat(string).isEqualTo("Empty");
        }

    }

}