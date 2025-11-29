package in.code.api;

import in.code.entity.Employee;
import in.code.exception.ResourceNotFoundException;
import in.code.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);

        if(employee != null) {
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Employee with id: " + id + " not found");
        }
    }

    @GetMapping(
            value = "/all",
            produces = "application/json"
    )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.save(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/update",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        Employee employeeFromDb = employeeService.getEmployeeById(employee.getEmployeeId());
        if(employeeFromDb != null) {
            Employee updatedEmployee = employeeService.save(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Employee with id: " + employee.getEmployeeId() + " not found");
        }
    }

    @PatchMapping(
            value = "/partialUpdate/{id}",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable Integer id, @RequestBody Map<String, Object> employeeData) {

        Employee updatedEmployee = employeeService.partialUpdateEmployee((Double) employeeData.get("salary"), id);

        if(updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Employee with id: " + id + " not found");
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        Employee employeeFromDB = employeeService.getEmployeeById(id);

        if(employeeFromDB != null) {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            throw new ResourceNotFoundException("Employee with id: " + id + " not found");
        }
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        List<FieldError> list = ex.getBindingResult().getFieldErrors();
//
//        list.forEach(error -> {
//            errors.put(error.getField(), error.getDefaultMessage());
//        });
//
//        return errors;
//    }


}
