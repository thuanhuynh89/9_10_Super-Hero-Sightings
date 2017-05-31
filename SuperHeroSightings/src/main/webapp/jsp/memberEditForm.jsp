<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello Controller Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      
        <link href="${pageContext.request.contextPath}/css/SuperHeroCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container">
            <h1>HERO EDIT</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organizationCRUD/displayOrganizationPage" id="heroes">Organization </a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/memberCRUD/displayMemberPage" id="heroes">Member </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/addressCRUD/displayAddressPage" id="sightings">Address </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/locationCRUD/displayLocationPage" id="sightings">Location </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/sightingCRUD/displaySightingPage" id="sightings">Sighting </a></li>
                </ul>    
            </div>

            <br><br>    
            <sf:form class="form-horizontal" role="form" modelAttribute="member"
                     action="editMember" method="POST">
                <div class="form-group">
                    <label for="add-startDate" class="col-md-4 control-label">Start Date:</label>
                    <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-startDate"
                                  path="startDate" placeholder="StartDate"/>
                        <sf:errors path="startDate" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-endDate" class="col-md-4 control-label">End Date:</label>
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-endDate"
                                  path="endDate" placeholder="EndDate"/>
                        <sf:errors path="endDate" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-hero" class="col-md-4" id="right">Hero:</label>
                        <div class="col-md-4">

                        <sf:select  path="hero.heroId" id="heroDropDown"  >
                            <c:forEach var="heroList" items="${heroList}">
                                <sf:option value="${heroList.heroId}"> ${heroList.alias}</sf:option>
                            </c:forEach>
                        </sf:select>

                        <sf:errors path="hero.heroId" cssclass="error"></sf:errors>
                    </div>
                </div>
                    <div class="form-group">
                        <label for="add-organization" class="col-md-4" id="right">Organization:</label>
                        <div class="col-md-4">

                        <sf:select  path="organization.organizationId" id="organizationDropDown"  >
                            <c:forEach var="organizationList" items="${organizationList}">
                                <sf:option value="${organizationList.organizationId}"> ${organizationList.organizationName}</sf:option>
                            </c:forEach>
                        </sf:select>

                        <sf:errors path="organization.organizationId" cssclass="error"></sf:errors>

                        <sf:hidden path="hero.heroId"/>
                        <sf:hidden path="organization.organizationId"/>
                        <sf:hidden path="memberId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
                    </div>
                </div>
            </sf:form>        

        </div>




        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

