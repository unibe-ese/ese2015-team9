# FindBugs Report

#### SignupForm.java:132 : RC_REF_COMPARISON
The file has 96 lines, and the only usage of comparison with == is for null-checks. 

### CourseService.java:37 : RC_REF_COMPARISON
CourseService does no more exist. The method is now "removeOffer" in the "AgencyService". The only == usage there is again a null-check.

### EditFormValidationService.java:
The class was renamed.
#### Correctness
== comparison only for null-checks. 
#### Performance SS_SHOULD_BE_STATIC
The field is static. 
#### BC_UNCONFIRMED_CAST
This is how Spring Validator usage is proposed in the api. To ensure correctness an AssertionError is thrown when the object is of the wrong type.

### SignupFormValidationService.java:
The class was renamed.
#### Performance SS_SHOULD_BE_STATIC
The field is static. 
#### BC_UNCONFIRMED_CAST
This is how Spring Validator usage is proposed in the api. To ensure correctness an AssertionError is thrown when the object is of the wrong type.

### Course.java:48, SE_NO_SERIALVERSIONID
The serialVersionUID was already added before the last build. 
