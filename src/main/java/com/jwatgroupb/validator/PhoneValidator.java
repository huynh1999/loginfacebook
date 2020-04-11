/*
	@author:Quang Truong
	@date: Feb 2, 2020
*/

package com.jwatgroupb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
	  public void initialize(Phone paramA) {
	  }
	  public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
	    if (phoneNo == null) {
	      return false;
	    }
	    return phoneNo.matches("\\d{10}");
	  }
	}
