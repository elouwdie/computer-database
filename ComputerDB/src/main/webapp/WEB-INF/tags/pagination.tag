<%@ tag body-content="empty" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@ attribute name="currentPage" required="true"%>
<%@ attribute name="noOfPages" required="true"%>
<li><a
	href=<tags:link target="computerdb" page="${currentPage - 1}" limit="${records}"
				search="${search }" />
	aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
</a></li>
<c:choose>
	<c:when test="${noOfPages - currentPage < 5}">
		<c:set var="begin" value="${currentPage}" scope="page" />
		<c:set var="end" value="${noOfPages}" scope="page" />

	</c:when>
	<c:when test="${currentPage < 6}">
		<c:set var="begin" value="1" scope="page" />
		<c:set var="end" value="9" scope="page" />
		<c:if test="${noOfPages < 9}">
			<c:set var="end" value="${noOfPages}" scope="page" />
		</c:if>

	</c:when>
	<c:otherwise>
		<c:set var="begin" value="${currentPage - 4}" scope="page" />
		<c:set var="end" value="${currentPage + 4}" scope="page" />

	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${begin}" end="${end}" step="1">
	<c:choose>
		<c:when test="${currentPage eq i }">
			<li><a href="#">${i}</a></li>
		</c:when>
		<c:otherwise>
			<li><a
				href=<tags:link target="computerdb" page="${i}" limit="${records}" search="${search }"/>>${i}</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${currentPage lt noOfPages}">
	<li><a
		href=<tags:link target="computerdb" page="${currentPage + 1}" limit="${records}" search="${search }"/>
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</c:if>