# realEstate
Simplified Real Estate Management System using Spring Boot, Hibernate, REST APIs, TDD approach

# Problem Statement: Real Estate Manager

Imagine you're building a simplified backend system for a real estate agency. Your goal is to develop a Spring Boot application that manages property listings and provides basic functionalities for both agency staff and potential buyers.
Here's the scenario you'll be tackling:

# 1. Property Entity:
   Create a Hibernate entity for a "Property." Each property has these attributes: `id`, `address`, `price`, `numberOfBedrooms`, `numberOfBathrooms`, and `areaSquareFeet`.

# 2. RESTful Endpoints:
   Design RESTful APIs to perform CRUD operations on properties.
    - Create an endpoint to add a new property.
    - Create an endpoint to retrieve a property by its ID.
    - Create an endpoint to update property details.
    - Create an endpoint to delete a property.

# 3. Annotation Utilization:
   Use Spring annotations like `@RestController`, `@RequestMapping`, `@PostMapping`, `@GetMapping`, `@PutMapping`, and `@DeleteMapping` to create well-structured APIs.



# 4. Test-Driven Development:
   Embrace Test-Driven Development (TDD) by writing unit tests for your services and controllers. Use frameworks like JUnit and Mockito to ensure the reliability of your code.

# 5. Database Integration:
   Integrate Hibernate to persist property data in a MySQL database. Design the necessary tables and relationships.

# 6. Basic Business Logic:
   Implement some basic business logic:
    - Calculate and return the average price of properties.
    - Provide an endpoint to retrieve properties with a certain number of bedrooms and bathrooms.

# 7. Data Validation:
   Implement data validation for property attributes. For example, ensure that the price is not negative and the number of bedrooms is a positive integer.

# 8. Search Functionality:
   Implement a search functionality that allows buyers to retrieve properties within a certain price range.
