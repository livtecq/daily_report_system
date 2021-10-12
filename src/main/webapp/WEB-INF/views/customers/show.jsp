<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>顧客 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${customer.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${customer.customerDate}" pattern="yyyy-MM-dd" var="customerDay" type="date" />
                    <td><fmt:formatDate value='${customerDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>顧客名</th>
                    <td><pre><c:out value="${customer.customerName}" /></pre></td>
                </tr>
                <tr>
                    <th>顧客名カナ</th>
                    <td><pre><c:out value="${customer.customerKana}" /></pre></td>
                </tr>
                <tr>
                    <th>会社名</th>
                    <td><pre><c:out value="${customer.companyName}" /></pre></td>
                </tr>
                <tr>
                    <th>固定電話</th>
                    <td><pre><c:out value="${customer.phoneNumber}" /></pre></td>
                </tr>
                <tr>
                    <th>携帯番号</th>
                    <td><pre><c:out value="${customer.mobileNumber}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${customer.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${customer.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actCus}&command=${commEdt}&id=${customer.id}' />">この顧客情報を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actCus}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>