<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>2payuback email notification</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">

    <tr>
        <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            <img src="cid:app_logo" style="width:200px; display:block; margin:auto;">
            <p>Dear <#if name??>${name}<#else>User,</#if></p>
<#--            <p>${content}</p>-->
            <p>Sending Test Email</p>
            <#--<p>Click here to verify your email <b>${token}</b></p>-->
            <p>Thanks</p>
        </td>
    </tr>
    <tr>
        <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
            <p>${signature}</p>
            <p>${location}</p>
        </td>
    </tr>
</table>

</body>
</html>


