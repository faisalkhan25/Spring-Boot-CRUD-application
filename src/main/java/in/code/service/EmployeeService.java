package in.code.service;

import in.code.entity.Employee;
import in.code.repository.EmployeeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @CachePut(value = "employee", key = "#employee.employeeId")
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Cacheable(value = "employee", key = "#id", unless = "#result == null")
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "AllEmployee")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @CacheEvict(value = "employee", key = "#id")
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
    @CachePut(value = "employee", key = "#id")
    public Employee partialUpdateEmployee(Double salary, Integer id) {
        employeeRepository.partialUpdateEmployeeSalary(salary, id);
        return getEmployeeById(id);
    }
}
