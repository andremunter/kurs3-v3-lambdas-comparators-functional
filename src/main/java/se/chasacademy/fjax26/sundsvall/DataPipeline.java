package se.chasacademy.fjax26.sundsvall;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataPipeline<T, R> {

    private Predicate<T> filterCondition = item -> true;
    private Function<T, R> function;

    public DataPipeline<T, R> filter(Predicate<T> predicate) {
        Objects.requireNonNull(predicate, "Filter cannot be null");
        this.filterCondition = this.filterCondition.and(predicate);
        return this;
    }


    public DataPipeline<T, R> map(Function<T, R> function) {
        Objects.requireNonNull(function, "Mapper cannot be null");
        this.function = function;
        return this;
    }

    public void execute(List<T> source, Consumer<R> action) {
        Objects.requireNonNull(source, "Source-list cannot be null");
        Objects.requireNonNull(action, "Action cannot be null");

        if (function == null) {
            throw new IllegalStateException("Cannot execute pipeline without defined function");
        }

        source.stream()
                .filter(filterCondition)
                .map(function)
                .forEach(action);
    }

}
