<%--
To use this file, include it in your JSP file using the following code, replacing "jspName" with
the name of the JSP file that you are including it in:
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="jspName"/>
</jsp:include>
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav>
    <%--    <div>
            <a href="${pageContext.request.contextPath}/">
                <!-- TODO: Add Images? -->
                <div id="logo"><img src="image.png" alt="Home"/></div>
                <div id="headtag"><img src="image.png" alt="Home"/></div>
            </a>
        </div>--%>
    <div class="navbar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/viewDashboard"
                   class="${param.activePage == 'dashboard' ? 'active' : ''}">Dashboard</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/leaderboard/${user.getId()}"
                   class="${param.activePage == 'leaderboard' ? 'active' : ''}">Leaderboard</a>
            </li>
            <%-- Currently redundant, but checks if the user is logged in. If they aren't,
            it will display Login and Sign up buttons, otherwise it will display a Logout button.
            If in the future Spring Security is configured to allow navigation to a page with nav
            before login, this will handle that. --%>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal != null}">
                    <li style="float:right"><a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li style="float:right"><a href="${pageContext.request.contextPath}/register">Sign
                        up</a></li>
                    <li style="float:right"><a
                            href="${pageContext.request.contextPath}/login">Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>