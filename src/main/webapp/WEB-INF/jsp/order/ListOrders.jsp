<%@ include file="../common/IncludeTop.jsp"%>

<h2>My Orders</h2>

<table>
	<tr>
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
		<th>Order Cancel</th>
	</tr>

	<c:forEach var="order" items="${actionBean.orderList}">
		<tr>
			<td><stripes:link
					beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean"
					event="viewOrder">
				<stripes:param name="orderId" value="${order.orderId}" />
				${order.orderId}
			</stripes:link></td>
			<td><fmt:formatDate
					value="${order.orderDate}"
					pattern="yyyy/MM/dd hh:mm:ss" /></td>
			<td>$<fmt:formatNumber value="${order.totalPrice}"
								   pattern="#,##0.00" /></td>
			<td>
				<stripes:link class="Button"
							  beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean"
							  event="delOrder">
					<stripes:param name="orderId" value="${order.orderId}" />
					CANCEL
				</stripes:link>
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../common/IncludeBottom.jsp"%>