package in.code.repository;

import in.code.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "UPDATE EMPLOYEES SET salary = :salary WHERE employee_id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void partialUpdateEmployeeSalary(@Param(value = "salary") Double salary, @Param(value = "id") Integer id);
}
