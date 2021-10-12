<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="actNeg" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>顧客　一覧</h2>
        <table id="customer_list">
            <tbody>
                <tr>
                    <th class="customer_name">顧客名</th>
                    <th class="company_name">会社名</th>
                    <th class="phone_number">固定電話</th>
                    <th class="mobile_number">携帯番号</th>
                    <th class="customer_action">操作</th>
                </tr>
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <fmt:parseDate value="${customer.customerDate}" pattern="yyyy-MM-dd" var="customerDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="customer_name"><c:out value="${customer.customerName}" /></td>
                        <td class="company_name"><c:out value="${customer.companyName}" /></td>
                        <td class="phone_number">${customer.phoneNumber}</td>
                        <td class="mobile_number">${customer.mobileNumber}</td>
                        <td class="customer_action"><a href="<c:url value='?action=${actCus}&command=${commShow}&id=${customer.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${customers_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((customers_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actCus}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actCus}&command=${commNew}' />">新規顧客の登録</a></p>

    </c:param>
</c:import>