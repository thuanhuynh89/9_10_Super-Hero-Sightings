<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SUPER HERO SIGHTINGS</title>
        <!-- Bootstrap core CSS -->
        
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      
        <link href="${pageContext.request.contextPath}/css/SuperHeroCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container" >
            <h1>SUPER HERO SIGHTINGS</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organizationCRUD/displayOrganizationPage" id="heroes">Organization </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/memberCRUD/displayMemberPage" id="heroes">Member </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/addressCRUD/displayAddressPage" id="sightings">Address </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/locationCRUD/displayLocationPage" id="sightings">Location </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/sightingCRUD/displaySightingPage" id="sightings">Sighting </a></li>
                </ul>    

            </div>
            <h3>This Website application is for the user to be able to add hero and sighting information into MySQL database.
                They are also able to read, update, and delete these 8 list of information. <DIV>NOTE: SOME OF THE LIST NEED TO BE CREATED FIRST SINCE SOME THINGS RELATE TO OTHERS.</DIV></h3>
                <br>
            
                <div class="col-md-6" style="text-align: left">
                    <img style="text-align: left" src="http://orig02.deviantart.net/5f0e/f/2012/279/e/8/marvel_characters_wallpaper_by_superman8193-d5gzh6w.jpg" alt="" 
                         style="float: left; width: 100%;">
                </div>
                
                <div class="col-md-6" >
                    <div id="title">10 Most Recent Postings</div>
                    <div class="static"> 
                    
                    <c:forEach var="sighting" items="${sighting}" begin="0" end="9" >
                            <div>    
                                <div>Hero Sighted: ${sighting.hero.alias}</div>
                                <div>Sight Description: ${sighting.description}</div>
                                <div>Sighted Date: ${sighting.sightingDate}</div>
                            </div>
                            <br>
                    </c:forEach>
                           
                    </div>
                </div>
            
        </div>



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

