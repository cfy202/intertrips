<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${booking.product.name}</title>
</head>
<body style="margin:0; padding:0;background:#f3f3f3;">
<div>
	<div style="height:40px;"></div>
	<div style="width:780px; margin:0 auto;background:#fff; border:1px solid #e3e3e3;font-family: Tahoma,Arial,Helvetica,STHeiti; font-size:13px; color:#333;">
    	<table cellpadding="0" cellspacing="0">
        	<tr>
            	<td width="25"></td>
                <td width="730"><img src="https://www.intertrips.com/assets-web/images/intertrips-email-bg.jpg"></td>
                <td width="25"></td>
            </tr>
        	<tr>
            	<td width="25"></td>
                <td width="730"><img width="200" src="https://www.intertrips.com/assets-web/images/InterTrips.png"></td>
                <td width="25"></td>
            </tr>            
        </table>
        <table cellpadding="0" cellspacing="0">
        	<tr height="20"><td colspan="3"></td></tr>
            <tr>
                <td width="25"></td>
                <td width="730">
                	<p style="margin-bottom:0; font-weight:bold; font-size:15px;">Hello <span>${(booking.name)!}</span>:</p>
                    <p style="margin-bottom:0;">Thank you for travelling with InterTrips, You Ordered <a href="https://www.intertrips.com${booking.product.pagePageid.filepath}" target="_blank" style="color:#333;">${booking.product.name}</a> online. <#if departure??>The departure city is ${(departure.name)!}.</#if>This is a confirmation email for your order's successful submission.</p>
                </td>
                <td width="25"></td>
            </tr>
            <tr height="10"><td colspan="3"></td></tr>
            <tr>
                <td width="25"></td>
                <td width="730">
                	<table cellpadding="0" cellspacing="0" width="100%">
                    	<tr>
                        	<td><b>Order No.：<span>${booking.bookingNo}</span></b></td>
                            <td><b>Status：<span>Paid</span></b></td>
                            <td><b>Total Amount：<span>$${booking.total}</span></b></td>
                        </tr>
                    </table>
                </td>
                <td width="25"></td>
            </tr>
            <tr height="10"><td colspan="3"></td></tr>
        	<tr>
                <td width="25"></td>
                <td width="730">
                    <table width="100%" cellpadding="0" cellspacing="0" style="border-left:1px solid #e3e3e3;border-top:1px solid #e3e3e3;">
                        <tr height="25">
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:12%;">Date</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:30%;">Product description</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:12%;">Quantity</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:23%;">Price</th>
                        </tr>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${departureString!}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${booking.product.name}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${booking.pax}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>Fare:&nbsp;&nbsp;$${booking.total}</p>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
                <td width="25"></td>
                <td width="730" align="right">
                	<p style="margin-bottom:0;"><b>Total price:<span style="color:red;">$${booking.total}</span></b></p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
                <td width="25"></td>
                <td width="730">
                    <p style="margin-bottom:0;">
                        This e-mail is generated by computer, please don't reply.
                    </p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin-bottom:0;">If you have any question, please contact our customer service center via phone:
                		  888-410-4111 or via email: 
                    	  <a href="mailto:Info@InterTrips.com" target="_blank" style="color:#333;">Info@InterTrips.com</a>.
                    </p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin-bottom:0;">Thank you for choosing <a href="https://www.intertrips.com/" target="_blank" style="color:#333;">InterTrips.com</a>.</p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="border-bottom:1px solid #e3e3e3;"></p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin:0;">By Placing your order, you agree to InterTrips' Terms of Use.</p>
	                    <p style="margin-bottom:0;">Delivered by <a href="https://www.intertrips.com/" target="_blank" style="color:#333;">InterTrips</a>, 680 Brea Canyon Rd, Suite 278, Diamond Bar, CA, 91789, USA.</p>
	                	<p>California Seller of Travel #2080370-40</p>
                </td>
                <td width="25"></td>
            </tr>
           <tr>
            	<td width="25"></td>
            	<td width="730">
                    <!--
                	<p style="margin:0; font-weight:bold;">One bullet added. Sorry!</p>
                	-->
	                <p style="margin-bottom:0;">1. Special Promotion Tour Packages <br/> The tour package you just purchased is heavily discounted; it is non-refundable, non-transferable. To protect your own benefits, we strongly suggest you purchase ‘<a href="https://www.travelexinsurance.com/index.aspx?location=05-1808&go=bp">Trip Interruption and Cancelation Insurance</a>’.</p>
  			       <p>2.How to update the personal information you provided to us<br/>If the information you input is incorrect, or you have missed anything when making reservation online, please e-mail us the correct one within 24 hours after you received this confirmation, along with your booking ID, we will help fix in our system.</p>
			<p>3.Passport <br/>The passport must have at least six months validity remaining to be allowed entry.<br/>Your full name (including middle name), birthday and passport number on your booking must match your passport; otherwise Airlines may refuse you to board. If you are not sure if you have provided us the correct name, please email us your passport copy with booking ID.</p>
  			<p>4.Forms – Call In Customers Only<br/>For bookings made by our agent over telephone, please fill out ‘<a href="https://www.intertrips.com/resources/pdf/TourReservationForm.pdf">Tour Reservation Form</a>’, ‘<a href="https://www.intertrips.com/resources/pdf/CreditCardAuthorizationForm.pdf">Credit Card Authorization Form</a>’. (Online booking please skip this)</p>
  			<p>5.Ticketing<br/>
                We reserve the right to have your tickets issued anytime from today till your departure date. Thus some of you may not be able to make seat assignment in advance.<br/>
                Please do not purchase domestic air tickets from your hometown to gateway yet, until you receive our air tickets confirmation by e-mail.<br/>
                If you worry about the domestic air ticket price may change, you can use our fixed price add-on fares chart, and we will have it issued with your international air ticket.<br/>
                We are able to upgrade your seat to premium economy at $400 each way, or to business class at $1500 each way, all tax included. The price would be subject to change based on airlines.
            </p>
            <p>6.VISA<br/>Your passport must have at least six months validity remaining to be allowed entry foreign country.<br/>
                The following instructions is for US passport holders only, if you are holding passport from any countries other than US, please check with destination country embassy.<br/>
                CHINA: U.S. citizens need apply for China Visa to enter China.<br/>
                Option 1: single-entry-group-visa: cost $169 per US passport, no need to mail your passports<br/>
                We only need your passport copy to apply, we will ship the granted visa document to your home in about 1-2 weeks before your trip, and you don’t have to mail your passport. Please provide correct mailing address.<br/>
                Option 2: 10-year-multiple-entry-visa: Consulate charges $140 per US passport, plus $59 per passport service fee, and $30 return fee by FedEx per address.<br/>
                Please ship us the following items in about 1-2 months before your trip:<br/>
                Your original passport must have at least 1 blank visa page<br/>
                One 2 by 2 passport photo<br/>
                One <a href="https://www.intertrips.com/resources/pdf/ChinaVisaApplicationForm.pdf">Visa Application Form</a> (please fill in online, print out and sign)<br/>
                VIETNAM: U.S. citizens need apply for Vietnam Visa to enter Vietnam.<br/>
                Option 1: Travelers can apply for an e-visa online by themselves. To apply for e-visas, foreign citizens can visit the e-visa page of Vietnam Immigration’s website. The application is available in English. Applicants will receive an application code and will be asked to pay a non-refundable, $25 per US passport online. Guests need to upload their passport plus 1 photo (straight looking without glasses). According to the directive, it will take three (3) business days for tourists to find out if their applications have been approved or not. However, we suggest you allow 7 business days in case of any issues with the internet or the website. we are not responsible for any trouble on arrival.<br/>
                Option 2: we can apply for an e-visa online, on behalf of clients who do not want to do it on your own: we need your photo and passport copy, we will complete the approval process online for you with the Vietnam Immigration department. The total fee will be $95. We will take responsibility if any trouble occurred when you arrive. <br/>
            	THAILAND: U.S. citizens carrying a tourist passport and in possession of an onward or return airline ticket do not require a visa to enter Thailand. Upon entry, Thai immigration officials will place an immigration stamp in the passport permitting a 30-day stay in Thailand if arriving by air or land.<br/>
        		LAOS:  Tourist visas on arrival are available at certain ports of entry, and generally permit a stay of 30 days. Your passport must have at least two blank visa pages.<br/>
                Visa processing fee is $35 for American and $42 for Canadian, please have cash ready.<br/>
                JAPAN/SOUTH KOREA/TAIWAN/HONG KONG/SINGAPORE: U.S. citizens carrying a tourist passport and in possession of an onward or return airline ticket do not require a visa for short-term stay less than 30 days.
            </p>
            <p>7.Travel Insurance<br/>Lots of things can happen to interrupt your travel (disease, wars, and weather conditions), We strongly recommend that you purchase a travel protection plan to help protect you and your travel investment against the unexpected. For your convenience, we offer travel protection through Travelex Insurance Services. <br/>
                 For more information on the available plans or to enroll, go to <a href="https://www.travelexinsurance.com/index.aspx?location=05-1808&go=bp">www.travelexinsurance.com</a> or contact Travelex Insurance Services at 800-228-9792 and reference location number 05-1795.<br/>
            <p>
            <p>8.Baggage Allowance<br/>We strongly suggest you travel light, please only bring one check in luggage under 44LB/20KG, and one carryon luggage under 11LB/5KG. Luggage fee may apply on some flights.</p>
            <p>9.Options<br/>Optional tours:<br/>
                China: If you haven't purchased your preferred optional tours online, please purchase from your tour guide in China. <br/>
                Japan: Please purchase your preferred optional tours from your tour guide in Japan, except pre-tour extension to Hiroshima.<br/>
                Southeast Asia: If you haven't purchased your preferred optional tours online, please purchase from your tour guide in Southeast Asia.<br/>
                Earlier Departure & Late Return:<br/>
                We are able to help before we issue your ticket; the group deviation fee is $200, plus fare difference if there is any.
            </p>
            <p>10.Final Confirmation<br/>we will provide final confirmation in about 30 days prior to departure, you are welcome to e-mail us if you have any questions. <a href="mailto:info@intertrips.com">info@intertrips.com</a></p>
            <p>Have A Pleasant Journey! </p>
		</td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
            		<#--
                	<span style="font-family:Tahoma,Arial,Helvetica,STHeiti;font-size:15px;">
                    	<a style="text-decoration:none;color:#ffffff;" href="" target="_blank">
                        	<div style="padding:10px 25px; width:70px;border-radius:3px;text-align:center;text-decoration:none;background-color:#ffa800;color:#ffffff;font-size:15px;margin:0;">Pay Now</div>
                        </a>
                    </span>
                    -->
                </td>
                <td width="25"></td>
            </tr>
            <tr height="30"><td colspan="3"></td></tr>
        </table>
    </div>
</div>
</body>
</html>
