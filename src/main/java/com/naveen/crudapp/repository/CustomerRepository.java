package com.naveen.crudapp.repository;

import com.naveen.crudapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(value = "SELECT firstname, lastname from customer WHERE age in (25,45)", nativeQuery = true)
    List<Customer> getCustomerDetailsByFirstName();
    //    List<Customer> getCustomerDetailsByFirstName(@Param("age") List<Integer> agelist);

}
