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
            <h1>ADDRESS EDIT</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/" id="home"><span class="active glyphicon glyphicon-home" id="home"></span> Home</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/heroCRUD/displayHeroPage" id="heroes">Hero </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/powerCRUD/displayPowerPage" id="heroes">Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/HPCRUD/displayHPBridgePage" id="heroes">Hero&Power </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organizationCRUD/displayOrganizationPage" id="heroes">Organization </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/memberCRUD/displayMemberPage" id="heroes">Member </a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/addressCRUD/displayAddressPage" id="sightings">Address </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/locationCRUD/displayLocationPage" id="sightings">Location </a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/sightingCRUD/displaySightingPage" id="sightings">Sighting </a></li>
                </ul>    
            </div>

            <br><br>    
            <sf:form class="form-horizontal" role="form" modelAttribute="address"
                     action="editAddress" method="POST">
                <div class="form-group">
                    <label for="add-StreetNumber" class="col-md-4 control-label">StreetNumber:</label>
                    <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-StreetNumber"
                                  path="StreetNumber" placeholder="StreetNumber"/>
                        <sf:errors path="StreetNumber" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-StreetName" class="col-md-4 control-label">StreetName:</label>
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-StreetName"
                                  path="StreetName" placeholder="StreetName"/>
                        <sf:errors path="StreetName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-City" class="col-md-4 control-label">City:</label>
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-City"
                                  path="City" placeholder="City"/>
                        <sf:errors path="City" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-State_Province" class="col-md-4 control-label">State_Province:</label>                          
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-State_Province"
                                  path="State_Province" placeholder="State_Province"/>
                        <sf:errors path="State_Province" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-Country" class="col-md-4 control-label">Country:</label>                          
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-Country"
                                  path="Country" placeholder="Country"/>
                        <sf:errors path="Country" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-Planet" class="col-md-4 control-label">Description:</label>                          
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-Planet"
                                  path="Planet" placeholder="Planet"/>
                        <sf:errors path="Planet" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-Galaxy" class="col-md-4 control-label">Galaxy:</label>                          
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-Galaxy"
                                  path="Galaxy" placeholder="Galaxy"/>
                        <sf:errors path="Galaxy" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-Latitude" class="col-md-4 control-label">Latitude:</label>                          
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-Latitude"
                                  path="Latitude" placeholder="Latitude"/>
                        <sf:errors path="Latitude" cssclass="error"></sf:errors>
                        </div>
                    </div>    
                    <div class="form-group">
                        <label for="add-Longitude" class="col-md-4 control-label">Longitude:</label>                          
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="add-Longitude"
                                  path="Longitude" placeholder="Longitude"/>
                        <sf:errors path="Longitude" cssclass="error"></sf:errors>
                        <sf:hidden path="addressId"/>
                    </div>
                </div>   

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Address"/>
                    </div>
                </div>
            </sf:form>                

        </div>




        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
