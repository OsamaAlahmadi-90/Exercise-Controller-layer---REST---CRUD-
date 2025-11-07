package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    ArrayList<Customer> customers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("The customer has been added");
    }

    @PutMapping("update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer){
        customers.set(index,customer);
        return new ApiResponse("The customer has been updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("The customer has been deleted");
    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable double amount){
        customers.get(index).setBalance(customers.get(index).getBalance() + amount);
        return new ApiResponse(amount + " has been added to the customer");
    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw (@PathVariable int index, @PathVariable double amount){
        if(customers.get(index).getBalance() - amount >= 0){
            customers.get(index).setBalance(customers.get(index).getBalance() - amount);
            return new ApiResponse(amount +" has been withdrew from customer");
        }else{
            return new ApiResponse("Cannot withdraw " + amount + " from the customer");
        }
    }
}
