package org.mybatis.jpetstore.mapper;

import java.util.List;

import org.mybatis.jpetstore.domain.Order;
import org.mybatis.jpetstore.domain.Popular;

/**
 * The Interface OrderMapper.
 *
 * @author Eduardo Macarron
 */
public interface OrderMapper {

  List<Order> getOrdersByUsername(String username);

  List<Popular> getPopularOrderLists();

  Order getOrder(int orderId);

  Order getOrderDate(int orderId);

  void insertOrder(Order order);

  void insertOrderStatus(Order order);

  void delOrder(int orderId);

  void delOrderStatus(int orderId);

  void BackInventoryQuantity(int orderId);
}