package kr.sm.itaewon.routepang.repo;

import kr.sm.itaewon.routepang.model.Customer;
import kr.sm.itaewon.routepang.model.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {

    List<Route> findByParentId(long parentId);

    List<Route> findByCustomer(Customer customer);

    @Query(value = "SELECT * FROM route WHERE customer_id = ? AND parent_id = 0", nativeQuery = true)
    List<Route> findByCustomerAndTop(Customer customer);

    Integer deleteAllByCustomer(Customer customer);

    Route findByRouteId(long routeId);

    int countByCustomer(Customer customer);
}