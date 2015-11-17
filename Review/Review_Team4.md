
#Review of Team4 by Team9

##Design
* Violation of MVC pattern

* Usage of helper objects between view and model

* Rich OO domain model

* Clear responsibilities

* Sound invariants

* Overall code organization & reuse, e.g. views


##Coding Style

* Consistency  
The codebase is consistent. Similar classes are grouped into corresponding packages. 
Class and method naming is consistent and according to java coding style with 
camelCase, lowercase starting names in methods etc...

* Intention-revealing names  
Method names in the controller classes are very intention revealing, methods starting 
with 'display' create a view which simply displays a page most often after a get 
request of the user, 'update' updates the state after post requests. Exceptions also 
reveal in what situations they might be thrown, e.g. UserAlreadyExistsException, 
NoResultFoundException.. Tests are named intention revealing and separated into unit 
tests or integration tests.

* Do not repeat yourself  
Every service implements a unique interface. In this case I think that the interface 
might have been left out because every service has it's own interface, so for every 
service class an extra interface is created which doesn't add any functionality.
Regarding jsp pages includes were used to factor out common parts like the header and 
footer which are the same for many pages. However while reading the codebase it is not 
evident at the first moment why therere are two login.jsp/exception.jsp/signup.jsp 
pages one of them an include and the other not.

* Exceptions, testing null values  
Team4 has extensively used Error classes with intention revealing names in appropriate 
places and documented in which cases they are thrown.

* Encapsulation  
Encapsulation was used according to the Spring framework. Lecture, Message, User 
encapsulate the data which corresponds to the model for the real world. Different form 
classes are used for encapsulating the data which a user can put in by the view. 
Behaviour for the interaction of view and model is done properly in Controller classes 
like intended by the framework.

* Assertions, contracts, invariant checks  
Assertions most often assert whether the input paramters are not null. Contracts are 
clearly defined in method names and state whether a parameter mustn't be null or what 
exception can be thrown and in which case a method gets thrown.
Invariants haven't been used but I also did not see a particular case in which this 
would have been necessary.

* Utility methods  
Only utility class used was the MultipartFileMocker class which was created in the 
ch.ututor.utils package and is a helper class for mocking images for tests.

##Documentation
*Understandable  
Documentation is understandable for example considering following exerpts:
<pre><code>
/**
	 * @return	If the user's already tutor, the method returns a ModelAndView 
	 * 			with an AddLectureForm to add a new lecture. Otherwise a ModelAndView
	 * 			to become tutor is returned.
	 */
	@RequestMapping( value = {"/user/add-lecture"}, method = RequestMethod.GET )
    public ModelAndView displayAddLectureForm() {
</code></pre>
<pre><code>
   /**
     *  Updates the profile data based on the information entered in the profileEditForm
     * 
     *	@return	ModelAndView of the own profile if the update has succeeded.
	 *			Otherwise a ModelAndView with a profile edit form containing error or exception messages.
     */
    @RequestMapping( value = {"/user/profile/edit"}, method = RequestMethod.POST )
    public ModelAndView updateProfileData( 	@Valid ProfileEditForm profileEditForm, 
    										BindingResult result, 
    										RedirectAttributes redirectAttributes ) {
</code></pre>
Existing documentation is concise and describes what what will be returned and in which case.
* Intention-revealing

* Describes responsibilities  
Class documentation is missing but responsibilities are described in the method documentation which exists 
for most important methods.
Maybe in the case of the MessageCenterServiceImpl and ExceptionServiceImpl class documentation would have been 
helpful to understand the codebase a little bit faster. In all other cases documentation for methods was helpful.
*Match a consistent domain vocabulary

##Testing

* Clear and distinct test cases  
Test cases are both clear and distinct. Unit tests do not overlapp and it is clear what item is tested.

* Number/coverage of test cases  
All controller and services are tested except LoginController (which doesn't need to be tested). The classes are tested extensively including Exceptions. Very good code coverage overall.

* Easy to understand the case that is tested  
The names of the test methods are well choosen and describtive. A nice addition (but not necessary) would be a link to the tested class. Something like this:
	```
	/*
	* Test of class {@link IndexController}
	*/
	```

* Well crafted set of test data  
Use of a data seeder is a good method for setting up the test data. The data set is comprehensive and you have some nice test users :)

* Readability  
The tests are well formatted with a few missteps in line length (e.g. ProfileEditControllerTest).
However, I don't see why you didn't use the standard file structure:
	```
	main
		java ...
	tests
		java
			ch
				ututor
					controller      <- controller tests; name the integration tests ControllerNameIT (or something like that)
						service 	<- service tests
	```

I think that would be a better structure than the one you have now. You could still use test suits if you want to.
Use assertArrayEquals instead assertEquals when comparing arrays for equality (best practise).
