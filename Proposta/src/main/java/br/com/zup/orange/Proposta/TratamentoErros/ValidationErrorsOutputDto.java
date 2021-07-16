package br.com.zup.orange.Proposta.TratamentoErros;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorsOutputDto{
	   	
		private List<String> globalErrorsMessages = new ArrayList<>();
	    private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	    private MessageSource messageSource;
	    
	    
	    public ValidationErrorsOutputDto(MessageSource msg) {
			messageSource = msg;
		}
	    
	    @Deprecated
	    public ValidationErrorsOutputDto( ) {	
		}

		public void addError(String errorMessage) {
	        globalErrorsMessages.add(errorMessage);
	    }

	    public void addFieldError(String field, String errorMessage) {
	        FieldErrorOutputDto fieldErrorOutputDto = new FieldErrorOutputDto(field, errorMessage);
	        fieldErrors.add(fieldErrorOutputDto);
	    }

	    public List<String> getGlobalErrorMessages() {
	        return globalErrorsMessages;
	    }

	    public List<FieldErrorOutputDto> getFieldErrors() {
	        return fieldErrors;
	    }
	    
	    private String getErrorMessage(ObjectError error) {
	    	return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	    }
	    
	    public ValidationErrorsOutputDto buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
	    	
	    	ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();
	    	globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
	    	
	    	fieldErrors.forEach(error ->{
	    		String errorMessage = getErrorMessage(error);
	    		validationErrors.addFieldError(error.getField(), errorMessage);
	    	});
	    	
	    	return validationErrors;
		
	    }
	    
	    

}
