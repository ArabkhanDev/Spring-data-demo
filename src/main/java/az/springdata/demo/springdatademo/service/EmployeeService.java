package az.springdata.demo.springdatademo.service;

import az.springdata.demo.springdatademo.model.Address;
import az.springdata.demo.springdatademo.model.Employee;
import az.springdata.demo.springdatademo.repository.AddressRepository;
import az.springdata.demo.springdatademo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public Iterable<Employee>getAllEmployees(int page,int size,String property){

        Sort sort = Sort.by(Sort.Order.desc(property));
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageRequest);
    }

    public List<Employee>getEmployeesByName(String name, Pageable pageable){

        PageRequest pageRequest = PageRequest.of(0,15);
        return employeeRepository.findByName(name,pageRequest);
    }

    public List<Employee>getEmployeesByNameAndSurname(String name,String surname){
        return employeeRepository.findByNameAndSurname(name,surname);
    }

    public Employee getEmployeeById(long id){
        return employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found with given id:"+id));
    }

    public void insert(Employee employee){
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(Employee employee, Address address){

        addressRepository.save(address);

        if(employee.getId()<=0){
            throw new IllegalArgumentException("id can not be empty");
        }
        employeeRepository.save(employee);
    }

    public void delete(long id){
        employeeRepository.deleteById(id);
    }


}
