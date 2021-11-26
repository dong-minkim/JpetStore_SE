package org.mybatis.jpetstore.web.actions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.mybatis.jpetstore.domain.Order;
import org.mybatis.jpetstore.domain.Popular;
import org.mybatis.jpetstore.service.OrderService;

/**
 * The Class OrderActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class OrderActionBean extends AbstractActionBean {

  private static final long serialVersionUID = -6171288227470176272L;

  private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
  private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
  private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
  private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
  private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
  private static final String VIEW_POPULAR = "/WEB-INF/jsp/order/ViewPopular.jsp";

  private static final List<String> CARD_TYPE_LIST;

  @SpringBean
  private transient OrderService orderService;

  private Order order = new Order();
  private int orderId;
  private boolean shippingAddressRequired;
  private boolean confirmed;
  private List<Order> orderList;
  private List<Popular> popularList;

  static {
    CARD_TYPE_LIST = Collections.unmodifiableList(Arrays.asList("Visa", "MasterCard", "American Express"));
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public boolean isShippingAddressRequired() {
    return shippingAddressRequired;
  }

  public void setShippingAddressRequired(boolean shippingAddressRequired) {
    this.shippingAddressRequired = shippingAddressRequired;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  public List<String> getCreditCardTypes() {
    return CARD_TYPE_LIST;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public List<Popular> getPopularList(){
    return popularList;}

  /**
   * List orders.
   *
   * @return the resolution
   */
  public Resolution listOrders() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    orderList = orderService.getOrdersByUsername(accountBean.getAccount().getUsername());
    return new ForwardResolution(LIST_ORDERS);
  }

  /**
   * New order form.
   *
   * @return the resolution
   */
  public Resolution newOrderForm() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    CartActionBean cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");

    clear();
    if (accountBean == null || !accountBean.isAuthenticated()) {
      setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
      return new ForwardResolution(AccountActionBean.class);
    } else if (cartBean != null) {
      order.initOrder(accountBean.getAccount(), cartBean.getCart());
      return new ForwardResolution(NEW_ORDER);
    } else {
      setMessage("An order could not be created because a cart could not be found.");
      return new ForwardResolution(ERROR);
    }
  }

  /**
   * New order.
   *
   * @return the resolution
   */
  public Resolution newOrder() {
    HttpSession session = context.getRequest().getSession();

    if (shippingAddressRequired) {
      shippingAddressRequired = false;
      return new ForwardResolution(SHIPPING);
    } else if (!isConfirmed()) {
      return new ForwardResolution(CONFIRM_ORDER);
    } else if (getOrder() != null) {

      orderService.insertOrder(order);

      CartActionBean cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");
      cartBean.clear();

      setMessage("Thank you, your order has been submitted.");

      return new ForwardResolution(VIEW_ORDER);
    } else {
      setMessage("An error occurred processing your order (order was null).");
      return new ForwardResolution(ERROR);
    }
  }

  /**
   * View order.
   *
   * @return the resolution
   */
  public Resolution viewOrder() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

    order = orderService.getOrder(orderId);

    if (accountBean.getAccount().getUsername().equals(order.getUsername())) {
      return new ForwardResolution(VIEW_ORDER);
    } else {
      order = null;
      setMessage("You may only view your own orders.");
      return new ForwardResolution(ERROR);
    }
  }
  /*
  차별화 기능 추가 - delOrder
   */
  public Resolution delOrder(){
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

    System.out.println(orderId);
    order = orderService.getOrderDate(orderId);

    if (order!= null){
      orderService.delOrder(orderId);
      return new RedirectResolution(OrderActionBean.class, "listOrders");
    }else{
      setMessage("Can't cancel your Order.");
      return new ForwardResolution(ERROR);
    }

  }

  /*
   *필수 기능 추가
   */
  public Resolution viewPopular(){

    popularList=orderService.getPopularOrderLists();

    return new ForwardResolution(VIEW_POPULAR);
  }

  /**
   * Clear.
   */
  public void clear() {
    order = new Order();
    shippingAddressRequired = false;
    confirmed = false;
    orderList = null;
  }

}
