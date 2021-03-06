package kr.sm.itaewon.routepang.service;

import kr.sm.itaewon.routepang.model.Basket;
import kr.sm.itaewon.routepang.model.detail.CustomerPage;
import kr.sm.itaewon.routepang.model.Customer;
import kr.sm.itaewon.routepang.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FollowService followService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private ArticleService articleService;


    public Customer findByCustomerId(long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
//        customer.setPassword("");
        return customer;
    }

    public Customer findByAccount(String account) {
        Customer customer = customerRepository.findByAccount(account);
//        if(customer !=null)
//            customer.setPassword("");
        return customer;
    }

    public void save(Customer customer){
        //basketService.crateBasket(customer);
        customerRepository.save(customer);
    }

    public CustomerPage getCustomerPageByCustomerId(long customerId) {
        CustomerPage customerPage = new CustomerPage();

        Customer customer = findByCustomerId(customerId);

        customerPage.setCustomerId(customerId);
        customerPage.setEmail(customer.getEmail());
        customerPage.setReference(customer.getReference());
        customerPage.setFollowingCount(followService.getFollowingCount(customer));
        customerPage.setFollowerCount(followService.getFollowerCount(customer));

        customerPage.setArticleCount(articleService.countArticlesByCustomer(customer));

        Basket basket = basketService.findByCustomer(customer);
        customerPage.setProductCount(productService.countByBasket(basket));

        customerPage.setRouteCount(routeService.countByCustomer(customer));
        return customerPage;
    }
}
