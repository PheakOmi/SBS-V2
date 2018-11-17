<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!— Popup Checkout Form —>
<div id="aba_main_modal" class="aba-modal">
    <!— Modal content —>
    <div class="aba-modal-content">


        <%@ page import="com.PaymentGateway.PayWayApiCheckout" %>

        <form method="POST" target="aba_webservice"
              action="<% out.print(PayWayApiCheckout.getApiUrl()); %>" id="aba_merchant_request">
            <input type="hidden" name="hash" id="hash" />
            <input type="hidden" name="tran_id" id="tran_id"/>
            <input type="hidden" name="amount" id="amount" />
            <input type="hidden" name="firstname" id="first_name"/>
            <input type="hidden" name="lastname" id="last_name"/>
            <input type="hidden" name="phone" id="phone"/>
            <input type="hidden" name="email" id="email"/>
        </form>
    </div>
    <!— end Modal content—>
</div>
<!— End Popup Checkout Form —>

<!— Page Content —>

<!— End Page Content —>

<!— Make a copy this javaScript to paste into your site—>
<!— Note: these javaScript files are using for only integration testing—>
    <link rel="stylesheet" href="/resources/css/pop_up.css"/>
    <!--<link rel="stylesheet" href="https://payway-dev.ababank.com/checkout-popup.html?file=css"/>-->
<!-- <script src="file:///home/hanako/Desktop/checkout-popup.js"></script> -->
<spring:url value="/resources/js/checkout-popup.js" var="CheckOut"/>
<script src="${CheckOut}"></script>

<!— These javaScript files are using for only production —>
<!--
<link rel="stylesheet" href="https://payway.ababank.com/checkout-popup.html?file=css"/>
<script src="https://payway.ababank.com/checkout-popup.html?file=js"></script>
-->


<!— End —>

</div>
</html>