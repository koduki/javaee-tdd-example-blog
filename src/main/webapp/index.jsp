<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="/Blog/resources/Items/upload" enctype="multipart/form-data">
            <input type="file" name="file" /><br />
            <input type="text" name="id" value="abc" /><br />
            <br />
            <input type="submit" value="upload" />
        </form>
    </body>
</html>
