package com.bacon.aifiles.ais.midbeatoptimal.processing.permutations;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

@Component
public class PermutationGenerator {
    public int factorial(int n) {
        return rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }

    public <T> List<T> permutation(int no, List<T> items) {
        return permutationHelper(no,
                new LinkedList<>(requireNonNull(items)),
                new ArrayList<>());
    }

    private <T> List<T> permutationHelper(int no, LinkedList<T> in, List<T> out) {
        if (in.isEmpty()) {
            return out;
        }

        int subFactorial = factorial(in.size() - 1);

        out.add(in.remove(no / subFactorial));
        return permutationHelper(no % subFactorial, in, out);
    }

    public <T> List<List<T>> allPermutations(List<T> list) {
        return range(0, factorial(list.size()))
                .mapToObj(i -> permutation(i, list))
                .collect(toList());
    }
}