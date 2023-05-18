package az.springdata.demo.springdatademo;

import az.springdata.demo.springdatademo.model.Address;
import az.springdata.demo.springdatademo.model.Employee;
import az.springdata.demo.springdatademo.repository.EmployeeRepository;
import az.springdata.demo.springdatademo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;
    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee();

        employee.setId(1);
        employee.setName("Samir");
        employee.setSurname("Agayarov");
        employee.setAge(30);
        employee.setSalary(5000);

        Address address = new Address();
        address.setStreet("Baku, Azerbaijan Nizami street");

        employeeService.update(employee,address);

        employeeService.getAllEmployees(0,10,"name")
                .forEach(System.out::println);
    }
}
