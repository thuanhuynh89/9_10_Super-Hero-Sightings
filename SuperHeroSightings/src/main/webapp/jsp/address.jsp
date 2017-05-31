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
            <h1>ADDRESSES</h1>
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


            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Addresses</h2>
                    <table id="contactTable" class="table table-hover">

                        <c:forEach var="currentAddress" items="${addressList}">
                            <tr>
                                <td class="col-md-6">
                                    <a href="${pageContext.request.contextPath}/addressCRUD/displayAddressDetails?addressId=${currentAddress.addressId}" id="tableLinkData">
                                        <c:out value="${currentAddress.streetNumber}"/>
                                        <c:out value="${currentAddress.streetName}"/>
                                    </a>
                                </td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/addressCRUD/displayEditAddressForm?addressId=${currentAddress.addressId}" id="tableLinkData">
                                        Edit
                                    </a>
                                </td>
                                <td class="col-md-3">
                                    <a href="${pageContext.request.contextPath}/addressCRUD/deleteAddress?addressId=${currentAddress.addressId}" id="tableLinkData">
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
                    <h2>Add New Address</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createAddress">
                        <div class="form-group">
                            <label for="add-StreetNumber" class="col-md-4 control-label">StreetNumber:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="StreetNumber" placeholder="StreetNumber"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-StreetName" class="col-md-4 control-label">StreetName:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="StreetName" placeholder="StreetName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-City" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="City" placeholder="City"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-State_Province" class="col-md-4 control-label">State_Province:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="State_Province" placeholder="State_Province"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-Country" class="col-md-4 control-label">Country:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="Country" placeholder="Country"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-Planet" class="col-md-4 control-label">Planet:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="Planet" placeholder="Planet"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-Galaxy" class="col-md-4 control-label">Galaxy:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="Galaxy" placeholder="Galaxy"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-Latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="Latitude" placeholder="Latitude"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-Longitude" class="col-md-4 control-label">Longitude:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="Longitude" placeholder="longitude"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Address"/>
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

