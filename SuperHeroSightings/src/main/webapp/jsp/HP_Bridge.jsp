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
        <style>

        </style>
    </head>
    <body>
        <div class="container">
            <h1>HEROES AND POWERS </h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
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
                    <h2>Heroes and Powers</h2>
                    <table id="contactTable" class="table table-hover">

                        <c:forEach var="currentHP" items="${hpList}">
                            <tr>
                                <td class="col-md-6">
                                    <a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgeDetails?HPId=${currentHP.hero.heroId}" id="tableLinkData">
                                        ${currentHP.hero.alias}<span id="space"> </span>
                                        ${currentHP.power.description}
                                    </a>
                                </td>
                                <td class="col-md-3"></td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/HPCRUD/deleteHeroPower?HPDelete=${currentHP.hero.heroId}&HPDelete2=${currentHP.power.powerId}" id="tableLinkData">
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
                    <h2>Add New Hero and Power Connection</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createHPBridge">


                        <div class="form-group">

                            <label class="col-md-4" id="right">Hero:</label>
                            <div class="col-md-8">
                                    <select name="hero" id="dropDown">
                                        <option value="" disabled selected hidden>Select your option</option>
                                        <c:forEach var="heroList" items="${heroList}">
                                            <option value="${heroList.heroId}">${heroList.alias}</option>
                                        </c:forEach>
                                    </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4" id="right">Power:</label>
                            <div class="col-md-8">
                                <select name="power" id="dropDown">
                                    <option value="" disabled selected hidden>Select your option</option>
                                    <c:forEach var="powerList" items="${powerList}">
                                        <option value="${powerList.powerId}">${powerList.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Hero Power Connection"/>
                            </div>
                        </div>
                    </form>

                    Pick Your Hero to see more details:
                    <c:forEach var="currentHP" items="${heroList}" >


                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgeDetails?HPId=${currentHP.heroId}" id="pickHero">

                                    ${currentHP.alias}<span id="space"> </span>

                                </a>
                            </td>

                        </tr>
                    </c:forEach>
                </div>
            </div> <!-- End row div -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

