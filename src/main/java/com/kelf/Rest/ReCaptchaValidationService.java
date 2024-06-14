package com.kelf.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaValidationService {

private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";
    
private final String RECAPTCHA_SECRET = "6LcdFTwoAAAAAI-gAzCyJgI-4gIP3EpMArYpebiX";

    public boolean validateCaptcha(String captchaResponse){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", RECAPTCHA_SECRET);
        requestMap.add("response", captchaResponse);

        ReCaptchResponseType apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptchResponseType.class);
        if(apiResponse == null){
            return false;
        }

        return Boolean.TRUE.equals(apiResponse.isSuccess());
    }
    @Autowired
	public EmployeeRepository employeeRepository;
	
    public String signup(EmployeeEntity e)
    {
    	employeeRepository.save(e);
    	return "Employee Added";
    }
    
    public EmployeeEntity checkadminlogin(String uname, String pwd)
    {
    	return employeeRepository.checkemployeelogin(uname, pwd);
    }
}