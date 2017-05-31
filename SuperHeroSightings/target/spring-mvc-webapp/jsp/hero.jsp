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
            <h1>HEROES</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organizationCRUD/displayOrganizationPage" id="heroes">Organization </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/memberCRUD/displayMemberPage" id="heroes">Member </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/addressCRUD/displayAddressPage" id="sightings">Address </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/locationCRUD/displayLocationPage" id="sightings">Location </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/sightingCRUD/displaySightingPage" id="sightings">Sighting </a></li>
                </ul>    
            </div>



            <div class="row">
                <div class="form-group">
                    <!-- 
                        Add a col to hold the summary table - have it take up half the row 
                    -->
                    <div class="col-md-6">
                        <h2>Heroes</h2>
                        <table id="contactTable" class="table table-hover">

                            <c:forEach var="currentHero" items="${heroList}">
                                <tr>
                                    <td class="col-md-6">
                                        <a href="${pageContext.request.contextPath}/heroCRUD/displayHeroDetails?heroId=${currentHero.heroId}" id="tableLinkData">
                                            <c:out value="${currentHero.alias}"/>
                                        </a>
                                    </td>
                                    <td class="col-md-3">
                                        <a href="${pageContext.request.contextPath}/heroCRUD/displayEditHeroForm?heroId=${currentHero.heroId}" id="tableLinkData">
                                            Edit
                                        </a>
                                    </td>
                                    <td class="col-md-3">
                                        <a href="${pageContext.request.contextPath}/heroCRUD/deleteHero?heroId=${currentHero.heroId}" id="tableLinkData">
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
                        <h2>Add New Hero and New Power</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createHero">
                            <div class="form-group">
                                <label for="add-alias" class="col-md-4 control-label">Alias:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="alias" placeholder="Alias"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="firstName" placeholder="First Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="lastName" placeholder="Last Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-heroDescription" class="col-md-4 control-label">Hero Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="heroDescription" placeholder="Hero Description"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Create Hero and Power"/>
                                </div>
                            </div>
                        </form>

                    </div> <!-- End col div -->

                </div> <!-- End row div -->
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

