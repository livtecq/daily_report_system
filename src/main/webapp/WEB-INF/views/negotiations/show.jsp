<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actNeg" value="${ForwardConst.ACT_NEG.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>商談 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${negotiation.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${negotiation.negotiationDate}" pattern="yyyy-MM-dd" var="negotiationDay" type="date" />
                    <td><fmt:formatDate value='${negotiationDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>営業担当</th>
                    <td><pre><c:out value="${negotiation.salesRep}" /></pre></td>
                </tr>
                <tr>
                    <th>会社名</th>
                    <td><pre><c:out value="${negotiation.customerId.companyName}" /></pre></td>
                </tr>
                <tr>
                    <th>商談状況</th>
                    <td><pre><c:out value="${negotiation.negotiationStatus}" /></pre></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${negotiation.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${negotiation.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${negotiation.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_employee.id == negotiation.employee.id}">
            <p>
                <a href="<c:url value='?action=${actNeg}&command=${commEdt}&id=${negotiation.id}' />">この商談を編集する</a>
            </p>
        </c:if>

        <p>
            <a href="<c:url value='?action=${actNeg}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>