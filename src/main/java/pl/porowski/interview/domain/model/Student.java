package pl.porowski.interview.domain.model;


import java.math.BigDecimal;

public record Student(String name, BigDecimal rate) implements Comparable<Student> {
    @Override
    public int compareTo(Student o) {
        return this.rate.compareTo(o.rate);
    }

    @Override
    public String toString() {
        return String.format("%s,%s", name, rate);
    }
}
