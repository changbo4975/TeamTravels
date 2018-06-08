<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ !empty listPinpoint}">
		<c:forEach items="${listPinpoint}" var="listPinpoint"
			varStatus="status">
			<tr>
				<td style="text-align: center;"><c:out
						value="${ status.count }" /></td>
     			<td style="text-align: center;"><c:out
						value="${ listPinpoint.no }" /></td>
				<td style="text-align: center;"><c:out
						value="${ listPinpoint.travelRecordNo } "/></td>
				<td style="text-align: center;"><c:out
						value="${ listPinpoint.email }" /></td>
				<td style="text-align: center;"><c:out
						value="${ listPinpoint.latitude }" /></td>
				<td style="text-align: center;"><c:out
						value="${ listPinpoint.longitude }" /></td>
				<td style="text-align: center;"><c:out
						value="${ listPinpoint.range }" /></td>
				<td style="text-align: center;"><c:out
						value="${ listPinpoint.iconNo }" /></td>
				<%-- <td style="text-align: center;"><a
					href="<c:url value="/attendance/view/${listPinpoint.no}" />">
						<input type="button" value="상세조회">
				</a></td>
				<td style="text-align: center;"><a
					href="<c:url value="/attendance/remove/${attendance.attendanceNo}" />">
						<input type="button" value="삭제">
				</a></td> --%>
			</tr>
		</c:forEach>
	</c:if>
</body>
</html>