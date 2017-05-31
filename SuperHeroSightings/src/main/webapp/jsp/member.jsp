<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
            <h1>MEMBERS</h1>
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


            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Members </h2>
                    <table id="contactTable" class="table table-hover">
                        
                        <c:forEach var="currentMember" items="${memberList}">
                            <tr>
                                <td class="col-md-6">
                                    <a href="${pageContext.request.contextPath}/memberCRUD/displayMemberDetails?memberId=${currentMember.memberId}" id="tableLinkData">
                                        ${currentMember.hero.alias}<span id="space"> </span> ${currentMember.organization.organizationName}
                                    </a>
                                </td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/memberCRUD/displayEditMemberForm?memberId=${currentMember.memberId}" id="tableLinkData">
                                        Edit
                                    </a>
                                </td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/memberCRUD/deleteMember?memberId=${currentMember.memberId}" id="tableLinkData">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                   
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the new contact form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6">
                    <h2>Add New Member</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createMember">
                        <div class="form-group">
                            <label for="add-startDate" class="col-md-4 control-label">Start Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="startDate" placeholder="StartDate"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-endDate" class="col-md-4 control-label">End Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="endDate" placeholder="End Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" id="right">Hero:</label>
                            <div class="col-md-8">
                                <select name="hero" id="dropDown">
                                    <option value="" disabled selected hidden>Select your option</option>
                                    <c:forEach var="heroList" items="${heroList}">
                                        <option value="${heroList.heroId}">${heroList.alias} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" id="right">Organization:</label>
                            <div class="col-md-8">
                                <select name="organization" id="dropDown">
                                    <option value="" disabled selected hidden>Select your option</option>
                                    <c:forEach var="organizationList" items="${organizationList}">
                                        <option value="${organizationList.organizationId}">${organizationList.organizationName} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Member"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->
            </div>
        </div> <!-- End row div -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

