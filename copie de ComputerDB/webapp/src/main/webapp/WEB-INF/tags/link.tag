<%@ tag body-content="empty" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="false"%>
<%@ attribute name="page" required="false"%>
<%@ attribute name="limit" required="false"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="searchCompany" required="false"%>
<%@ attribute name="del" required="false"%>

<c:set var="link" value="${target}?page=${page}&records=${limit}" />
<c:if test="${not empty search}">
	<c:set var="link">${link}&search=${search}</c:set>
</c:if>

<c:url value="${target}">
	<c:if test="${not empty page}">
		<c:param name="page" value="${page}" />
	</c:if>
	<c:if test="${not empty limit}">
		<c:param name="records" value="${limit}" />
	</c:if>
	<c:if test="${not empty search}">
		<c:param name="search" value="${search}" />
	</c:if>
	<c:if test="${not empty searchComputer}">
		<c:param name="searchComputer" value="${searchComputer}" />
	</c:if>
	<c:if test="${not empty del}">
		<c:param name="del" value="${del}" />
	</c:if>
</c:url>
