package com.selesy.hibp;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

import lombok.EqualsAndHashCode;

/**
 * Provides a specialization of Either with the left element containing a
 * value or the right element containing a Throwable.  Like Either, the value
 * and the Throwable are mutually exclusive (only one may be present).  Methods
 * are provided to mimic the behavior of Optional to promote its use in
 * functions.
 */
@EqualsAndHashCode
public class Possible<T> {

    static final String NO_ERROR_MESSAGE = "Throwable was not present";
    static final String NO_VALUE_MESSAGE = "Value was not present";

    Throwable error;
    T value;

    private Possible(T value, Throwable error) {
        this.error = error;
        this.value = value;
    }

    /**
     * Constructs a Possible object when a value is not available.
     * 
     * @param <T>  the type of the Possible's value field
     * @param error  the Throwable describing the problem encountered when
     *               trying to supply the value
     */
    public static <T> Possible<T> of(Throwable error) {
        return new Possible<T>(null, error);
    }

    /**
     * Constructs a Possible object when a value is available.
     * 
     * @param <T>  the type of the Possible's value field
     * @param value the object of type <T> that will be stored in the
     *              value field
     */
    public static <T> Possible<T> of(T value) {
        return new Possible<T>(value, null);
    }

    /**
     * Retrieves the error field if it is present.
     * 
     * @return a Throwable representing the error encountered when trying
     *         to supply the value
     * 
     * @throws NoSuchElementException if this Possible contains a value
     */
    public Throwable error() {
        if(isPresent()) {
            throw new NoSuchElementException(NO_ERROR_MESSAGE);
        }
        return error;
    }

    /**
     * Retrieves the value field if it is present.
     * 
     * @return the value with type <T>
     * 
     * @throws NoSuchElementException if this possible contains an error
     */
    public T get() {
        if(!isPresent()) {
            throw new NoSuchElementException(NO_VALUE_MESSAGE);
        }
        return value;
    }

    /**
     * Applies the Consumer to the value field's contents if it is present.
     * 
     * @param consumer the function to apply to the value
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if(isPresent()) {
            consumer.accept(get());
        }
    }

    /**
     * Indicates whether the value field has been populated.
     * 
     * @return true if the value exists, otherwise false.
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Provides an Optional<T> based on whether or not this Possible contains
     * a value.
     * 
     * @return the requested Optional<T>
     */
    public Optional<T> optional() {
        return Optional.ofNullable(value);
    }

    /**
     * Provides a string representation of this Possible indicating whether
     * the instance contains a value or a Throwable.  This String is suitable
     * for debugging.
     * 
     * @return a string representation of this Possible
     */
    public String toString() {
        if(isPresent()) {
            return "Value: " + value.toString();
        } else {
            return "Error: " + error.getClass().getSimpleName() + ", " + error.getMessage();
        }

        // TODO - Figure out why this doesn't work!
        //
        // return Optional.of("Test")
        //     .map(Object::toString)
        //     .orElse("Error: " + error.getClass().getSimpleName() + ", " + error.getMessage());
    }
    
}