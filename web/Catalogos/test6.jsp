<%-- 
    Document   : test6
    Created on : 1 nov 2023, 10:53:19
    Author     : Desarrollo Tacts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table id="myTable">
            <tr>
                <td>Row 1</td>
                <td><input type="file" class="file-input" /></td>
            </tr>
            <tr>
                <td>Row 2</td>
                <td><input type="file" class="file-input" /></td>
            </tr>
            <!-- Add more rows as needed -->
        </table>
        <script>
            // Get all file input elements with the class 'file-input'
            const fileInputs = document.querySelectorAll('.file-input');

// Add an event listener to each file input
            fileInputs.forEach(function (inputElement) {
                inputElement.addEventListener('change', function (event) {
                    // This function will be called when a file is selected in the input
                    const selectedFile = event.target.files[0];
                    console.log('File selected:', selectedFile);
                });
            });

        </script>
    </body>
</html>
