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
            <h1>SIGHTINGS</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organizationCRUD/displayOrganizationPage" id="heroes">Organization </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/memberCRUD/displayMemberPage" id="heroes">Member </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/addressCRUD/displayAddressPage" id="sightings">Address </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/locationCRUD/displayLocationPage" id="sightings">Location </a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightingCRUD/displaySightingPage" id="sightings">Sighting </a></li>
                </ul>    
            </div>



            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Sightings </h2>
                    <table id="contactTable" class="table table-hover">
                        
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td class="col-md-8">
                                    <a href="${pageContext.request.contextPath}/sightingCRUD/displaySightingDetails?sightingId=${currentSighting.sightingId}" id="tableLinkData">
                                        ${currentSighting.sightingDate}<span id="space"> </span>
                                        ${currentSighting.hero.alias}<span id="space"> </span>
                                        ${currentSighting.location.locationName}
                                    </a>
                                </td>
                                <td class="col-md-2">
                                    <a href="${pageContext.request.contextPath}/sightingCRUD/displayEditSightingForm?sightingId=${currentSighting.sightingId}" id="tableLinkData">
                                        Edit
                                    </a>
                                </td>
                                <td class="col-md-2">
                                    <a href="${pageContext.request.contextPath}/sightingCRUD/deleteSighting?sightingId=${currentSighting.sightingId}" id="tableLinkData">
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
                    <h2>Add New Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="add-sightingDate" class="col-md-4 control-label">Sighting Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="sightingDate" placeholder="Sighting Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Site Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Site Description"/>
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
                            <label class="col-md-4" id="right">Location:</label>
                            <div class="col-md-8">
                                <select name="location" id="dropDown">
                                    <option value="" disabled selected hidden>Select your option</option>
                                    <c:forEach var="locationList" items="${locationList}">
                                        <option value="${locationList.location}">${locationList.locationName} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Hero and Power"/>
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

