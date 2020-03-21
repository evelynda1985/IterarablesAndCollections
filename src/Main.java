import java.util.*;

import static java.util.Collections.emptyList;

public class Main {

    public static void main(String[] args) {

        Person person1 = new Person("Ana", "Agredo", 15);
        Person person2 = new Person("Sara", "Agredo", 45);
        Person person3 = new Person("Sara", "Agredo", 35);
        Person person4 = new Person("Diana", "Sanchez", 35);
        Person person5 = new Person("Isabela", "Santodomingo", 55);
        Person person6 = new Person("Juan", "Valdez", 75);


        Address workAddress1 = new Address("123 Monroise", "Dorchester", "02125", "MA");
        Address workAddress2 = new Address("123 Monroise", "Dorchester", "02125", "IA");
        Address workAddress3 = new Address("123 Monroise", "Dorchester", "02125", "IL");

        List<Person> people = new ArrayList(Arrays.asList(person1, person2, person3, person4, person5, person6));

        System.out.println("print people");
        people.forEach(person -> System.out.println(person));
        System.out.println("print people----------------------->");
        people.forEach(System.out::println);

        people.removeIf(person -> person.getAge() < 20);
        System.out.println("print people with older than 20");
        people.forEach(System.out::println);

        System.out.println("Put the names in Uppercase");
        people.replaceAll(person -> new Person(person.getFirstName().toUpperCase(), person.getLastName().toUpperCase(), person.getAge()));

        System.out.println("order people by firstName and age");
        people.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getAge));
        people.forEach(System.out::println);

        Map<String, List<Person>> map = new HashMap<>();
        map.forEach((city, peopleList) -> System.out.println(city + ": " + peopleList.size() + " people"));

        // Allows one to check if a key is present in a map or not
        // getOrDefault -> if the city is not in the map, return a empty list instead of a null
        System.out.println("People from Paris: " + map.get("paris"));
        System.out.println("People from Paris: " + map.getOrDefault("paris", Collections.EMPTY_LIST));


        //New version of the put method
        map.putIfAbsent("boston", new ArrayList<>());
        map.get("boston").add(person1);
        map.get("boston").add(person2);

        System.out.println("People from boston: " + map.getOrDefault("boston", Collections.EMPTY_LIST));
        System.out.println("People from boston: " + map.getOrDefault("boston", Collections.EMPTY_LIST));

        //The computeIfAbsent()
        //The key passed as a parameter, should not be in the map
        //The lambda to compute the mapping from the key
        map.computeIfAbsent("New York", city -> new ArrayList<>()).add(person3);
        map.forEach((city, peopleList) -> System.out.println(city + ": " + peopleList));


        Map<String, List<Person>> map2 = new HashMap<>();
        map2.computeIfAbsent("Alexandria", city -> new ArrayList<>()).add(person4);
        map2.computeIfAbsent("Alexandria", city -> new ArrayList<>()).add(person5);
        map2.computeIfAbsent("Austin", city -> new ArrayList<>()).add(person6);
        map2.forEach((city, peopleList) -> System.out.println(city + ": " + peopleList));

        map2.forEach(
                (city, peopleList) -> {
                    map.merge(
                            city, peopleList,
                            (peopleFromMap, peopleFromMap2) -> {
                                peopleFromMap.addAll(peopleFromMap2);
                                return peopleFromMap;
                            });
                });

        System.out.println("Merged map with map2-------------->");
        map.forEach((city, peopleList) -> System.out.println(city + ": " + peopleList));

        //New replace method
        //replaceAll

        //New remove method
        //remove

        //new compute method
        //The key passed as a parameter, that may not be in the map
        //the value that may be associated with that key, or null
        //the lambda that will compute the remapping


        //The computeIfPresent()
        //The key is present in the map,
        //The existing value can't be null
        //The lambda to compute the remapping from the key and the existing value

    }
}
