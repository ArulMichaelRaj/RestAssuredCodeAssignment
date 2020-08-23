<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>API Tests</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test Referral Add Page </title>
    <style>

        h1 {
            margin-left: 75px;
            color: brown;
            font-family: Verdana;

        }

        h2, Label {
            margin-left: 75px;
            color: darkblue;
            font-family: Verdana;

        }

        body {
            font-family: Verdana;
        }
    </style>
</head>
<body>
<form class="text-center border border-light p-5" method="get">
    <h1>Test Assignment</h1>
    <!-- Label Display -->
    <Label type="text" name="apiResultText" id="apiResultTextElement"
           style="width:300px;">Hello, all API tests were successful</Label>
   </form>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>