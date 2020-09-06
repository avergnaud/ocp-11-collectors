package com;

import com.Employee;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import static java.lang.System.*;

public class M1 {

	static Stream<Employee> getStream() {

		Employee presto = new Employee("Presto",100,"HR");
                Employee jelly = new Employee("Jelly",10,"PROD");
                Employee marc = new Employee("Marc",20,"MKG");
                Employee fred = new Employee("Fred",20,"MKG");
		return Stream.of(presto,jelly,marc,fred);
	}

	public static void main(String... args) {

		/* joining */		
		Collector<CharSequence,?,String> c1 = joining(", ");
		String out1 = getStream()
			.map(Employee::getName)
			.collect(c1);
		out.println(out1);

		/* mapping */
		Collector<Employee,?,String> c2 = mapping(Employee::getName, c1);
		String out2 = getStream().collect(c2);
		out.println(out2);

		/* summingInt */
		Collector<Employee,?,Integer> c3 = summingInt(Employee::getSalary);
		Integer out3 = getStream().collect(c3);
		out.println(out3);

		/* groupingBy - standard */
		Collector<Employee,?,Map<String,List<Employee>>> c6 = groupingBy(
                        Employee::getDepartment
                );
                Map<String,List<Employee>> out6 = getStream().collect(c6);
                out.println(out6);
		
		/* groupingBy - Custom Downstream Collection */
		Collector<Employee,?,Map<String,List<Employee>>> c5 = groupingBy(
                        Employee::getDepartment,
                        toList()
                );
                Map<String,List<Employee>> out5 = getStream().collect(c5);
                out.println(out5);
	
		/* groupingBy - Custom Downstream Collection, ! java.lang.Comparable */
		Collector<Employee,?,Map<String,TreeSet<Employee>>> c51 = groupingBy(
                        Employee::getDepartment,
                        toCollection(TreeSet::new)
                );
                Map<String,TreeSet<Employee>> out51 = getStream().collect(c51);
                out.println(out51);

		/* groupingBy - Custom Downstream Collector, ! NOT a Collection */
		Collector<Employee,?,Map<String,String>> c4 = groupingBy(
			Employee::getDepartment,
			c2
		);
		Map<String,String> out4 = getStream().collect(c4);
		out.println(out4);

		/* groupingBy - Custom type returned, default toList() downstream Collector */
		Collector<Employee,?,TreeMap<String,List<Employee>>> c7 = groupingBy(
			Employee::getDepartment,
			TreeMap::new,
			toList()
		);
		TreeMap<String,List<Employee>> out7 = getStream().collect(c7);
		out.println(out7);
	}	
}
