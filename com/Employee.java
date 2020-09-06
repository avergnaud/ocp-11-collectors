package com;

class Employee implements Comparable<Employee> {

        String name; 
	int salary; 
	String department;

        Employee(String name, int salary, String department) {
		this.name=name;
		this.salary=salary;
		this.department=department;
	}

	public int compareTo(Employee other) {
		return this.name.compareTo(other.name);
	}
	
        String getName(){return this.name;}

        int getSalary(){return this.salary;}

        String getDepartment(){return this.department;}

        public String toString() {return "Employee_"+this.name;}
}
