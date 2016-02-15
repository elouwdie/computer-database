<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="date" required="true" type="java.time.LocalDate" %>
<fmt:parseDate value="${date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>