package three.stone.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Person {
    private int id;
    private String name;
    private List<Car> cars;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Person(int id, String name, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public static void main(String[] args) {
        Person p1 = new Person(1, "aa", Arrays.asList(new Car("car1"), new Car("car2"), new Car("car3")));
        Person p2 = new Person(2, "bb", Arrays.asList(new Car("car1"), new Car("car2")));
        Person p3 = new Person(3, "cc", Arrays.asList(new Car("car1"), new Car("car2")));
        List<Person> persons = Arrays.asList(p1, p2, p3);
        List<Integer> sortedPerson = persons.stream().sorted(Comparator
                .comparing((Person p) -> p.getCars().size())
                .thenComparing(p -> p.name)
                .reversed())
                .map(p -> p.id)
                .collect(Collectors.toList());
        System.out.println(sortedPerson);


        PriorityQueue<Person> minHeap = new PriorityQueue<>(Comparator.comparing(p -> p.getCars().size()));
        PriorityQueue<Person> maxHeap = new PriorityQueue<>(Comparator
                .comparing((Person p) -> p.getCars().size())
                .thenComparing(p -> p.name)
                .reversed());



    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }

    static class Car {
        private String carName;

        public Car(String carName) {
            this.carName = carName;
        }
    }
}
