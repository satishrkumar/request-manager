<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Promissory note</title>
</head>
<body style="margin: 0; padding: 0;">
    <h2 style="text-align: center;">
        <span>PROMISSORY NOTE</span>
    </h2>

    <div class="declaration">
        <p>${borrower.firstName} ${borrower.lastName} of ${borrower.address1},
            ${borrower.address2} ${borrower.country} ${borrower.postalcode} (we or us)
            promises to pay
            ${lender.firstName} ${lender.lastName} of ${lender.address1},
            ${lender.address2} ${lender.country} ${lender.postalcode} (you)</p>
    </div>

    <div class="interest">
        <h3>Interest</h3>
        <ul>
            <li>Interest will be calculated on the unpaid balance of the Main Debt at a fixed yearly interest rate of 0%.</li>
        </ul>
    </div>

    <div class="installment">
        <h3>Installments</h3>
        <ul>
            <li>We will make ${apr} payments of ${principal} weekly on Monday of each week, with the first payment being made on 01 October 2021 and a final payment of 10,000 at the end of the loan term on ${repaymentDate}.</li>
        </ul>
    </div>

    <div class="payments_application">
        <h3>Application of payments</h3>
        <ul>
            <li>Payments are to be applied to interest first and then to the Main Debt.</li>
        </ul>
    </div>

    <div class="prepayment">
        <h3>Prepayment</h3>
        <ul>
            <li>We may pay the unpaid balance of the Main Debt at any time..</li>
        </ul>
    </div>

    <div class="late_payment">
        <h3>Late/ No payment</h3>
        <ul>
            <li>The full balance of the Main Debt will become payable immediately, together with accrued interest, should we be late in making any payment due to you.</li>
        </ul>
    </div>

    <div class="court_costs">
        <h3>Lawyers’ fees and court costs</h3>
        <ul>
            <li>We are responsible for your lawyers’ fees and court costs associated with the enforcement of this agreement, should we fail to fulfil the terms of this agreement.</li>
        </ul>
    </div>

    <div class="jurisdiction">
        <h3>Governing law and jurisdiction</h3>
        <ul>
            <li>This Agreement shall be governed by and interpreted according to the law of England and Wales and all disputes arising under the Agreement (including non-contractual disputes or claims) shall be subject to the exclusive jurisdiction of the English and Welsh courts.</li>
        </ul>
    </div>

    <div class="variations">
        <h3>Variations</h3>
        <ul>
            <li>This agreement cannot be varied by us, without your prior written acceptance.</li>
        </ul>
    </div>

    <div class="entire_agreement">
        <h3>Entire agreement</h3>
        <ul>
            <li>This agreement constitutes the entire agreement between us and overrides other oral or written agreements entered into, concerning this subject matter.</li>
        </ul>
    </div>

    <div class="signature">
        <p>The parties have signed this Agreement the date(s) below:</p>
        <div class="borrower_signature">
            <span>_____________________________</span>
            <span style="float: right">_____________________________</span>
        </div>
        <div class="borrower_name">
            <span>${borrower.firstName} ${borrower.lastName}</span>
        </div>
        <div class="lender_signature">
            <span>_____________________________</span>
            <span style="float: right">_____________________________</span>
        </div>
        <div class="lender_name">
            <span>${lender.firstName} ${lender.lastName}</span>
        </div>
    </div>

</body>
</html>
