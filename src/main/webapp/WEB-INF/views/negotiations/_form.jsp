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
<fmt:parseDate value="${negotiation.negotiationDate}" pattern="yyyy-MM-dd" var="negotiationDay" type="date" />
<label for="${AttributeConst.NEG_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.NEG_DATE.getValue()}" value="<fmt:formatDate value='${negotiationDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="${AttributeConst.NEG_SALES.getValue()}">営業担当</label><br />
<input type="text" name="${AttributeConst.NEG_SALES.getValue()}" value="${negotiation.salesRep}" />
<br /><br />


<label for="${AttributeConst.NEG_COMPANY.getValue()}">会社名</label><br />
<select name="${AttributeConst.NEG_COMPANY.getValue()}">
<option hidden></option>
<c:forEach var="customer" items="${customers}" varStatus="status">
<option><c:out value="${customer.companyName}" /></option>
</c:forEach>
</select>
<br /><br />

<label for="${AttributeConst.NEG_STATUS.getValue()}">商談状況</label><br />
<select name="${AttributeConst.NEG_STATUS.getValue()}">
<option>商談中</option>
<option>成約済み</option>
<option>見送り</option>
</select>
<br /><br />

<label for="${AttributeConst.NEG_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.NEG_CONTENT.getValue()}" rows="10" cols="50">${negotiation.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.NEG_ID.getValue()}" value="${negotiation.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>