<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<fmt:parseDate value="${customer.customerDate}" pattern="yyyy-MM-dd" var="customerDay" type="date" />
<label for="${AttributeConst.CUS_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.CUS_DATE.getValue()}" value="<fmt:formatDate value='${customerDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="${AttributeConst.CUS_NAME.getValue()}">顧客名</label><br />
<input type="text" name="${AttributeConst.CUS_NAME.getValue()}" value="${customer.customerName}" />
<br /><br />

<label for="${AttributeConst.CUS_KANA.getValue()}">顧客名カナ</label><br />
<input type="text" name="${AttributeConst.CUS_KANA.getValue()}" value="${customer.customerKana}" />
<br /><br />

<label for="${AttributeConst.CUS_COM.getValue()}">会社名</label><br />
<input type="text" name="${AttributeConst.CUS_COM.getValue()}" value="${customer.companyName}" />
<br /><br />

<label for="${AttributeConst.CUS_PHONE.getValue()}">固定電話</label><br />
<input type="text" name="${AttributeConst.CUS_PHONE.getValue()}" value="${customer.phoneNumber}" />
<br /><br />

<label for="${AttributeConst.CUS_MOBILE.getValue()}">携帯番号</label><br />
<input type="text" name="${AttributeConst.CUS_MOBILE.getValue()}" value="${customer.mobileNumber}" />
<br /><br />

<input type="hidden" name="${AttributeConst.CUS_ID.getValue()}" value="${customer.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>