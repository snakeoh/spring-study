<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title><spring:message code="member.register" /></title>
  </head>
  <body>
    <h2><spring:message code="member.info" /></h2>
    <!-- <form action="step3" method="post"> -->
    <form:form action="step3" modelAttribute="registerRequest">
    <%-- <form:form action="step3" commandName="registerRequest"> --%>
      <p>
        <label><spring:message code="email" />:<br>
        <!-- <input type="text" name="email" id="email" /> -->
        <form:input path="email" />
        <form:errors path="email" />
        </label>
      </p>
      <p>
        <label><spring:message code="name" />:<br>
        <!-- <input type="text" name="name" id="name" /> -->
        <form:input path="name" />
        <form:errors path="name" />
        </label>
      </p>
      <p>
        <label><spring:message code="password" />:<br>
        <!-- <input type="password" name="password" id="password" /> -->
        <form:password path="password" />
        <form:errors path="password" />
        </label>
      </p>
      <p>
        <label><spring:message code="password.confirm" />:<br>
        <form:password path="confirmPassword" />
        <form:errors path="confirmPassword" />
        </label>
      </p>
      <input type="submit" value="<spring:message code="register.btn" />" >
    </form:form>
  </body>
</html>
