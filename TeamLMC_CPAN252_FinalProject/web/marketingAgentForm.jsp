<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <title>Add/Update Marketing Agent Page</title>
    </head>
    <body>
        <div class="col-sm-12" style="height:140px;background-color: lightblue; text-align: center; font-family:'Courier New', Courier, 'Lucida Sans Typewriter', 'Lucida Typewriter', monospace; font-size: 2.5em; color: white">
            <br>
            <b>Last Minute Club Printing Company.</b>
        </div>
        <div class="col-sm-12" style="background-color: AliceBlue; height: 85px">
            <center>
                <h2>
                    <a href="admin.jsp" class="btn btn-info btn-lg">Admin Home</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="newAgent" class="btn btn-info btn-lg">Add New Agent</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="listAgents" class="btn btn-info btn-lg">List All Agents</a>
                </h2>
            </center>
            <br>
        </div>
        <div class="col-sm-4">

        </div>
        <div class="col-sm-4">
            <!--if a marketingAgent is passed (as update is selected) then form action is update
            if marketingAgent is null then it is insert,  also the Edit or add marketingAgent is displayed-->
            <c:if test="${marketingAgent != null}">
                <form action="updateAgent" method="post">
                </c:if>
                <c:if test="${marketingAgent == null}">
                    <form action="insertAgent" method="post">
                    </c:if>

                    <h2>
                        <c:if test="${marketingAgent != null}">
                            Edit Marketing Agent
                        </c:if>
                        <c:if test="${marketingAgent == null}">
                            Add New Marketing Agent
                        </c:if>
                    </h2>
                    <br>   
                    <c:if test="${marketingAgent != null}">
                        <input type="hidden" name="id" value="<c:out value='${marketingAgent.id}' />" />
                    </c:if>
                    <div class="form-group">
                        <label for="firstName">First Name:</label>
                        <input type="text" class="form-control" id="locationName" name="firstName" placeholder="Enter First Name" value="<c:out value='${marketingAgent.firstName}' />">
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name:</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter Last Name" value="<c:out value='${marketingAgent.lastName}' />">
                    </div>
                    <div class="form-group">
                        <label for="phoneNo">Phone Number:</label>
                        <input type="text" class="form-control" id="phoneNo" name="phoneNo" placeholder="Enter Phone Number" value="<c:out value='${marketingAgent.phoneNo}' />">
                    </div>
                    <div class="form-group">
                        <label for="email">Last Name:</label>
                        <input type="text" class="form-control" id="email" name="email" placeholder="Enter Email" value="<c:out value='${marketingAgent.email}' />">
                    </div>
                    <button type="submit" value="Save" class="btn btn-primary">Submit</button>
                </form>
                <br><br>
                </div>
                <div class="col-sm-4">

                </div>
                <div class="col-sm-12" style="height:120px;background-color: lightblue; text-align: center; font-family:'Courier New', Courier, 'Lucida Sans Typewriter', 'Lucida Typewriter', monospace; font-size: 1.9em; color: white">
                    <br>
                    &COPY;2018 LMC ltd.
                </div>
                </body>
                </html>
