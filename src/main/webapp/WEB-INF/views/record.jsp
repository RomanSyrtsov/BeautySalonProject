<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 20.05.2021
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
      <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

  <fmt:setBundle basename="app" var="lang"/>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="container mt-5">
    <div class="d-flex justify-content-center bd-highlight mb-3">

        <table border="0">
            <tr>
                <form action="controller?command=selectmaster" method="POST" >

                    <td><label for="SelectMaster">Select master: </label></td>
                    <td><select  id="SelectMaster" class="Select_By_Masters_Form" name="SelectId">
                        <option></option>
                        <c:forEach items="${masters}"  var="list1">
                            <option name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1.getFirstname()} ${list1.getLastname()}</option>
                        </c:forEach>
                    </select></td>
                </form>
            </tr>

            <c:if test="${masterId != null}">
            <tr>
                    <form action="controller?command=selectmaster" method="post">
                        <td><label for="SelectDate">Select date: </label></td>
                        <td><select id="SelectDate" class="SelectDate" name="SelectDate">
                            <c:forEach items="${dates}"  var="list2">
                                <option name="${list2}" value="${list2}" onclick="this.form.submit()">${list2}</option>
                            </c:forEach>
                        </select></td>
                    </form>
            </tr>
            </c:if>

            <c:if test="${date != null}">
            <tr>
                <form action="controller?command=selectmaster" method="post">
                    <td><label for="SelectTime">Select time: </label></td>
                    <td><select id = "SelectTime" class="SelectTime" name="SelectTime">
                        <c:forEach items="${times}"  var="list3">
                            <option name="${list3}" value="${list3}" onclick="this.form.submit()">${list3}</option>
                        </c:forEach>
                    </select></td>
                </form>
            </tr>
            </c:if>
            <c:if test="${time != null && date != null && masterId != null}">
            <tr>
                <td><form action="controller?command=makeRecord" method="post">
                    <input type="submit"/>
                </form></td>
            </tr>
            </c:if>
        </table>


<%--  <table class="table table-bordered">--%>

 <%--   <thead>
    <tr>
      <th scope="col">#</th>
      <c:forEach items="${dates}"  var="list2">
        <th scope="col">${list2}</th>
      </c:forEach>
    </tr>
    </thead>
    <tbody>

    <c:set var="count" value="0" scope="page" />

    <c:forEach var="date" items="${schedule.keySet()}">

        <c:forEach var="key" items="${schedule[date].keySet()}">
            <tr>
            <c:if test="${count < 4}">
                <th scope="row">${key}</th>
                <td style="cursor: pointer" onclick="this.form.submit()">${schedule[date][key]}</td>

                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:if>
            &lt;%&ndash;<c:if test="${count >= 4}">
                <td style="cursor: pointer" onclick="this.form.submit()">${schedule[date][key]}</td>
            </c:if>&ndash;%&gt;
            </tr>
        </c:forEach>
    </c:forEach>
--%>



      <%--<c:forEach var="type2" items="${times}">
      <tr>
        <th scope="row">${type2}</th>
          &lt;%&ndash;<c:forEach var="type" items="${dates}">&ndash;%&gt;
            <c:if test="${records.get(count) != 0}">
                <td style="background-color: crimson">${records.get(count)}</td>
            </c:if>
            <c:if test="${records.get(count) == 0}">
              <form action="controller?command=makeRecord" method="post">
                  <input type="hidden" name="dateField" value="${type}" }/>
                  <input type="hidden" name="timeField" value="${type2}" }/>

                  <td style="cursor: pointer" onclick="this.form.submit()">${records.get(count)}<input type="submit"></td>
              </form>
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
          &lt;%&ndash;</c:forEach>&ndash;%&gt;
      </tr>
      </c:forEach>--%>




<%--      <c:forEach items="${times}"  var="list3">
        <tr>
          <th scope="row">${list3}</th>
          <c:forEach items="${dates}"  var="list2">
            <td style="cursor: pointer" onclick="document.location='controller?command=/'"></td>
          </c:forEach>
        </tr>
      </c:forEach>--%>
<%--
    </tbody>
  </table>--%>
</div>
</div>
</body>
</html>
