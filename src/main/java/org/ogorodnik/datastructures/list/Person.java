package org.ogorodnik.datastructures.list;

class Person implements Comparable<Person>{
    int id;
    String name;

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return name.length() - o.name.length();
    }

    @Override
    public String toString(){
        return "Person{" + "id=" +id + ", name='" + name + '\'' + '}';
    }
}
