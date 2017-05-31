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
            <h1>POWERS</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organizationCRUD/displayOrganizationPage" id="heroes">Organization </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/memberCRUD/displayMemberPage" id="heroes">Member </a></li>
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
                    <h2>Powers</h2>
                    <table id="contactTable" class="table table-hover">
                        
                        <c:forEach var="currentPower" items="${powerList}">
                            <tr>
                                <td class="col-md-6">
                                    <a href="${pageContext.request.contextPath}/powerCRUD/displayPowerDetails?powersId=${currentPower.powerId}" id="tableLinkData">
                                        <c:out value="${currentPower.description}"/>
                                    </a>
                                </td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/powerCRUD/displayEditPowerForm?powersId=${currentPower.powerId}" id="tableLinkData">
                                        Edit
                                    </a>
                                </td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/powerCRUD/deletePower?powersId=${currentPower.powerId}" id="tableLinkData">
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
                    <h2>Add New Power</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createPower">
                        <div class="form-group">
                            <label for="add-powerDescription" class="col-md-4 control-label">Power Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Power Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Power"/>
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

