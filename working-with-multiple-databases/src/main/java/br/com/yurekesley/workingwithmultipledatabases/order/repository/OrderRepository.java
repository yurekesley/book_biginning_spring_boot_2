package br.com.yurekesley.workingwithmultipledatabases.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yurekesley.workingwithmultipledatabases.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{ 

}
