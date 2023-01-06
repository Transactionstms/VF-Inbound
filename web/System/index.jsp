<%@page contentType="text/html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/WEB-INF/jsp/baseLayout.jsp" flush="true">
    <tiles:put name="title" value="TransactionsTMS" />
    <tiles:put name="topheader" value="/WEB-INF/jsp/topheader.jsp" />
    <tiles:put name="header" value="/WEB-INF/jsp/header.jsp" />
    <tiles:put name="body" value="/WEB-INF/jsp/body.jsp" />
    <tiles:put name="footer" value="/WEB-INF/jsp/footer.jsp" />
</tiles:insert>
