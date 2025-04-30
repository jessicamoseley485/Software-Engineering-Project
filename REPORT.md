# COM1028 - Software Engineering


# Assignment 1 - Report

**Student Name: Jessica Moseley**

**Student ID: 6864843**

## 1.1 Proto Personas

### Persona 1

 **Jon, a casting director**

Jon, age 31, is a casting director for a production company located in Bristol. He works alongside directors to find the best actors for roles within films and other productions. Originally from Kent, he relocated to London for university, where he achieved a degree in Film Production from the University of Greenwich. He has eight years of experience within the film industry, three of which being in his current role, although he worked alongside more experienced casting directors as a casting assistant prior to this.

Jon is particularly interested in the FlickFinder application as a tool to aid him in finding potential talent for the productions he works on, as it will allow him to find other examples of an actor’s work to give him a starting point for further research.

### Persona 2

**Celia, a sixth form teacher**

Celia, age 27, is a teacher at a small sixth form in Kingston with around 120 students. Originally from Glasgow, she has an undergraduate degree from the University of Glasgow in Film and Television Studies, as well as a Postgraduate Certificate in Education. She worked as an assistant teacher in Glasgow for three years after graduation, before relocating to Kingston after being offered her current position as an A-Level Film Studies teacher.

Celia’s experience with the A-Level Film Studies curriculum means that she is particularly interested in the FlickFinder application as it would allow her students easier access to reliable data for use in their assignments.

## 1.2 Scenario

**Casting for a new movie**

Jon is a casting director for a production company located in Bristol. He works alongside directors to find the best actors for roles within films and other productions. His company has recently started the casting process for the lead roles for a new film and he has been tasked with casting the two lead roles.

As a part of the casting decision, Jon holds an audition for prospective actors for the roles. After this audition, he creates a shortlist of actors for further consideration with the plan to do further research on any past productions they have been a part of to better understand their skill set. This further research can be time consuming when done on the scale that he is required to complete it on, so he searches for a more streamlined way of obtaining this information.

After asking other casting directors at his company, Jon is introduced to the FlickFinder application, an application that allows people to easily find the movies that a particular actor has starred in, as well as the respective ratings of those movies. Jon uses the FlickFinder application to streamline his research process by finding the movies that a particular actor he is considering for the role has been a part of and then using the rating feature to give a general overview of how well those movies have been received by audiences.

## 1.3 User Stories

### User Story 1

**Retrieve details of a specific movie**

As a user, I want to be able to retrieve the details of a specific movie based on its ID so that I can find the relevant details about the movie without searching through a list of movies.

### User Story 2

**Retrieve details of a specific person**

As a user, I want to be able to retrieve the details of a specific person based on their ID so that I don’t have to leave the app to do further research on the stars in a specific movie.

### User Story 3

**Limit the number of search results**

As a user, I want to be able to limit the number of search results so that it reduces the time taken to gather the needed information.

### User Story 4

**Output a list of all the stars in a specific movie**

As a user, I want to be able to find a list of all the stars in a specific movie so that I can remind myself of the name of an actor I recognise.

### User Story 5

**Find the highest rated movie for a specific year**

As a user, I want to be able to find the highest rated movie for a specific year so that I can easily decide on what movie to watch for my weekly movie nights.

### User Story 6

**Find a list of movies that feature a specific star**

As a user, I want to find a list of movies that a specific star is in so that I can watch all the movies containing that star.

## 2 Critical Analysis and Reflection

### 2.1 Reflection

This project was overall a success as all the required functionality was implemented as described in both the functional and non-functional requirements. There is sufficient security regarding accessing the database itself as the application utilises a singleton design pattern, ensuring that there is only one instance of the database which can then be accessed by other classes within the program. This prevents multiple instantiations of the database. The singleton pattern also allows reduced resource inefficiency and data inconsistency, improving the sustainability of the application through more efficient code and reducing the chances of redundant data. This security measure, along with the MVC architecture, is sufficient for this data as it is publicly available information and therefore doesn’t require higher security or a more secure architecture such as a layered architecture.

Possible improvements are mainly related to efficiency. Efficiency could be increased by reducing duplicate code and reducing the length of methods, especially in the controller classes. There could also be fewer unit tests testing certain errors as it isn’t necessary to test the same exceptions every time they are thrown in a class.

### 2.2 Professional Aspects

There are legal and ethical issues surrounding the creation of the FlickFinder application, such as the legislation that is in place regarding accessibility and data protection. WCAG 2.2 level AA is a set of guidelines surrounding the accessibility requirements for software to ensure that it is accessible to a wide range of users, including those with impairments to their vision, hearing, mobility, and understanding. These guidelines cover the ability to access content using most assistive technologies such as screen readers and screen magnifiers.

Data protection laws are applicable to the FlickFinder application when storing user data. The protection of user data should be in alignment with the Data Protection Act 2018. According to this act, information must be used fairly and transparently, kept up to date, and handled in a way that ensures appropriate security.

The FlickFinder application uses a Model View Controller (MVC) design pattern. This design pattern is made up of three logical components that interact with each other to allow the user to access the data within the database. The user interacts with the system through the controller component, and this component passes these interactions to the view and model components.

Code maintainability is important regarding the long-term development of the FlickFinder application. One way to allow for easier code maintainability is properly formatted code as it is easier to update, leading to easier code evolution. The FlickFinder application allows for easy code maintainability as the MVC architecture allows for self-contained components that can be altered reasonably independently of each other.

Sustainability involves using the lowest amount of energy possible when running an application while still implementing the required functionality. One of the most effective ways of doing this for the FlickFinder application is ensuring that the code is as efficient as possible through utilising the best design patterns, data types, and coding practices such as reduced repetition of code. 

## 3. References

Appleton, J. 2025. *Security Engineering*. [Lecture to BSc Computer Science Year 1]. University of Surrey, 4 April 2025.

Appleton, J. 2025. *Sustainability and Professional Aspects*. [Lecture to BSc Computer Science Year 1]. University of Surrey, 11 April 2025.

Sommerville, I. 2015. *Software Engineering, Global Edition*. 10th ed. London: Pearson Education, Limited.

UK Government. 2024. *Understanding WCAG 2.2*. Available at: https://www.gov.uk/service-manual/helping-people-to-use-your-service/understanding-wcag  [Accessed: 27 April 2025]. 
