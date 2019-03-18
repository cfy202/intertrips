package net.authorize.api.controller;

import net.authorize.api.contract.v1.CreateCustomerProfileRequest;
import net.authorize.api.contract.v1.CreateCustomerProfileResponse;
import net.authorize.api.contract.v1.ValidationModeEnum;
import net.authorize.api.controller.base.ApiOperationBase;

public class CreateCustomerProfileController extends ApiOperationBase<CreateCustomerProfileRequest, CreateCustomerProfileResponse> {

	public CreateCustomerProfileController(CreateCustomerProfileRequest apiRequest) {
		super(apiRequest);
	}

	
	@Override
	protected void validateRequest() {
		CreateCustomerProfileRequest request = this.getApiRequest();
		
		//validate required fields		
		if ( null == request.getProfile()) throw new NullPointerException("Profile cannot be null");
		if ( null == request.getRefId()) throw new NullPointerException("RefId cannot be null");
		if ( null == request.getValidationMode() || ValidationModeEnum.NONE == request.getValidationMode()) throw new NullPointerException("ValidationMode cannot be null");
		if ( null == request.getProfile().getPaymentProfiles() || 0 == request.getProfile().getPaymentProfiles().size()) throw new NullPointerException("Payment Profile cannot be null or empty");
		
		//validate not-required fields		
	    
		
		//creditCardOne.setCardCode("");

		//keyBlock.setValue(value);

		//paymentOne.setBankAccount(bankAccountOne);
		//paymentOne.setTrackData(trackDataOne);
		//paymentOne.setEncryptedTrackData(encryptedTrackDataOne);
		//paymentOne.setPayPal( payPalOne);
		
//		driversLicenseOne = new DriversLicenseType();
//		driversLicenseOne.setNumber(getRandomString("DLNumber"));
//		driversLicenseOne.setState(getRandomString("WA"));
//		driversLicenseOne.setDateOfBirth(nowString);

//	    customerPaymentProfileOne.setBillTo(customerAddressOne);
//	    customerPaymentProfileOne.setDriversLicense(driversLicenseOne);
//	    customerPaymentProfileOne.setTaxId(getRandomString("XX"));

	}

	@Override
	protected Class<CreateCustomerProfileResponse> getResponseType() {
		return CreateCustomerProfileResponse.class;
	}
}
