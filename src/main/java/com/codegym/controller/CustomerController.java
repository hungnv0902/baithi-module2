package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Page<Province> provinces(Pageable pageable) {
        return provinceService.findAll(pageable);
    }

    @GetMapping("/create-customer")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView createCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message","Fail Create Customer");
            return modelAndView;
        } else {

            customerService.save(customer);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message","Create Customer successfully");
            return modelAndView;
        }
    }

    @GetMapping("/customers")
    public ModelAndView listCustomer(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        Page<Customer> customers = customerService.findAll(pageable);
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView editCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message","Fail Edit Customer");
            return modelAndView;
        } else {

            customerService.save(customer);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message","Edit Customer successfully");
            return modelAndView;
        }
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/delete-customer")
    public ModelAndView deleteCustomer(@ModelAttribute("customer") Customer customer, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        customerService.remove(customer.getId());
        Page<Customer> customers = customerService.findAll(pageable);
        modelAndView.addObject("customers",customers);

        return modelAndView;
    }

    @GetMapping("/view-customer/{id}")
    public ModelAndView viewCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @GetMapping("/search-customer")
    public ModelAndView searchCustomer(@RequestParam("province") String provinceName,@PageableDefault(size = 5) Pageable pageable) {
        Province province = provinceService.findByName(provinceName);
        Page<Customer> customers = customerService.findByProvince(province,pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/search");
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }
}