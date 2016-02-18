<%@ tag body-content="empty" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="true"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="limit" required="true"%>
<%@ attribute name="search" required="false"%>

<c:set var="link" value="${target}?page=${page}&records=${limit}" />
<c:if test="${not empty search}">
	<c:set var="link">${link}&search=${search}</c:set>
</c:if>

<c:url value="${target}">
	<c:param name="page" value="${page}" />
	<c:param name="records" value="${limit}" />
	<c:if test="${not empty search}">
		<c:param name="search" value="${search}" />
	</c:if>
</c:url>
