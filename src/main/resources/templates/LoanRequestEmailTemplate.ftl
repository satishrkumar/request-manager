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

<table align="center" border="0" cellpadding="0" cellspacing="0" width="800" style="border-collapse: collapse;">
    <tr>
        <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            <img src="cid:app_logo" style="width:200px; display:block; margin:auto;">
            <p>Hi</p>
            <p style="margin-top: 14px;">${name} is using an exciting new app which allows friends and families
                to help each other out financially by borrowing on preferential terms
                while also providing the option to pay interest back to you, ofter
                beating the rates available from banks.</p>
            <div style="margin: 25px 40px 0 40px;">
                <p><b>Request Summary</b></p>
                <p><i>${loan_amt}</i></p>
                <p><i>${purpose}</i></p>
                <p><i>${repayment_date}</i></p>
                <p>
                    <span><i>${repayment_frequency}</i></span>
                    <span style="margin-left: 30px"><i>${repayment_amt}</i></span>
                    <span style="margin-left: 30px"><i>${repayment_amt_tot}</i></span>
                </p>
            </div>
            <div style="margin: 25px 40px 0 40px;">
                <p><i>Link to loan request summary</i>
                    <a href="${loan_req_summary_url}">Click here</a> to get full details of the requestand to register with
                    2PayUBack if you're not already a member.</p>
            </div>
            <div style="margin: 25px 40px 0 40px;">
                <p>You will also have the opportunity to modify the proposed loan terms that work for you and ${name}</p>
            </div>
        </td>
    </tr>
</table>

</body>
</html>
