<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actNeg" value="${ForwardConst.ACT_NEG.getValue()}" />
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
        <h2>商談　一覧</h2>
        <table id="negotiation_list">
            <tbody>
                <tr>
                    <th class="sales_rep">営業担当</th>
                    <th class="negotiation_date">日付</th>
                    <th class="company_name">会社名</th>
                    <th class="negotiation_status">商談状況</th>
                    <th class="negotiation_action">操作</th>
                </tr>
                <c:forEach var="negotiation" items="${negotiations}" varStatus="status">
                    <fmt:parseDate value="${negotiation.negotiationDate}" pattern="yyyy-MM-dd" var="negotiationDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="sales_rep"><c:out value="${negotiation.salesRep}" /></td>
                        <td class="negotiation_date"><fmt:formatDate value='${negotiationDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="company_name"><c:out value="${negotiation.companyName}" /></td>
                        <td class="negotiation_status"><c:out value="${negotiation.negotiationStatus}" /></td>
                        <td class="negotiation_action"><a href="<c:url value='?action=${actNeg}&command=${commShow}&id=${negotiation.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${negotiations_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((negotiations_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actNeg}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actNeg}&command=${commNew}' />">新規商談の登録</a></p>

    </c:param>
</c:import>